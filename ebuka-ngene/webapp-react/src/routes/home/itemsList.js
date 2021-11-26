import React from 'react'
import { ListComponent } from './listComponent'


export const ItemsList = ({ list }) => {
    return (
        <div className='menu-wrapper'>
            { list.map(({item_name, id, item_price})=>{
                return <ListComponent key={id} name={item_name} amount={item_price} />
            }) }
        </div>
    )
}

