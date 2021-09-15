const axios = require("axios");
const Payments = require('../models/payment.model');
require('dotenv').config();

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
        console.log({ data });
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
    res.status(200).json('Make Payment');
}

const verifyPaymentController = async (req, res) => {
    const { accountNumber, bankCode } = req.body;

    if (!accountNumber || !bankCode) {
        res.status(400).json({
            message: 'Bad Request',
            description: 'accountNumber or bankCode is missing',
            isSuccessful: false,
            status: 400
        });
        return;
    }
    
    const payment = await new Payments({
        wallet_id: 1,
        paid_by: req.body.email,
        paid_to: req.body.reference,
        payment_amount: req.body.amount,
        status: 'Pending'
    })

    try {
        const response = await axios({
            method: 'GET',
            url: `https://api.paystack.co/transaction/verify/${req.body.reference}`,
            headers: {
                "Authorization": `Bearer ${process.env.PAYSTACK_SECRET_KEY}`
            }
        });

        if (!response.data) {
            throw new Error('Payment Verification Failed');
        }
        
        const paymentData = response.data.data;
        payment.status = paymentData.status;
        console.log({ payment });

        const data = {
            amount: paymentData.amount,
            currency: paymentData.currency,
            transactionDate: paymentData.transaction_date,
            status: paymentData.status,
            reference: paymentData.reference,
        }

        console.log({ data });
        await payment.save();
        res.status(200).json({
            message: 'Verified Payment', 
            isSuccessful: true,
            data
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