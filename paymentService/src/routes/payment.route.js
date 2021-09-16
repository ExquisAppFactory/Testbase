const router = require('express').Router();
const { 
    getBankList, 
    verifyPaymentController 
} = require('../controllers/payment.controller');

router.get('/banks', getBankList);
router.post('/verify', verifyPaymentController);

module.exports = router;