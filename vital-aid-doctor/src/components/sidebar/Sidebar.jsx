import React from 'react'
import { NavLink } from 'react-router-dom'
import './Sidebar.scss'

import DashboardIcon from '@mui/icons-material/Dashboard'
import EventNoteIcon from '@mui/icons-material/EventNote'
import PeopleIcon from '@mui/icons-material/People'
import DescriptionIcon from '@mui/icons-material/Description'
import AccountCircleIcon from '@mui/icons-material/AccountCircle'
import LogoutIcon from '@mui/icons-material/Logout'

const Sidebar = () => {
  return (
    <div className="sidebar">
      <div className="top">
        <NavLink style={{ textDecoration: "none" }} to="/dashboard" className="logo">
          Vital Aid Doctor
        </NavLink>
      </div>
      <hr />
      <div className="bottom">
        <ul>
          <p className="title">Main</p>
          <NavLink style={{ textDecoration: "none" }} to="/dashboard" className={({ isActive }) => isActive ? 'active' : ''}>
            <li>
              <DashboardIcon className="icon" />
              <span>Dashboard</span>
            </li>
          </NavLink>

          <p className="title">My Work</p>
          <NavLink style={{ textDecoration: "none" }} to="/appointments" className={({ isActive }) => isActive ? 'active' : ''}>
            <li>
              <EventNoteIcon className="icon" />
              <span>Appointments</span>
            </li>
          </NavLink>
          <NavLink style={{ textDecoration: "none" }} to="/patients" className={({ isActive }) => isActive ? 'active' : ''}>
            <li>
              <PeopleIcon className="icon" />
              <span>Patients</span>
            </li>
          </NavLink>
          {/* <NavLink style={{ textDecoration: "none" }} to="/prescriptions" className={({ isActive }) => isActive ? 'active' : ''}>
            <li>
              <DescriptionIcon className="icon" />
              <span>Prescriptions</span>
            </li>
          </NavLink> */}

          <p className="title">Account</p>
          <NavLink style={{ textDecoration: "none" }} to="/profile" className={({ isActive }) => isActive ? 'active' : ''}>
            <li>
              <AccountCircleIcon className="icon" />
              <span>Profile</span>
            </li>
          </NavLink>
          <NavLink style={{ textDecoration: "none" }} to="/login" className={({ isActive }) => isActive ? 'active' : ''}>
            <li>
              <LogoutIcon className="icon" />
              <span>Logout</span>
            </li>
          </NavLink>
        </ul>
      </div>
    </div>
  )
}

export default Sidebar
