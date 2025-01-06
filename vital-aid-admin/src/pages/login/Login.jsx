import React, { useState } from 'react'
import './Login.scss'
import { Link, useNavigate, useLocation } from 'react-router-dom'
import { useGlobalContext } from '../../context/GlobalContext'

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loginErrorMessage, setLoginErrorMessage] = useState("");
  const { setIsLoggedIn } = useGlobalContext();
  const navigate = useNavigate();
  const location = useLocation();
  const passedMessage = location.state?.message;
  

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleSuccessfullLogin = () => {
    setIsLoggedIn(true);
    navigate('/home');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoginErrorMessage("");

    try {
      const response = await fetch('http://localhost:8080/vital_aid/admin/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          personEmail: email,
          loginPassword: password,
        }),
      });

      if (response.ok) {

        const data = await response.json();
        console.log('Login successful');
        setLoginErrorMessage('');
        localStorage.setItem('adminToken', data.token);
        handleSuccessfullLogin();

        // const token = localStorage.getItem('token');
        // console.log('Token:', token);

      } else {
        const error = await response.text();
        setLoginErrorMessage(error || 'Failed to log in');
      }
    } catch (error) {
      setLoginErrorMessage('An error occurred. Please try again.');
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} className="login-form">
        {passedMessage && <p className="passed-message">{passedMessage}</p>}
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
        {loginErrorMessage && <p className="error-message">{loginErrorMessage}</p>}
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
