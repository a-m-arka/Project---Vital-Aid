import React, { useState, useEffect } from 'react'
import './List.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import Datatable from '../../components/datatable/Datatable'
import { useNavigate } from 'react-router-dom'

const List = ({ type, columns, adminControl }) => {

  const navigate = useNavigate();
  const handleAddClick = (row) => {
    navigate(`/${type}/new`, { state: { row } });
  };

  const [dataRows, setDataRows] = useState([]);

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
        setDataRows(data);
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
  }, [type]); // Fetch new data when 'type' changes

  return (
    <div className='list'>
      <Sidebar />

      <div className="listContainer">
        <div className="top">
          <h1>{String(type).toUpperCase()} LIST</h1>
          {adminControl && (
            <div className="addButton" onClick={() => handleAddClick()}>
              Add New
            </div>
          )}
        </div>
        <Datatable type={type} rows={dataRows} columns={columns} adminControl={adminControl} />
      </div>
    </div>
  );
};

export default List;
