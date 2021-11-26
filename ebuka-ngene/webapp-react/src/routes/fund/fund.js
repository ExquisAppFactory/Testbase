import React, { Component } from 'react'
import { Button } from '../components/button/Button'
import { FormInput } from '../components/input/FormInput'
import { FlutterWaveButton, closePaymentModal } from 'flutterwave-react-v3';

import '../login/style.scss'

export default class Fund extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
             amount:'',
             user: ''
        }
    }

    handleChange = (event) => {
        const { value, name } = event.target
        this.setState({ [name]: value }, ()=>console.log(this.state))
        ;
    }

    componentDidMount () {
        let storedUser = JSON.parse(localStorage.getItem("user"))
        this.setState({user: storedUser})
    }
    
    render() {
        console.log(this.state.user);
        const { user } = this.state
        const { email, firstName, lastName, phone, id } = user
        const { amount } = this.state
        const config = {
            public_key: 'FLWPUBK_TEST-5a4309c1b7dbf700e63dc4d676f438ec-X',
            tx_ref: Date.now(),
            redirect_url:"http://localhost:8002/payment/rave/response",
            amount: amount,
            currency: 'NGN',
            payment_options: 'card, mobilemoney, ussd',
            customer: {
                email: user ? email : '',
                phonenumber: user ? phone : '',
                name: user ? `${firstName} ${lastName}`: '',
                id: user ? id : ''
            },
            customizations: {
            title: 'Fund Wallet',
            description: 'Payment for wallet funds',
            logo: 'https://st2.depositphotos.com/4403291/7418/v/450/depositphotos_74189661-stock-illustration-online-shop-log.jpg',
            },
        };

        const flutterConfig = {
            ...config,
            text: 'Fund Wallet',
            callback: (response) => {
            console.log(response);
            alert(response.status)
            closePaymentModal() // this will close the modal programmatically
            },
            onClose: () => {},
        };

        return (
            <div className='sign-in'>
                <h2 className='title'>Fund wallet</h2>
                <span className='sub-title'>Sign in with your email and password</span>
                <FormInput 
                    type='number'
                    min='1'
                    name='amount'
                    value={amount}
                    onChange={this.handleChange}
                    label='Amount'
                    required
                />
                {/* <Button onClick={this.handleSubmit}>Fund Wallet</Button> */}
                <FlutterWaveButton {...flutterConfig} />

            </div>
        )
    }
}

