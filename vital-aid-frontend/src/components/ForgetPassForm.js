import React, { useState } from 'react'
import '../style/ForgetPassForm.css'
import Timer from './Timer'

export const EnterEmail = (props) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [email, setEmail] = useState("");

    const handleEmail = (e) => {
        setEmail(e.target.value);
    };

    const handleNext = () => {
        if (email === props.email) {
            props.onSubmit();
        }
        else {
            setErrorMessage("Invalid email address");
        }
    };

    return (
        <div className='forget-pass-input-container'>
            <div className='forget-pass-input'>
                <label>
                    Enter Your Email
                </label>
                <input type="email" value={email} onChange={handleEmail} />
            </div>
            <div className="error-message">
                {errorMessage}
            </div>
            <button className="btn" onClick={handleNext}>
                Get OTP
            </button>
        </div>
    )
}

export const EnterOtp = (props) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [timeOver, setTimeOver] = useState(false);
    const [otp, setOtp] = useState("");

    const handleTimerComplete = () => {
        setTimeOver(true);
        setErrorMessage("Time over");
    };

    const handleOtp = (e) => {
        setOtp(e.target.value);
    };

    const handleNext = () => {
        if (timeOver || otp !== props.otp) {
            setErrorMessage("OTP does not match");
            setTimeOver(true);
        }
        else {
            props.onSubmit();
        }
    };

    const handleGetNewOtp = () => {
        setTimeOver(false);
        setErrorMessage("");
        setOtp("");
    };

    return (
        <div className='forget-pass-input-container'>
            <div className='forget-pass-input'>
                <label>
                    Enter OTP
                </label>
                <input type="text" value={otp} onChange={handleOtp} />
            </div>
            {!timeOver && (
                <Timer startTime={5} onComplete={handleTimerComplete} />
            )}
            <div className="error-message">
                {errorMessage}
            </div>
            {!timeOver && (
                <button className="btn" onClick={handleNext}>
                    Verify OTP
                </button>
            )}
            {timeOver && (
                <button className="btn" onClick={handleGetNewOtp}>
                    Get New OTP
                </button>
            )}
        </div>
    )
}

export const NewPassword = (props) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [newPass, setNewPass] = useState("");
    const [confirmPass, setConfirmPass] = useState("");

    const handleNewPass = (e) => {
        setNewPass(e.target.value);
    };

    const handleConfirmPass = (e) => {
        setConfirmPass(e.target.value);
    };

    const handleNext = () => {
        if (newPass === confirmPass) {
            props.onSubmit();
        }
        else {
            setErrorMessage("Passwords do not match");
        }
    };

    return (
        <div className='forget-pass-input-container'>
            <div className='forget-pass-input'>
                <label>
                    Enter New Password
                </label>
                <input type="password" value={newPass} onChange={handleNewPass} />
                <label>
                    Confirm New Password
                </label>
                <input type="password" value={confirmPass} onChange={handleConfirmPass} />
            </div>
            <div className="error-message">
                {errorMessage}
            </div>
            <button className="btn" onClick={handleNext}>
                Next
            </button>
        </div>
    )
}

export const Success = () => {
    return (
        <div className='success-message'>
            Your password is successfully restored.
        </div>
    )
}