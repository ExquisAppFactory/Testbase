import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { Button } from '../components/button/Button'
import { FormInput } from '../components/input/FormInput'
import axios from 'axios'
import '../login/style.scss'

class Register extends Component {
    constructor(props) {
        super(props)
    
        this.state = {
             email:'',
             password:'',
             first:'',
             last:'',
             phone:''
        }
    }

    handleChange = (event) => {
        const { value, name } = event.target
        this.setState({ [name]: value }, ()=>console.log(this.state))
        ;
    }

    handleSubmit = (event) => {
        const { email, password, first, last, phone} = this.state
        event.preventDefault()
        if (email || password || first || last !== '') {
            // axios.post('http://localhost:8000/register', {
            axios.post('https://money.free.beeceptor.com/register', { 
                email,
                password,
                firstName:first,
                lastName:last,
                contactPhone: phone
            })
            .then( (response) => {
                alert('account created')
                this.props.history.push('/login')
            })
            .catch((error) => {
                console.log(error);
            });
            
        } else {
            alert('Field all empty fields')
            
        }
        this.setState({ password:'', email:'', first:'', last:'', phone:'' })
        
    }

    
    render() {
        const { email, password, first, last, phone} = this.state
        return (
            <div className='sign-in'>
                <h2 className='title'>Register</h2>
                <FormInput 
                    type='text' 
                    name='first' 
                    value={first} 
                    onChange={this.handleChange}
                    label='Name'
                    required
                />

                <FormInput 
                    type='text' 
                    name='last' 
                    value={last} 
                    onChange={this.handleChange}
                    label='Surname'
                    required
                />

                <FormInput 
                    type='email' 
                    name='email' 
                    value={email} 
                    onChange={this.handleChange}
                    label='Email'
                    required
                />

                <FormInput 
                    type='number' 
                    name='phone' 
                    value={phone} 
                    onChange={this.handleChange}
                    label='Phone Number'
                    required
                />

                <FormInput 
                    type='password' 
                    name='password' 
                    value={password} 
                    handleChange={this.handleChange}
                    label='Password'
                    required
                />
                <Button onClick={this.handleSubmit}>Create account</Button>
                <span className='sub-title'>I have an account? <Link className='option' to='/login'>Sign in</Link></span>
            </div>
        )
    }
}
export default Register