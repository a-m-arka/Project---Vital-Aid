import React, { useState } from 'react'
import './Signup.scss'
import { Link, useNavigate } from 'react-router-dom'

const Signup = () => {
    const [error, setError] = useState("");
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

    const handleSubmit = (e) => {
        e.preventDefault();
        setError("");

        if (password === confirmPassword) {
            navigate('/');
        } else {
            setError("The passwords entered do not match. Please try again.");
        }
    };

    return (
        <div className="signup-container">
            <form onSubmit={handleSubmit} className="signup-form">
                <h2>Admin Signup</h2>
                {error && <p className="error-message">{error}</p>}
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
