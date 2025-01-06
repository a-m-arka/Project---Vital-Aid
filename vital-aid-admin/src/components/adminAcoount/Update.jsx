import React, { useState } from 'react'
import './Update.scss'
import { useNavigate } from 'react-router-dom'
import { useGlobalContext } from '../../context/GlobalContext';

const Update = ({ data }) => {

    const [name, setName] = useState(data.personName);
    const [email, setEmail] = useState(data.personEmail);
    const [phone, setPhone] = useState(data.personPhone);
    const [errorMessage, setErrorMessage] = useState('');
    const { setIsLoggedIn } = useGlobalContext();
    const navigate = useNavigate();

    const handleNameChange = (e) => {
        setName(e.target.value);
    };

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePhoneChange = (e) => {
        setPhone(e.target.value);
    };

    const handleUpdateClick = async () => {
        setErrorMessage('');
        const token = localStorage.getItem('adminToken');
        const url = 'http://localhost:8080/vital_aid/admin/updateAdminProfile';

        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    personName: name,
                    personEmail: email,
                    personPhone: phone
                }),
            });

            if (!response.ok) {
                // throw new Error(`HTTP error! status: ${response.status}`);
                const error = await response.text();
                setErrorMessage(error || 'Failed to update');
            }
            else {
                // const data = await response.json();
                // console.log('Success: ', data);
                // window.location.reload();
                setIsLoggedIn(false);
                localStorage.removeItem('adminToken');
                navigate('/login', {
                    state: { message: 'Your profile has been successfully updated. Please log in again.' },
                });
            }

        } catch (err) {
            setErrorMessage(err.message);
            console.error('Error fetching profile:', err.message);
        }
    };

    return (
        <div className='update-profile'>
            <div className="title">
                <h2>Update Profile</h2>
            </div>
            <form action="" className="update-form">
                <div className="form-input">
                    <label>Name</label>
                    <input type="text" value={name} onChange={handleNameChange} />
                </div>
                <div className="form-input">
                    <label>Email</label>
                    <input type="text" value={email} onChange={handleEmailChange} />
                </div>
                <div className="form-input">
                    <label>Phone</label>
                    <input type="text" value={phone} onChange={handlePhoneChange} />
                </div>
            </form>
            <div className="message">
                <p>{errorMessage}</p>
            </div>
            <div className="button" onClick={handleUpdateClick}>
                Update
            </div>
        </div>
    )
}

export default Update
