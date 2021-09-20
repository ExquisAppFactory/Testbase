import axios from "axios";
import { PaymentSchema, VerifyPaymentModel } from '../models';
import express from 'express';
require('dotenv').config();
import { v4 } from 'uuid';
import { validateVerifyProps } from "../utils";

type Request = express.Request<never, never, VerifyPaymentModel>;

export const verifyPaymentController = async (request: Request, response: express.Response) => {
    const { accountNumber, bankCode, reference, email, amount } = request.body;

    validateVerifyProps({ reference, bankCode, accountNumber, email, amount });

    await axios({
        method: 'GET',
        url: `https://api.paystack.co/transaction/verify/${reference}`,
        headers: {
            "Authorization": `Bearer ${process.env.PAYSTACK_SECRET_KEY}`
        }
    })
    .then(response => {
        const paymentData = response.data.data;
        
        const data = {
            amount: paymentData.amount,
            currency: paymentData.currency,
            transactionDate: paymentData.transaction_date,
            status: paymentData.status,
            reference: paymentData.reference,
        }

        const payments = new PaymentSchema({
            wallet_id: v4(),
            paid_by: email,
            paid_to: reference,
            payment_amount: paymentData.amount / 100,
            status: paymentData.status
        });

        return { payments, data }
    })
    .then(({payments, data}) => {
        console.log({ payments, data });
        payments.save((err) => {
            if (err) {
                return response.status(400).json({
                    message: 'Update Failed',
                    isSuccessful: false,
                    description: undefined,
                    data: null
                });
            }

            response.status(200).json({
                message: 'Payment Verified', 
                isSuccessful: true,
                data
            });
        }); 
    })
    .catch(err => {
        console.log({ err });
        throw new Error('Payment Verification Failed');
    });
}