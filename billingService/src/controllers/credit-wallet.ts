import express from 'express';
import { CreditWalletModel } from '../models';

type Request = express.Request<never, never, CreditWalletModel>;

export const CreditWalletController = (request: Request, response: express.Response) => {
    const { userId, amount } = request.body;

    
}