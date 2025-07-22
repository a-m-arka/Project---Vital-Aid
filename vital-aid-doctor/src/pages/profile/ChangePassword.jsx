import React, { useState } from 'react';
import './ChangePassword.scss';
import { useNavigate } from 'react-router-dom';
import { useGlobalContext } from '../../context/GlobalContext';

const ChangePassword = () => {
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState('');

    const { setIsLoggedIn } = useGlobalContext();
    const navigate = useNavigate();

    const handleOldPassChange = (e) => setOldPassword(e.target.value);
    const handleNewPassChange = (e) => setNewPassword(e.target.value);
    const handleConfirmPassChange = (e) => setConfirmPassword(e.target.value);

    const handleConfirm = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMessage('');
        const token = localStorage.getItem('doctorToken');

        try {
            const response = await fetch('http://localhost:8080/vital_aid/doctors/changePassword', {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    oldPassword,
                    newPassword,
                    confirmPassword,
                }),
            });

            if (response.ok) {
                setMessage('Password changed successfully!');
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
            console.error('Error changing password:', error);
            setMessage(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className='changePasswordContainer'>
            <h2>Change Password</h2>
            <form onSubmit={handleConfirm}>
                <div className="formInput">
                    <label htmlFor="currentPassword">Current Password</label>
                    <input
                        type="password"
                        id="currentPassword"
                        name="currentPassword"
                        value={oldPassword}
                        onChange={handleOldPassChange}
                        required
                    />
                </div>
                <div className="formInput">
                    <label htmlFor="newPassword">New Password</label>
                    <input
                        type="password"
                        id="newPassword"
                        name="newPassword"
                        value={newPassword}
                        onChange={handleNewPassChange}
                        required
                    />
                </div>
                <div className="formInput">
                    <label htmlFor="confirmPassword">Confirm New Password</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        value={confirmPassword}
                        onChange={handleConfirmPassChange}
                        required
                    />
                </div>
                <button className='submit-btn' type="submit" disabled={loading}>
                    {loading ? 'Updating...' : 'Save Changes'}
                </button>
                {message && <p className="message">{message}</p>}
            </form>
        </div>
    );
};

export default ChangePassword;
