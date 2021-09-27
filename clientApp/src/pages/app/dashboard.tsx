import React from 'react';
import { useState } from 'react';
import { Redirect } from 'react-router-dom';
import { Container, Row } from 'reactstrap';
import { AuthenticatedUser } from '../../models';
import { extract } from '../../utils';
import { CreditWalletModal, TransferFormModal } from './components';

export const DashboardPage = () => {
    const [userDetails] = useState(extract<AuthenticatedUser>('AUTH_USER'));
    const [isTransferring, setTransferring] = useState(false);

    if (!userDetails) {
        return <Redirect to="/login" />
    }

    if (isTransferring) {
        return <CreditWalletModal onClose={() => setTransferring(false)} />
    }

    return (
        <Container style={{ maxWidth: 450, margin: '4em auto' }}>
            <Row className="mb-4">
                <h2 className="col-lg-4 mx-auto text-primary">MoneyPal</h2>
            </Row>

            <Row>
                <div className="col-lg-12 mb-3 text-center">Hello, <strong className="capitalize">{userDetails.firstName}&nbsp;{userDetails.lastName}</strong></div>

                <div className="row mb-4">
                    <span className="col-6 bold">Wallet Balance:</span>
                    <span className="col-4">&#36;10</span>
                </div>
            </Row>

            <Row>
                <div className="col-lg-6 mx-auto mb-4">
                    <button className="btn btn-success" onClick={() => setTransferring(true)}>Fund Wallet</button>
                </div>
            </Row>

            <Row>
                <div className="col-12 text-primary bold-6 m-3">Transaction History</div>

                <div className="form-error capitalize centered">No Transactions Yet</div>
                {/* <Table>
                    <thead>
                        <th>S/N</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Amount</th>
                    </thead>
                </Table> */}
            </Row>
        </Container>
    )
}