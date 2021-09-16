const mongoose = require("mongoose")

const paymentSchema = mongoose.Schema({
    wallet_id: {
        type: String,
        required: true 
    },
    paid_by: {
        type: String,
        required: true
    },
    paid_to: {
        type: String,
        required: false
    },
    status: {
        type: String,
        required: true
    },
    payment_amount : {
        type: Number,
        required: true
    }
})

module.exports = mongoose.model("Payment", paymentSchema)