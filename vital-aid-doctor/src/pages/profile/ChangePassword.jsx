import React from 'react'
import './ChangePassword.scss'

const ChangePassword = () => {
    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle password change logic here
    };

    return (
        <div className='changePasswordContainer'>
            <h2>Change Password</h2>
            <form onSubmit={handleSubmit}>
                <div className="formInput">
                    <label htmlFor="currentPassword">Current Password</label>
                    <input
                        type="password"
                        id="currentPassword"
                        name="currentPassword"
                        required
                    />
                </div>
                <div className="formInput">
                    <label htmlFor="newPassword">New Password</label>
                    <input
                        type="password"
                        id="newPassword"
                        name="newPassword"
                        required
                    />
                </div>
                <div className="formInput">
                    <label htmlFor="confirmPassword">Confirm New Password</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        name="confirmPassword"
                        required
                    />
                </div>
                <button className='submit-btn' type="submit">Save Changes</button>
            </form>
        </div>
    )
}

export default ChangePassword
