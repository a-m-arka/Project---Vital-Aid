import React, { useState } from 'react'
import './Login.scss'
import { Link, useNavigate } from 'react-router-dom'

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    setError("");

    if (email === "arka@admin.com" && password === "1234") {
      navigate('/home');
    } else {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        <h2>Admin Login</h2>
        <Link to='/forgetpassword' style={{ textDecoration: "none" }}>
          <div className="go-to-forgetpass">
            Forgot Password?
          </div>
        </Link>
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
          <label htmlFor="password">Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
            required
          />
        </div>
        {error && <p className="error-message">{error}</p>}
        <button type="submit" className="login-button">Login</button>
        <Link to='/signup' style={{ textDecoration: "none" }}>
          <div className="go-to-signup">
            Register as an admin
          </div>
        </Link>
      </form>
    </div>
  );
}

export default Login
