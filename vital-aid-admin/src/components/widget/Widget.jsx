import React from 'react'
import './Widget.scss'
import PeopleIcon from '@mui/icons-material/People';
import MedicalInformationIcon from '@mui/icons-material/MedicalInformation';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import AirportShuttleIcon from '@mui/icons-material/AirportShuttle';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import { Link } from 'react-router-dom';
import { userRows } from '../../datasource/userdata';
import { doctorRows } from '../../datasource/doctorData';
import { hospitalRows } from '../../datasource/hospitalData';
import { productRows } from '../../datasource/productData';
import { ambulanceRows } from '../../datasource/ambulanceData';


const Widget = ({ type }) => {

  let data;

  switch (type) {
    case "user":
      data = {
        title: "USERS",
        count: userRows.length,
        link: "See All Users",
        icon: <PeopleIcon className='icon' style={{ color: "blue", backgroundColor: "rgba(0, 0, 255, 0.2)" }} />
      };
      break;
    case "doctor":
      data = {
        title: "DOCTORS",
        count: doctorRows.length-1,
        link: "See All Doctors",
        icon: <MedicalInformationIcon className='icon' style={{ color: "green", backgroundColor: "rgba(0, 128, 0, 0.2)" }} />
      };
      break;
    case "hospital":
      data = {
        title: "HOSPITALS",
        count: hospitalRows.length-1,
        link: "See All hospitals",
        icon: <LocalHospitalIcon className='icon' style={{ color: "red", backgroundColor: "rgba(255, 0, 0, 0.2)" }} />
      };
      break;
    case "ambulance":
      data = {
        title: "AMBULANCES",
        count: ambulanceRows.length-1,
        link: "See All Ambulances",
        icon: <AirportShuttleIcon className='icon' style={{ color: "purple", backgroundColor: "rgba(128, 0, 128, 0.2)" }} />
      };
      break;
    case "product":
      data = {
        title: "PRODUCTS",
        count: productRows.length-1,
        link: "See All Products",
        icon: <AddShoppingCartIcon className='icon' style={{ color: "orange", backgroundColor: "rgba(255, 166, 0, 0.2)" }} />
      };
      break;
    default:
      break;
  }


  return (
    <div className='widget'>
      <div className="left">
        <div className="title">{data.title}</div>
        <div className="counter">{data.count}</div>
        <Link to={`/${type}`} style={{ textDecoration: "none" }}>
          <div className="link">{data.link}</div>
        </Link>
      </div>
      <div className="right">
        {data.icon}
      </div>
    </div>
  )
}

export default Widget
