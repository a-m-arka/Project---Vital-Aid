import React from 'react'
import './View.scss'

const View = ({ data }) => {
    return (
        <div className='view'>
            <div className="data">
                <div className="key">
                    Name
                </div>
                <div className="colon">
                    :
                </div>
                <div className="value">
                    {data.name}
                </div>
            </div>
            <div className="data">
                <div className="key">
                    Id
                </div>
                <div className="colon">
                    :
                </div>
                <div className="value">
                    {data.id}
                </div>
            </div>
            <div className="data">
                <div className="key">
                    Email
                </div>
                <div className="colon">
                    :
                </div>
                <div className="value">
                    {data.email}
                </div>
            </div>
            <div className="data">
                <div className="key">
                    Phone
                </div>
                <div className="colon">
                    :
                </div>
                <div className="value">
                    {data.phone}
                </div>
            </div>
        </div>
    )
}

export default View
