


import React, { Component } from "react";
import {ItemsList} from "./itemsList";
import "./style.scss";
import { Link } from "react-router-dom";
import axios from "axios";

class Landing extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
            balance: 0,
            list: null
        }
    }

    componentDidMount () {
        console.log(this.props);
        // axios.get(' https://getwalletbalance.free.beeceptor.com/wallets/8e24d64c-496b-11ec-81d3-0242ac130003')
        //     .then((response) => {
        //         const {data} = response
        //         this.setState({ balance: data.amount })
        //         console.log(response);
        //     })
        //     .catch((error) => {
        //         // console.log(error);
        //     })
        axios.get('http://localhost:8003/items?page=0&size=10')
            .then((response) => {
                const {data} = response
                this.setState({ list: data })
                // console.log(response);
            })
            .catch((error) => {
                console.log(error);
            })
    
    }
    
    render() {
        const { balance, list } = this.state
        return (
            <>
              <div className="header">
                <div className="header">
                    <Link className="logo-container" to="/">
                        <h3>MoneyPal</h3>
                    </Link>
                    <div className="options">
                    <Link className="option" to="/login">
                        {" "}
                        Sign In{" "}
                    </Link>
                    {/* ) : (
                    <Link className="option" to="/create-wallet">
                        {" "}
                        Create wallet{" "}
                    </Link>
                    ) */}
                    {/* <span className="option"> Balance: {balance} </span> */}
                    {/* <div className="option"> Sign Out</div> */}
                    <Link className="option" to="/register">
                        {" "}
                        Register{" "}
                    </Link>
                    </div>
                </div>
              </div>
              <div className="homepage">
                  {
                      list ? (<ItemsList list={list}/>) : 'Loading...'
                  }
              </div>
            </>
        )
    }
}

export default Landing;
