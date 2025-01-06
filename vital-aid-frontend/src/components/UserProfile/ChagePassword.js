import React, { useState } from 'react'
import { useGlobalContext } from '../../context/GlobalContext'
import { useNavigate } from 'react-router-dom'
import '../../style/UserProfile/ChangePassword.scss'

const ChagePassword = () => {
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState('');

    const { setIsLoggedIn } = useGlobalContext();
    const navigate = useNavigate();

    const handleOldPassChange = (e) => {
        setOldPassword(e.target.value);
    };

    const handleNewPassChange = (e) => {
        setNewPassword(e.target.value);
    };

    const handleConfirmPassChange = (e) => {
        setConfirmPassword(e.target.value);
    };

    const handleConfirm = async () => {
        setLoading(true);
        setMessage('');
        const token = localStorage.getItem('token');

        try {
            const response = await fetch('http://localhost:8080/vital_aid/changePassword', {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    oldPassword: oldPassword,
                    newPassword: newPassword,
                    confirmPassword: confirmPassword,
                }),
            });

            if (response.ok) {
                setMessage('Profile updated successfully!');
                setIsLoggedIn(false);
                localStorage.removeItem('token');
                navigate('/login', {
                    state: { message: 'Your password has been successfully changed. Please log in again.' },
                });
            } else {
                const error = await response.text();
                setMessage(error || 'Failed to change password. Please try again.');
            }
        } catch (error) {
            console.error('Error updating profile:', error);
            setMessage(error.message);
        } finally {
            setLoading(false);
        }
    };


    return (
        <div className='change-password-section'>
            <div className="passChange-heading">
                Change Password
            </div>
            <form>
                <div className="change-password-form-Row">
                    <div className="change-password-input-Data">
                        <input type="password" value={oldPassword} onChange={handleOldPassChange} required />
                        <label>Old Password</label>
                    </div>
                </div>
                <div className="change-password-form-Row">
                    <div className="change-password-input-Data">
                        <input type="password" value={newPassword} onChange={handleNewPassChange} required />
                        <label>New Password</label>
                    </div>
                </div>
                <div className="change-password-form-Row">
                    <div className="change-password-input-Data">
                        <input type="password" value={confirmPassword} onChange={handleConfirmPassChange} required />
                        <label>Confirm Password</label>
                    </div>
                </div>
            </form>
            {message && <div className="message">{message}</div>}
            {loading ? (
                <p className="loading-message">Changing password...</p>
            ) : (
                <div className="confirm-btn" onClick={handleConfirm}>
                    Confirm
                </div>
            )}
        </div >
    )
}

export default ChagePassword
