import { Schema, model } from 'mongoose';
import { IBillingModel } from './billing-response-model';

const billingSchema = new Schema<IBillingModel>({
  userId: {
    type: String,
    required: true
  },
  billAmount: {
      type: Number,
      min: 50,
      required: true,
      max: 500000
  },
  invoiceNo: {
      type: Number,
      required: true
  },
  status: {
      type: String,
      required: true
  },
})

export const BillingSchema = model<IBillingModel>('Billing', billingSchema);