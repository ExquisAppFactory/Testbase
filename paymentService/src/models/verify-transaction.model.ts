export interface VerifyPaymentModel {
    reference: string;
    email: string;
    amount: number;
    accountNumber?: string; 
    bankCode?: string;
}