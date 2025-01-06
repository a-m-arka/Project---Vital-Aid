import React, { useState } from 'react'
import './Signup.scss'
import { Link, useNavigate } from 'react-router-dom'

const Signup = () => {
    const [signupErrorMessage, setSignupErrorMessage] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const handleNameChange = (e) => {
        setName(e.target.value);
    };

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePhoneChange = (e) => {
        setPhone(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleConfirmPasswordChange = (e) => {
        setConfirmPassword(e.target.value);
    };

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSignupErrorMessage("");

        if (password !== confirmPassword) {
            setSignupErrorMessage('Passwords do not match');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/vital_aid/admin/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    personName: name,
                    personEmail: email,
                    personPhone: phone,
                    loginPassword: password,
                    confirmPassword: confirmPassword,
                }),
            });

            if (response.ok) {
                const data = await response.text(); // Adjust if the API response differs
                console.log('Sign-Up Successful:', data);
                setSignupErrorMessage('');
                navigate('/');
            } else {
                const error = await response.text();
                setSignupErrorMessage(error || 'Failed to sign up');
            }
        } catch (error) {
            setSignupErrorMessage('An error occurred. Please try again.');
        }
    };

    return (
        <div className="signup-container">
            <form onSubmit={handleSubmit} className="signup-form">
                <h2>Admin Signup</h2>
                <div className="form-group">
                    <label htmlFor="name">Name</label>
                    <input
                        type="name"
                        id="name"
                        value={name}
                        onChange={handleNameChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        value={email}
                        onChange={handleEmailChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="phone">Phone</label>
                    <input
                        type="phone"
                        id="phone"
                        value={phone}
                        onChange={handlePhoneChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        value={password}
                        onChange={handlePasswordChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="confirmPassword">Confirm Password</label>
                    <input
                        type="password"
                        id="confirmPassword"
                        value={confirmPassword}
                        onChange={handleConfirmPasswordChange}
                        required
                    />
                </div>
                {signupErrorMessage && <p className="error-message">{signupErrorMessage}</p>}
                <button type="submit" className="signup-button">Sign Up</button>
                <Link to='/login' style={{ textDecoration: "none" }}>
                    <div className="go-to-login">
                        Already registered?
                    </div>
                </Link>
            </form>
        </div>
    );
}

export default Signup
