import React from 'react'
import './Sidebar.scss'
import DashboardIcon from '@mui/icons-material/Dashboard';
import PeopleIcon from '@mui/icons-material/People';
import MedicalInformationIcon from '@mui/icons-material/MedicalInformation';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import AirportShuttleIcon from '@mui/icons-material/AirportShuttle';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import LogoutIcon from '@mui/icons-material/Logout';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { Link } from 'react-router-dom';
import { useGlobalContext } from '../../context/GlobalContext';

const Sidebar = () => {

    const { setIsLoggedIn } = useGlobalContext();

    const handleLogOut = () => {
        setIsLoggedIn(false);
        localStorage.removeItem('adminToken');
    };

    return (
        <div className='sidebar'>

            <div className="top">
                <Link to='/home' style={{ textDecoration: "none" }}>
                    <span className="logo">Vital Aid</span>
                </Link>
            </div>
            <hr />
            <div className="bottom">
                <ul>
                    <p className="title">Main</p>
                    <Link to='/home' style={{ textDecoration: "none" }}>
                        <li>
                            <DashboardIcon className='icon' />
                            <span>Dashboard</span>
                        </li>
                    </Link>
                    <p className="title">Management</p>
                    <Link to='/user' style={{ textDecoration: "none" }}>
                        <li>
                            <PeopleIcon className='icon' />
                            <span>Users</span>
                        </li>
                    </Link>
                    <Link to='/doctor' style={{ textDecoration: "none" }}>
                        <li>
                            <MedicalInformationIcon className='icon' />
                            <span>Doctors</span>
                        </li>
                    </Link>
                    <Link to='/hospital' style={{ textDecoration: "none" }}>
                        <li>
                            <LocalHospitalIcon className='icon' />
                            <span>Hospitals</span>
                        </li>
                    </Link>
                    <Link to='/ambulance' style={{ textDecoration: "none" }}>
                        <li>
                            <AirportShuttleIcon className='icon' />
                            <span>Ambulances</span>
                        </li>
                    </Link>
                    <Link to='/product' style={{ textDecoration: "none" }}>
                        <li>
                            <AddShoppingCartIcon className='icon' />
                            <span>Products</span>
                        </li>
                    </Link>
                    <p className="title">Admin Account</p>
                    <Link to='/profile' style={{ textDecoration: "none" }}>
                        <li>
                            <AccountCircleIcon className='icon' />
                            <span>Profile</span>
                        </li>
                    </Link>
                    <Link to='/login' onClick={handleLogOut} style={{ textDecoration: "none" }}>
                        <li>
                            <LogoutIcon className='icon' />
                            <span>Logout</span>
                        </li>
                    </Link>
                </ul>
            </div>

        </div>
    )
}

export default Sidebar
