import React from 'react'
import '../../style/UserProfile/ChangePassword.scss'

const ChagePassword = () => {
    return (
        <div className='change-password-section'>
            <div className="passChange-heading">
                Change Password
            </div>
            <form>
                <div className="change-password-form-Row">
                    <div className="change-password-input-Data">
                        <input type="password" required />
                        <label>Old Password</label>
                    </div>
                </div>
                <div className="change-password-form-Row">
                    <div className="change-password-input-Data">
                        <input type="password" required />
                        <label>New Password</label>
                    </div>
                </div>
                <div className="change-password-form-Row">
                    <div className="change-password-input-Data">
                        <input type="password" required />
                        <label>Confirm Password</label>
                    </div>
                </div>
            </form>
            <div className="confirm-btn">
                Confirm
            </div>
        </div>
    )
}

export default ChagePassword
