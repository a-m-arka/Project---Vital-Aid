import React, { useState, useEffect } from 'react'
import './Widget.scss'
import PeopleIcon from '@mui/icons-material/People';
import MedicalInformationIcon from '@mui/icons-material/MedicalInformation';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import AirportShuttleIcon from '@mui/icons-material/AirportShuttle';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import { Link } from 'react-router-dom';


const Widget = ({ type }) => {

  let data;
  const [totalData, setTotalData] = useState();

  useEffect(() => {
    const fetchData = async (url, requireAuth = false) => {
      const headers = {
        'Content-Type': 'application/json',
      };

      if (requireAuth) {
        const token = localStorage.getItem('adminToken');
        if (token) {
          headers.Authorization = `Bearer ${token}`;
        } else {
          console.error('Token not found!');
          return;
        }
      }

      try {
        const response = await fetch(url, { method: 'GET', headers });
        if (!response.ok) {
          throw new Error(`Failed to fetch data from ${url}`);
        }
        const data = await response.json();
        setTotalData(data.length);
        // console.log(type, data);
      } catch (error) {
        console.error(error);
      }
    };

    switch (type) {
      case 'user':
        fetchData('http://localhost:8080/vital_aid/allUsers', true);
        break;
      case 'doctor':
        fetchData('http://localhost:8080/vital_aid/doctors/allDoctors', false);
        break;
      case 'hospital':
        fetchData('http://localhost:8080/vital_aid/hospitals/allHospitals', false);
        break;
      case 'product':
        fetchData('http://localhost:8080/vital_aid/medical_store/allProducts', false);
        break;
      case 'ambulance':
        fetchData('http://localhost:8080/vital_aid/ambulance/allAmbulances', false);
        break;
      default:
        break;
    }
  }, [type]);

  switch (type) {
    case "user":
      data = {
        title: "USERS",
        textColor: "dodgerblue",
        link: "See All Users",
        icon: <PeopleIcon className='icon' style={{ color: "dodgerblue", backgroundColor: "rgba(30, 143, 255, 0.3)" }} />
      };
      break;
    case "doctor":
      data = {
        title: "DOCTORS",
        textColor: "darkgreen",
        link: "See All Doctors",
        icon: <MedicalInformationIcon className='icon' style={{ color: "darkgreen", backgroundColor: "rgba(0, 100, 0, 0.3)" }} />
      };
      break;
    case "hospital":
      data = {
        title: "HOSPITALS",
        textColor: "indigo",
        link: "See All hospitals",
        icon: <LocalHospitalIcon className='icon' style={{ color: "indigo", backgroundColor: "rgba(76, 0, 130, 0.3)" }} />
      };
      break;
    case "ambulance":
      data = {
        title: "AMBULANCES",
        textColor: "crimson",
        link: "See All Ambulances",
        icon: <AirportShuttleIcon className='icon' style={{ color: "crimson", backgroundColor: "rgba(220, 20, 60, 0.3)" }} />
      };
      break;
    case "product":
      data = {
        title: "PRODUCTS",
        textColor: "darkgoldenrod",
        link: "See All Products",
        icon: <AddShoppingCartIcon className='icon' style={{ color: "darkgoldenrod", backgroundColor: "rgba(184, 135, 11, 0.3)" }} />
      };
      break;
    default:
      break;
  }


  return (
    <div className='widget'>
      <div className="left" style={{ color: data.textColor }}>
        <div className="title">{data.title}</div>
        <div className="counter">{totalData}</div>
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
