import { Router } from 'express';
import { fetchTransactions, getBankList, verifyPaymentController } from '../controllers';

const router = Router();

router.get('/banks', getBankList);
router.get('/transactions', fetchTransactions);
router.post('/verify', verifyPaymentController);

export default router;