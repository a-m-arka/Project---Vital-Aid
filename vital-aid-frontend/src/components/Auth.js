import React, { useState, useRef } from 'react';
import '../style/Auth.css';
import registerImage from '../Images/register.svg';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { useGlobalContext } from '../context/GlobalContext';

function Auth() {
  const loginBtnRef = useRef();
  const signupBtnRef = useRef();
  const authContainerRef = useRef();
  const location = useLocation();
  const passedMessage = location.state?.message;

  const [isSignupMode, setIsSignupMode] = useState(false);
  const [loginErrorMessage, setLoginErrorMessage] = useState('');
  const [signupErrorMessage, setSignupErrorMessage] = useState('');
  const { setIsLoggedIn } = useGlobalContext();

  const goToSignUp = () => {
    if (authContainerRef.current) {
      authContainerRef.current.classList.add('sign-up-mode');
    }
    setIsSignupMode(true);
  };

  const goToLogin = () => {
    if (authContainerRef.current) {
      authContainerRef.current.classList.remove('sign-up-mode');
    }
    setIsSignupMode(false);
  };

  const navigate = useNavigate();

  const handleSuccessfullLogin = () => {
    setIsLoggedIn(true);
    navigate('/');
  };

  const handleLogin = async (e) => {
    e.preventDefault();

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    try {
      const response = await fetch('http://localhost:8080/vital_aid/user/login', {
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
        localStorage.setItem('token', data.token);
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


  const handleSignUp = async (e) => {
    e.preventDefault();

    const name = document.getElementById('name').value;
    const email = document.getElementById('signup-email').value;
    const phone = document.getElementById('phone').value;
    const password = document.getElementById('signup-password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    if (password !== confirmPassword) {
      setSignupErrorMessage('Passwords do not match');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/vital_aid/user/register', {
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
        // alert('Sign-up successful');
        goToLogin(); // Switch to login form after successful registration
      } else {
        const error = await response.text();
        setSignupErrorMessage(error || 'Failed to sign up');
      }
    } catch (error) {
      setSignupErrorMessage('An error occurred. Please try again.');
    }
  };

  return (
    <div className="rout-container">
      <div className="auth-container" ref={authContainerRef}>
        <div className="forms-container">
          <div className="signin-signup">
            <form className="sign-in-form" onSubmit={handleLogin}>
              {passedMessage && <p className="passed-message">{passedMessage}</p>}
              <h2 className="auth-title">Log In</h2>
              <div className="auth-input-container">
                <input type="email" className="auth-input-field" placeholder="Email" id="login-email" />
                <label htmlFor="login-email" className="auth-input-label">Email</label>
                <i className="input-icon fa fa-envelope"></i>
              </div>
              <div className="auth-input-container">
                <input type="password" className="auth-input-field" placeholder="Password" id="login-password" />
                <label htmlFor="login-password" className="auth-input-label">Password</label>
                <i className="input-icon fa fa-lock"></i>
              </div>
              {loginErrorMessage && <p style={{ color: 'red', textAlign: 'center' }}>{loginErrorMessage}</p>}
              <input type="submit" value="Log In" className="btn solid auth-submit-btn" />
              <Link to="/forgotpassword" style={{ textDecoration: 'none' }}>
                <div className="forget-pass">Forgot Password?</div>
              </Link>
            </form>
            <form className="sign-up-form" onSubmit={handleSignUp}>
              <h2 className="auth-title">Sign Up</h2>
              <div className="auth-input-container">
                <input type="text" className="auth-input-field" placeholder="User name" id="name" />
                <label htmlFor="name" className="auth-input-label">User name</label>
                <i className="input-icon fa fa-user"></i>
              </div>
              <div className="auth-input-container">
                <input type="email" className="auth-input-field" placeholder="Email" id="signup-email" />
                <label htmlFor="signup-email" className="auth-input-label">Email</label>
                <i className="input-icon fa fa-envelope"></i>
              </div>
              <div className="auth-input-container">
                <input type="number" className="auth-input-field" placeholder="Phone Number" id="phone" />
                <label htmlFor="phone" className="auth-input-label">Phone Number</label>
                <i className="input-icon fa-solid fa-phone-flip"></i>
              </div>
              <div className="auth-input-container">
                <input type="password" className="auth-input-field" placeholder="Password" id="signup-password" />
                <label htmlFor="signup-password" className="auth-input-label">Password</label>
                <i className="input-icon fa fa-lock"></i>
              </div>
              <div className="auth-input-container">
                <input type="password" className="auth-input-field" placeholder="Confirm Password" id="confirm-password" />
                <label htmlFor="confirm-password" className="auth-input-label">Confirm Password</label>
                <i className="input-icon fa-solid fa-square-check"></i>
              </div>
              {signupErrorMessage && <p style={{ color: 'red', textAlign: 'center' }}>{signupErrorMessage}</p>}
              <input type="submit" value="Sign Up" className="btn auth-submit-btn" />
            </form>
          </div>
        </div>
        <div className="panels-container">
          <div className="panel left-panel">
            <div className="auth-content">
              <h3>New here?</h3>
              <p>Sign up to get started with our awesome platform.</p>
              <button className="btn transparent" id="sign-up-btn" ref={signupBtnRef} onClick={goToSignUp}>
                Sign Up
              </button>
            </div>
            <img src={registerImage} className="image" alt="Log In Illustration" />
          </div>
          <div className="panel right-panel">
            <div className="auth-content">
              <h3>One of us?</h3>
              <p>If you already have an account, Log in here.</p>
              <button className="btn transparent" id="sign-in-btn" ref={loginBtnRef} onClick={goToLogin}>
                Log In
              </button>
            </div>
            <img src={registerImage} className="image" alt="Sign Up Illustration" />
          </div>
        </div>
      </div>
    </div>
  );
}

export default Auth;
