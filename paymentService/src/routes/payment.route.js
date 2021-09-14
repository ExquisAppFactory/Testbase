const router = require('express').Router();
const { 
    updatePaymentController, 
    verifyPaymentController 
} = require('../controllers/payment.controller');

router.post('/update-payment', updatePaymentController);
router.post('/verify', verifyPaymentController);

module.exports = router;