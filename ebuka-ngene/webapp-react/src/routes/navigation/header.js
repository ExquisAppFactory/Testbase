import React from 'react'
import { Link } from 'react-router-dom'
import './style.scss'

export const Header = ({ userInfo }) => {
    const { walletExist, balance, auth } = userInfo[0]
    return (
        <div className='header'>
            <Link className='logo-container' to='/'>
                <h3>MoneyPal</h3>
            </Link>
            <div className='options'>
                {
                    walletExist ? (<Link className='option' to='/fund'> Fund Wallet </Link>) : 
                    (<Link className='option' to='/create-wallet'> Create wallet </Link>)
                }

                {
                    walletExist ? (<span className='option'> Balance: {balance} </span>) : null
                }

                {
                    auth ? (<div className='option' > Sign Out</div>) :
                    (<Link className='option' to='/login'> Sign In </Link>)
                }
            </div>
            
        </div>
    )
}
