import React, { useState } from 'react'
import './ForgetPassForm.scss'
import Timer from '../timer/Timer'


export const EnterEmail = (props) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [email, setEmail] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    const handleEmail = (e) => {
        setEmail(e.target.value);
    };

    const handleNext = async () => {
        setErrorMessage("");
        setIsLoading(true);

        props.onSubmit();

        // try {
        //     const response = await fetch('http://localhost:8080/vital_aid/forgotPassword/admin/sendCode', {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //         body: JSON.stringify({
        //             email: email,
        //         }),
        //     });

        //     if (response.ok) {
        //         props.onSubmit();
        //         localStorage.setItem('adminForgotPassEmail', email);
        //     } else {
        //         const error = await response.text();
        //         setErrorMessage(error || 'Failed to send OTP');
        //     }
        // } catch (error) {
        //     setErrorMessage('Failed to send OTP');
        // } finally {
        //     setIsLoading(false);
        // }
    };

    return (
        <>
            <div className='forget-pass-input'>
                <label>
                    Enter Your Email
                </label>
                <input type="email" value={email} onChange={handleEmail} />
            </div>
            <div className="error-message">
                {errorMessage}
            </div>
            {isLoading ? (
                <div className="loading-message" disabled>
                    Sending OTP to your email...
                </div>
            ) : (
                <button className="forget-pass-btn" onClick={handleNext}>
                    Get OTP
                </button>
            )}
        </>
    );
};

export const EnterOtp = (props) => {
    const [errorMessage, setErrorMessage] = useState("");
    const [timeOver, setTimeOver] = useState(false);
    const [otp, setOtp] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const [loadingMessage, setLoadingMessage] = useState("");

    const handleTimerComplete = () => {
        setTimeOver(true);
        setErrorMessage("Time over");
    };

    const handleOtp = (e) => {
        setOtp(e.target.value);
    };

    const handleNext = async () => {
        if (timeOver) {
            setErrorMessage("Time is over");
            setTimeOver(true);
        } else {
            setIsLoading(true);
            setLoadingMessage("Verifying OTP...");
            setErrorMessage("");
            setTimeOver(false);

            props.onSubmit();

            // try {
            //     const response = await fetch('http://localhost:8080/vital_aid/forgotPassword/admin/validateOtp', {
            //         method: 'POST',
            //         headers: {
            //             'Content-Type': 'application/json',
            //         },
            //         body: JSON.stringify({
            //             otp: otp,
            //         }),
            //     });

            //     if (response.ok) {
            //         props.onSubmit();
            //     } else {
            //         const error = await response.text();
            //         setErrorMessage(error || 'Failed to verify OTP');
            //     }
            // } catch (error) {
            //     setErrorMessage('Failed to verify OTP');
            // } finally {
            //     setIsLoading(false);
            //     setLoadingMessage("");
            // }
        }
    };

    const handleGetNewOtp = async () => {
        setIsLoading(true);
        setLoadingMessage("Sending new OTP...");

        // try {
        //     const response = await fetch('http://localhost:8080/vital_aid/forgotPassword/admin/sendCode', {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //         body: JSON.stringify({
        //             email: localStorage.getItem('adminForgotPassEmail'),
        //         }),
        //     });

        //     if (response.ok) {
        //         setTimeOver(false);
        //         setErrorMessage("");
        //         setOtp("");
        //     } else {
        //         const error = await response.text();
        //         setErrorMessage(error || 'Failed to send OTP');
        //     }
        // } catch (error) {
        //     setErrorMessage('Failed to send OTP');
        // } finally {
        //     setIsLoading(false);
        //     setLoadingMessage("");
        // }
    };

    return (
        <>
            <div className='forget-pass-input'>
                <label>
                    Enter OTP
                </label>
                <input type="text" value={otp} onChange={handleOtp} />
            </div>
            {!timeOver && (
                <Timer startTime={120} onComplete={handleTimerComplete} />
            )}
            <div className="error-message">
                {errorMessage}
            </div>
            {isLoading ? (
                <div className="loading-message">{loadingMessage}</div>
            ) : (
                <>
                    {!timeOver && (
                        <button className="forget-pass-btn" onClick={handleNext}>
                            Verify OTP
                        </button>
                    )}
                    {timeOver && (
                        <button className="forget-pass-btn" onClick={handleGetNewOtp}>
                            Get New OTP
                        </button>
                    )}
                </>
            )}
        </>
    );
};


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

    const handleNext = async () => {
        setErrorMessage("");

        props.onSubmit();

        // try {
        //     const response = await fetch('http://localhost:8080/vital_aid/forgotPassword/admin/resetPassword', {
        //         method: 'POST',
        //         headers: {
        //             'Content-Type': 'application/json',
        //         },
        //         body: JSON.stringify({
        //             newPassword: newPass,
        //             confirmPassword: confirmPass,
        //         }),
        //     });

        //     if (response.ok) {
        //         props.onSubmit();
        //     } else {
        //         const error = await response.text();
        //         setErrorMessage(error || 'Failed to reset password');
        //     }
        // }catch (error) {
        //     setErrorMessage('Failed to reset password');
        // }
    };

    return (
        <>
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
            <button className="forget-pass-btn" onClick={handleNext}>
                Next
            </button>
        </>
    )
}

export const Success = () => {
    return (
        <div className='success-message'>
            Your password is successfully restored.
        </div>
    )
}