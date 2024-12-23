import React, { useState } from 'react'
import './Update.scss'

const Update = ({ data }) => {

    const [name, setName] = useState(data.name);
    const [email, setEmail] = useState(data.email);
    const [phone, setPhone] = useState(data.phone);

    const handleNameChange = (e) => {
        setName(e.target.value);
    };

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePhoneChange = (e) => {
        setPhone(e.target.value);
    };

    return (
        <div className='update-profile'>
            <div className="title">
                <h2>Update Profile</h2>
            </div>
            <form action="" className="update-form">
                <div className="form-input">
                    <label>Name</label>
                    <input type="text" value={name} onChange={handleNameChange}/>
                </div>
                <div className="form-input">
                    <label>Email</label>
                    <input type="text" value={email} onChange={handleEmailChange}/>
                </div>
                <div className="form-input">
                    <label>Phone</label>
                    <input type="text" value={phone} onChange={handlePhoneChange}/>
                </div>
            </form>
            <div className="message">
                <p></p>
            </div>
            <div className="button">
                Update
            </div>
        </div>
    )
}

export default Update
