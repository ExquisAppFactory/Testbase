const axios = require("axios");
const Payments = require('../models/payment.model');
require('dotenv').config();

const updatePaymentController = async (req, res) => {
    console.log(req.body);
    res.status(200).send('Make Payment');
}

const verifyPaymentController = async (req, res) => {
    const { accountNumber, bankCode } = req.body;

    if (!accountNumber || !bankCode) {
        res.status(400).send({
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
        status: 'pending'
    })

    try {
        const { data } = await axios({
            method: 'GET',
            url: `https://api.paystack.co/transaction/verify/${req.body.reference}`,
            headers: {
                "Authorization": `Bearer ${process.env.PAYSTACK_SECRET_KEY}`
            }
        });
        
        const paymentData = data.data;
        payment.status = paymentData.status;
        const pay = await payment.save();
        console.log({ pay, paymentData })
        return res.status(200).json({
            message: data?.message ?? '',
            isSuccessful: true,
            data: {
                amount: data.data.amount,
                currency: data.data.currency,
                transactionDate: data.data.transaction_date,
                status: data.data.status,
                reference: data.data.reference,
            }
        }); 
    } catch(err) {
        console.log({ err });
    res.status(400).json({
        message: 'Update Failed',
        isSuccessful: false,
        description: undefined,
        data: null
    })
    }
}

module.exports = { updatePaymentController, verifyPaymentController }