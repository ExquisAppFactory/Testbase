import { Form, Modal, ModalBody } from "reactstrap";
import React, { useEffect, useState } from 'react';
import { X } from 'react-feather';
import { v4 } from 'uuid';
import { PaystackButton } from 'react-paystack';
import { ErrorMessage, Field, Formik } from "formik";
import { extract, PaymentSchema } from "../../../utils";
import axios from "axios";
import { Redirect } from "react-router-dom";
import { AuthenticatedUser } from "../../../models";

const styles = {
    width: '100%',
    margin: '2em auto 0',
    maxWidth: 700
}

interface Props {
    onClose: () => void;
}

export const TransferFormModal: React.FC<Props> = ({ onClose }) => {
    const [alert, setAlert] = useState<{ success: boolean; message: string } | null>(null);
    const [banks, setBanks] = useState<{ name: string; code: string; }[]>([]);
    const [users, setUsers] = useState<AuthenticatedUser[]>([]);

    const user = extract<{token: string}>('AUTH_USER')!;
    
    useEffect(() => {
        axios({
            url: `${process.env.REACT_APP_PAYMENT_BASE_URL}payments/banks`,
            method: 'GET',
        })
        .then((res) => res.data.data)
        .then((data) => {
            setBanks(data);
        })
        .catch((err) => console.log({ err }))
    }, []);

    useEffect(() => {
        axios({
            method: 'GET',
            url: `${process.env.REACT_APP_AUTH_BASE_URL}users`,
            headers: {
                authorization: `Bearer ${(user as AuthenticatedUser)?.token}`
            }
        }).then((response) => response.data.data)
        .then((data) => {
            setUsers(data);
        })
        .catch((error) => console.log({ error }))
    }, []);

    if (!user) {
        return <Redirect to="/" />
    }

    return (
        <Modal isOpen styles={styles}>
            <ModalBody>
                <div className="p-3">
                    <div style={{ width: '2em', marginLeft: 'auto' }} onClick={onClose}>
                        <i><X /></i>
                    </div>

                    <h3 className="text-center">Send Money</h3>

                    <div>
                    <Formik
                        initialValues={{
                            accountNumber: '',
                            email: '',
                            amount: '',
                            bankCode: '',
                        }}
                        validateOnMount
                        validationSchema={PaymentSchema}
                        onSubmit={(model) => {
                            console.log({ model });
                        }}
                    >
                        {({ handleSubmit, values, isValid, setFieldError }) => {
                            const onSuccess = (reference: {reference: string}) => {
                                if (values && +values.amount < 50) {
                                    setFieldError('amount', 'Amount must be at least 50');
                                    return;
                                }
                                axios({
                                    method: 'POST',
                                    url: `${process.env.REACT_APP_PAYMENT_BASE_URL}payments/verify`,
                                    data: {
                                        ...values,
                                        reference: reference.reference
                                    },
                                    headers: {
                                        authorization: `Bearer ${user.token}`
                                    }
                                }).then(({ data }) => {
                                    setAlert({ success: true, message: data.message });
                                    setTimeout(() => setAlert(null), 4000);
                                })
                                .catch(({ response }) => {
                                    setAlert({ success: false, message: response?.data?.message });
                                    setTimeout(() => setAlert(null), 4000);
                                }).finally(() => setTimeout(() => onClose(), 6000));
                            };
                            const handleClose = () => console.log('close');
                            const componentProps = () => ({
                                reference: v4(),
                                email: values.email,
                                text: 'Proceed to Pay',
                                amount: +values.amount * 100,
                                onSuccess, onClose: handleClose,
                                publicKey: 'pk_test_86a34dfe9bb54afb6fd8d566918c1e83ed0c440e',
                            });

                            return (
                                <>
                                <Form className="p-4" onSubmit={handleSubmit}>
                                <div className="row">
                                    <div className="mb-2">
                                        <label htmlFor="toEmail" className="form-label">Recipient Email</label>
                                        <Field as="select" required className="form-control rounded" id="email" name="email">
                                                <option
                                                value="jdnvfknv fbvufhfe"
                                                disabled>Select User</option>
                                                {users?.map(({ username, id }) => (
                                                    <option key={username} value={id}>{username}</option>
                                                ))}
                                            </Field>
                                        <div className="form-error">
                                            <ErrorMessage name="toEmail" />
                                        </div>
                                    </div>

                                    <div className="mb-2">
                                        <label htmlFor="accountNumber" className="form-label">Recipient Account Number</label>
                                        <Field required className="form-control rounded" id="accountNumber" name="accountNumber" />
                                        <div className="form-error">
                                            <ErrorMessage name="accountNumber" />
                                        </div>
                                    </div>

                                    <div className="mb-2">
                                        <label htmlFor="bankCode" className="form-label">Recipient Bank</label>
                                            <Field as="select" required className="form-control rounded" id="bankCode" name="bankCode">
                                                <option
                                                value="kmvklfmvojur8"
                                                disabled>Select Bank</option>
                                                {banks?.map(({ name, code }) => (
                                                    <option key={code} value={code}>{name}</option>
                                                ))}
                                            </Field>
                                        <div className="form-error">
                                            <ErrorMessage name="bankCode" />
                                        </div>
                                    </div>
                                    
                                    <div className="mb-3">
                                        <label htmlFor="amount" className="form-label">Amount</label>
                                            <Field required type="number" className="form-control rounded" id="amount" name="amount" />
                                        <div className="form-error">
                                            <ErrorMessage name="amount" />
                                        </div>
                                    </div>
                                </div>
                            </Form>

                            {alert && <div className={`${alert.success ? 'success success-box' : 'form-error'} mb-2 centered`}>
                                {alert.message}
                            </div>}

                                <div className="row mt-3">
                                    {isValid ? <div className="col-6 mx-auto">
                                        <PaystackButton className="btn btn-success" {...componentProps()} />
                                    </div> : null}
                                </div>
                                </>
                            )
                        }}
                    </Formik>
                    </div>
                </div>
            </ModalBody>
        </Modal>
    )
}