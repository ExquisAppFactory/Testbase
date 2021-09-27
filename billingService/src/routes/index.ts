import { Router } from 'express';
import { createBillingController } from '../controllers';

const router = Router();

router.post('/billing', createBillingController);
// router.get('/billings', getBillingsController);
// router.get('/billings/:walletId', getBillingByWalletController);

export default router;