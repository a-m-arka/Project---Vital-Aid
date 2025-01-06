import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../style/Navbar.css';
import logo from "../Images/logo.png";
import { useGlobalContext } from '../context/GlobalContext';

export default function Navbar() {
    const location = useLocation();
    const [sidebarOpen, setSidebarOpen] = useState(false);
    const { isLoggedIn, setIsLoggedIn } = useGlobalContext();

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    const handleLogOut = () => {
        setIsLoggedIn(false);
        localStorage.removeItem('token');
    };


    return (
        <>
            <div className="navbar-container">
                <div className="navbar">

                    <div className='logo'>
                        <Link to="/">
                            <img src={logo} alt="" />
                        </Link>
                    </div>



                    <div className='nav-items'>

                        <div className={sidebarOpen ? 'nav-links sidebar-open' : 'nav-links'}>
                            <ul>
                                <Link to="/" >
                                    <li className={location.pathname === "/" ? "nav-active" : ""}>Home</li>
                                </Link>
                                <Link to="/doctors">
                                    <li className={location.pathname === "/doctors" ? "nav-active" : ""}>Doctors</li>
                                </Link>
                                <Link to="/hospitals">
                                    <li className={location.pathname === "/hospitals" ? "nav-active" : ""}>Hospitals</li>
                                </Link>
                                <Link to="/ambulance">
                                    <li className={location.pathname === "/ambulance" ? "nav-active" : ""}>Call Ambulance</li>
                                </Link>
                                <Link to="/store">
                                    <li className={location.pathname === "/store" ? "nav-active" : ""}>Medical Store</li>
                                </Link>
                                <Link to="/about">
                                    <li className={location.pathname === "/about" ? "nav-active" : ""}>About Us</li>
                                </Link>
                                <Link to="/profile">
                                    <li className={location.pathname === "/profile" ? "nav-active" : ""}>Profile</li>
                                </Link>
                                {!isLoggedIn && (
                                    <Link to="/login" className="login-btn">
                                        <button>
                                            Log In
                                        </button>
                                    </Link>
                                )}
                                {isLoggedIn && (
                                    <Link to="/" className="login-btn">
                                        <button onClick={handleLogOut}>
                                            Log Out
                                        </button>
                                    </Link>
                                )}
                            </ul>
                        </div>

                        <div className="sidebar">
                            <button onClick={toggleSidebar}>
                                <i className={!sidebarOpen ? "fa-solid fa-bars" : "fa-solid fa-xmark"}></i>
                            </button>
                        </div>

                    </div>

                </div>

            </div>
        </>
    );
}
