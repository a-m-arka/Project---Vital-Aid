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
                    {data.personName}
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
                    {data.personEmail}
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
                    {data.personPhone}
                </div>
            </div>
        </div>
    )
}

export default View
