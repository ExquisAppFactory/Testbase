const axios = require("axios");
const Payment = require('../models/payment.model');
require('dotenv').config();
const { v4 } = require('uuid')

const getBankList = async (req, res) => {
    console.log(req.body);
    await axios({
        method: 'GET',
        url: 'https://api.paystack.co/bank',
        headers: {
            "Authorization": `Bearer ${process.env.PAYSTACK_SECRET_KEY}`
        }
    })
    .then((res) => res.data)
    .then((data) => {
        return res.status(200).json({
            message: data.message,
            data: data.data.map(({ name, code }) => ({
                name,
                code
            })),
            isSuccessful: true
        });
    })
    .catch((err) => {
        console.log({ err });
        res.status(400).json({
            message: 'Request Failed',
            data: null,
            isSuccessful: false
        });
    })
}

const verifyPaymentController = async (req, res) => {
    const { accountNumber, bankCode, reference } = req.body;

    if (!accountNumber || !bankCode) {
        res.status(400).json({
            message: 'Bad Request',
            description: 'accountNumber or bankCode is missing',
            isSuccessful: false,
            status: 400
        });
        return;
    }

    try {
        const response = await axios({
            method: 'GET',
            url: `https://api.paystack.co/transaction/verify/${reference}`,
            headers: {
                "Authorization": `Bearer ${process.env.PAYSTACK_SECRET_KEY}`
            }
        });

        if (!response.data) {
            throw new Error('Payment Verification Failed');
        }
        
        const paymentData = response.data.data;
        const data = {
            amount: paymentData.amount,
            currency: paymentData.currency,
            transactionDate: paymentData.transaction_date,
            status: paymentData.status,
            reference: paymentData.reference,
        }
        const payments = new Payment({
            wallet_id: v4(),
            paid_by: req.body.email,
            paid_to: req.body.reference,
            payment_amount: req.body.amount,
            status: paymentData.status
        });
        const savePayment = await payments.save((err, data) => {
            if (err) {
                return res.status(400).json({
                    message: 'Update Failed',
                    isSuccessful: false,
                    description: undefined,
                    data: null
                });
            }

            res.status(200).json({
                message: 'Payment Verified', 
                isSuccessful: true,
                data
            });
        });         
    } catch(err) {
        console.log({ err })
        res.status(400).json({
            message: 'Update Failed',
            isSuccessful: false,
            description: undefined,
            data: null
        })
    }
}

module.exports = { getBankList, verifyPaymentController }