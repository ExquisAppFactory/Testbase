import React from 'react'

export const ListComponent = ({ name, amount }) => {
    return (
        <div className='menu-item'>
            <div className='content'>
                <h1 className='title'>{name}</h1>
                <span className='subtitle'>Buy</span>
            </div>
        </div>
    )
}
