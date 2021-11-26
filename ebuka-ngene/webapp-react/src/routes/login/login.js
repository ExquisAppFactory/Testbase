import React, { Component } from "react";
import { Link } from "react-router-dom";
import { Button } from "../components/button/Button";
import { FormInput } from "../components/input/FormInput";
import "./style.scss";
import axios from "axios";

class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: "",
      password: "",
      authenticated: false,
      amount: 0,
    };
  }

  handleChange = (event) => {
    const { value, name } = event.target;
    this.setState({ [name]: value });
  };

  handleSubmit = (event) => {
    const { amount } = this.state;
    // axios
    //   .get("http://localhost:8001/wallets/8e24d64c-496b-11ec-81d3-0242ac130003")
    //   .then((response) => {
    //     localStorage.setItem("amount", amount);
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
    const { email, password } = this.state;
    event.preventDefault();
    if (email || password !== "") {
      axios
        .post("https://money.free.beeceptor.com/login", {
          email,
          password,
        })
        .then((response) => {
            let userProfile = []
            const {data}=response
            userProfile = data.data.body
            console.log(userProfile);
            localStorage.setItem("user", JSON.stringify(userProfile))
            this.props.history.push("/home");
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      alert("Fill all empty fields");
    }
    this.setState({ password: "", email: "" });
  };

  render() {
    const { email, password } = this.state;
    return (
      <div className="sign-in">
        <h2 className="title">I already have an account</h2>
        <span className="sub-title">Sign in with your email and password</span>
        <FormInput
          type="email"
          name="email"
          value={email}
          onChange={this.handleChange}
          label="Email"
          required
        />

        <FormInput
          type="password"
          name="password"
          value={password}
          handleChange={this.handleChange}
          label="Password"
          required
        />
        <Button onClick={this.handleSubmit}>Sign in</Button>
        <span className="sub-title">
          Don't have an account yet?{" "}
          <Link className="option" to="/register">
            Sign up
          </Link>
        </span>
      </div>
    );
  }
}
export default Login;
