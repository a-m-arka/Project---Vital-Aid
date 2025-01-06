import React from 'react';
import { Link } from 'react-router-dom';
import '../style/Footer.scss';
import logo from '../Images/logo.png';
import { useGlobalContext } from '../context/GlobalContext';

const Footer = () => {
  const { isLoggedIn, setIsLoggedIn } = useGlobalContext();
  const handleLogOut = () => {
    setIsLoggedIn(false);
    localStorage.removeItem('token');
  };

  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-section">
          <img src={logo} alt="Logo" className="footer-logo" />
          <h2>Vital Aid</h2>
          <p>Caring for your health, every step of the way.</p>
        </div>
        <div className="footer-section">
          <h3>Quick Links</h3>
          <ul className="footer-links">
            <Link style={{ textDecoration: "none" }} to="/about"><li>About Us</li></Link>
            <Link style={{ textDecoration: "none" }} to="/ambulance"><li>Ambulance</li></Link>
            <Link style={{ textDecoration: "none" }} to="/doctors"><li>Make Appointment</li></Link>
            <Link style={{ textDecoration: "none" }} to="/store"><li>Medical Store</li></Link>
            {isLoggedIn ? (
              <Link style={{ textDecoration: "none" }} to="/"><li onClick={handleLogOut}>Log Out</li></Link>
            ):(
              <Link style={{ textDecoration: "none" }} to="/login"><li>Log in</li></Link>
            )}
          </ul>
        </div>
        <div className="footer-section">
          <h3>Contact Us</h3>
          <p>Email: vitalaid2104@gmail.com</p>
          <p>Phone: 01234567890</p>
          <div className="footer-icons">
            <a href="https://facebook.com"><i className="fab fa-facebook-f"></i></a>
            <a href="https://twitter.com"><i className="fab fa-twitter"></i></a>
            <a href="https://instagram.com"><i className="fab fa-instagram"></i></a>
            <a href="https://linkedin.com"><i className="fab fa-linkedin-in"></i></a>
          </div>
        </div>
      </div>
      <div className="footer-bottom">
        <p>© 2024 Vital Aid. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
