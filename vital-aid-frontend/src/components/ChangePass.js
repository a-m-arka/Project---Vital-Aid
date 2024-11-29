import React from 'react'

export default function ChangePass({onCancel}) {
    return (
        <div className="change-password-section pop-out">
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
                <div className="change-password-form-Row confirm-cancel-btn">
                    <div className="change-password-input-Data">
                        <div className="inner"></div>
                        <input type="submit" value="Confirm" />
                    </div>
                </div>
                <div className="change-password-form-Row confirm-cancel-btn">
                    <div className="change-password-input-Data confirm-cancel-btn">
                        <div className="inner"></div>
                        <input type="button" value="Cancel" onClick={onCancel} />
                    </div>
                </div>
            </form>
        </div>

    )
}
