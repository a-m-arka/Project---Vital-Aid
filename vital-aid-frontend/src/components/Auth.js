import React, { useState, useRef } from 'react';
import '../style/Auth.css';
import registerImage from '../Images/register.svg';
import { Link } from 'react-router-dom';

function Auth() {
  const loginBtnRef = useRef();
  const signupBtnRef = useRef();
  const authContainerRef = useRef();

  const [isSignupMode, setIsSignupMode] = useState(false);
  const [inputType, setInputType] = useState('text');

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

  return (
    <div className="rout-container">

      <div className="auth-container" ref={authContainerRef}>
        <div className="forms-container">
          <div className="signin-signup">
            <form className="sign-in-form">
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
              <input type="submit" value="Log In" className="btn solid auth-submit-btn" />
              <Link to="/forgotpassword">Forgot Password?</Link>
            </form>
            <form className="sign-up-form">
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
