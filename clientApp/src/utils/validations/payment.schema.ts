import * as Yup from 'yup';

export const PaymentSchema = Yup.object().shape({
    email: Yup.string().email().required('Email must be provided'),
    accountNumber: Yup.string().required('Account number must be provided'),
    bankCode: Yup.string().required('Bank name must be provided'),
    amount: Yup.string()
        .required('Amount must be provided')
})