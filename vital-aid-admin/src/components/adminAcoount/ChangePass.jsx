import React, { useState } from 'react'
import './ChangePass.scss'
import { useNavigate } from 'react-router-dom'
import { useGlobalContext } from '../../context/GlobalContext';

const ChangePass = () => {

    const [oldPass, setOldPass] = useState("");
    const [newPass, setNewPass] = useState("");
    const [confirmPass, setConfirmPass] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const [status, setStatus] = useState("");
    const { setIsLoggedIn } = useGlobalContext();
    const navigate = useNavigate();

    const handleOldPassChange = (e) => {
        setOldPass(e.target.value);
    };

    const handleNewPassChange = (e) => {
        setNewPass(e.target.value);
    };

    const handleConfirmPassChange = (e) => {
        setConfirmPass(e.target.value);
    };

    const handleSubmit = async () => {
        setErrorMessage('');
        const token = localStorage.getItem('adminToken');
        const url = 'http://localhost:8080/vital_aid/admin/changePassword';

        try {
            const response = await fetch(url, {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    oldPassword: oldPass,
                    newPassword: newPass,
                    confirmPassword: confirmPass
                }),
            });

            if (!response.ok) {
                // throw new Error(`HTTP error! status: ${response.status}`);
                const error = await response.text();
                setErrorMessage(error || 'Failed to change password');
            }
            else {
                // const data = await response.json();
                // console.log('Success: ', data);
                // window.location.reload();
                setIsLoggedIn(false);
                localStorage.removeItem('adminToken');
                navigate('/login', {
                    state: { message: 'Your password has been successfully changed. Please log in again.' },
                });
            }

        } catch (err) {
            setErrorMessage(err.message);
            console.error('Error fetching profile:', err.message);
        }
    };

    return (
        <div className='change-pass'>
            <div className="title">
                <h2>Change Password</h2>
            </div>
            <form action="" className="change-pass-form">
                <div className="form-input">
                    <label>Old Password</label>
                    <input type="password" value={oldPass} onChange={handleOldPassChange} />
                </div>
                <div className="form-input">
                    <label>New Password</label>
                    <input type="password" value={newPass} onChange={handleNewPassChange} />
                </div>
                <div className="form-input">
                    <label>Confirm Password</label>
                    <input type="password" value={confirmPass} onChange={handleConfirmPassChange} />
                </div>
            </form>
            <div
                className="message"
                style={{ color: status === "ok" ? "green" : "red" }}
            >
                <p>{errorMessage}</p>
            </div>
            {status !== "ok" && (
                <div className="button" onClick={handleSubmit}>
                    Confirm
                </div>
            )}
        </div>
    )
}

export default ChangePass
