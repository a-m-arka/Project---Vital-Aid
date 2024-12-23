import React, { useState } from 'react'
import './ChangePass.scss'

const ChangePass = ({ password }) => {

    const [oldPass, setOldPass] = useState("");
    const [newPass, setNewPass] = useState("");
    const [confirmPass, setConfirmPass] = useState("");
    const [message, setMessage] = useState("");
    const [status, setStatus] = useState("");

    const handleOldPassChange = (e) => {
        setOldPass(e.target.value);
    };

    const handleNewPassChange = (e) => {
        setNewPass(e.target.value);
    };

    const handleConfirmPassChange = (e) => {
        setConfirmPass(e.target.value);
    };

    const handleSubmit = () => {
        if (oldPass !== password) {
            setMessage("The old password you entered is incorrect");
            setStatus("not ok");
        }
        else if (newPass !== confirmPass) {
            setMessage("The confirmation password does not match your new password");
            setStatus("not ok");
        }
        else {
            setMessage("Your password has been successfully changed");
            setStatus("ok");
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
                <p>{message}</p>
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
