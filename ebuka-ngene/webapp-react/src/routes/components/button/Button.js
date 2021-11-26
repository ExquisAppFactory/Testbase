import React from 'react'
import './style.scss'

export const Button = ({ children, ...otherProps}) => {
    return (
        <button className= 'custom-button'  {...otherProps}>
            {children}
        </button>
    )
}
