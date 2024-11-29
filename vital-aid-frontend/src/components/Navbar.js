import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../style/Navbar.css';
import logo from "../Images/logo.png";

export default function Navbar() {
    const location = useLocation();
    const [sidebarOpen, setSidebarOpen] = useState(false);
    // const [isSearchOpen, setIsSearchOpen] = useState(false);
    // const [isSearched, setIsSearched] = useState(false);

    const toggleSidebar = () => {
        setSidebarOpen(!sidebarOpen);
    };

    // const toggleSearchBox = () => {
    //     setIsSearchOpen(!isSearchOpen);
    //     setIsSearched(true);
    // };

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
                                <Link to="/login" className="login-btn">
                                    <button>
                                        Log In
                                    </button>
                                </Link>
                            </ul>
                        </div>

                        {/* <div className="search-icon" onClick={isSearchOpen ? null : toggleSearchBox}>
                            <i className="fa-solid fa-magnifying-glass"></i>
                        </div> */}

                        <div className="sidebar">
                            <button onClick={toggleSidebar}>
                                <i className={!sidebarOpen ? "fa-solid fa-bars" : "fa-solid fa-xmark"}></i>
                            </button>
                        </div>

                    </div>

                </div>

                {/* <div className={`search-box ${isSearchOpen  ? 'pop-out' : isSearched ? 'pop-in' : ''}`}>
                    <div className="search-input">
                        <input type="text" placeholder="Search Here" />
                    </div>
                    <div className="search-btn">
                        <i className="fa-solid fa-magnifying-glass"></i>
                    </div>
                    <div className="search-cancel" onClick={toggleSearchBox}>
                        <i className="fa-solid fa-xmark"></i>
                    </div>
                </div> */}

            </div>
        </>
    );
}
