export interface IBillingModel {
    id?: string;
    userId: string;
    invoiceNo: number;
    billAmount: number;
    status: string;
    date?: Date;
}