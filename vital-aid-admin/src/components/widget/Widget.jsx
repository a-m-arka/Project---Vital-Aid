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
        textColor: "dodgerblue",
        count: userRows.length,
        link: "See All Users",
        icon: <PeopleIcon className='icon' style={{ color: "dodgerblue", backgroundColor: "rgba(30, 143, 255, 0.3)" }} />
      };
      break;
    case "doctor":
      data = {
        title: "DOCTORS",
        textColor: "darkgreen",
        count: doctorRows.length-1,
        link: "See All Doctors",
        icon: <MedicalInformationIcon className='icon' style={{ color: "darkgreen", backgroundColor: "rgba(0, 100, 0, 0.3)" }} />
      };
      break;
    case "hospital":
      data = {
        title: "HOSPITALS",
        textColor: "indigo",
        count: hospitalRows.length-1,
        link: "See All hospitals",
        icon: <LocalHospitalIcon className='icon' style={{ color: "indigo", backgroundColor: "rgba(76, 0, 130, 0.3)" }} />
      };
      break;
    case "ambulance":
      data = {
        title: "AMBULANCES",
        textColor: "crimson",
        count: ambulanceRows.length-1,
        link: "See All Ambulances",
        icon: <AirportShuttleIcon className='icon' style={{ color: "crimson", backgroundColor: "rgba(220, 20, 60, 0.3)" }} />
      };
      break;
    case "product":
      data = {
        title: "PRODUCTS",
        textColor: "darkgoldenrod",
        count: productRows.length-1,
        link: "See All Products",
        icon: <AddShoppingCartIcon className='icon' style={{ color: "darkgoldenrod", backgroundColor: "rgba(184, 135, 11, 0.3)" }} />
      };
      break;
    default:
      break;
  }


  return (
    <div className='widget'>
      <div className="left" style={{color: data.textColor}}>
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
