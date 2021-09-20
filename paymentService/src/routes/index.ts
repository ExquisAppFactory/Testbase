import { Router } from 'express';
import { getBankList, verifyPaymentController } from '../controllers';

const router = Router();

router.get('/banks', getBankList);
router.post('/verify', verifyPaymentController);

export default router;