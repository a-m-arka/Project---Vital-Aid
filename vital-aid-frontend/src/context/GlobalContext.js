import React, { createContext, useContext, useState, useEffect } from 'react';

const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    const token = localStorage.getItem('token');
    return token !== null;
  });

  const [profile, setProfile] = useState(() => {
    const storedProfile = localStorage.getItem('profile');
    return storedProfile ? JSON.parse(storedProfile) : null;
  });

  const [doctorData, setDoctorData] = useState([]);
  const [hospitalData, setHospitalData] = useState([]);
  const [ambulanceData, setAmbulanceData] = useState([]);
  const [productData, setProductData] = useState([]);


  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchProfile = async () => {
    const token = localStorage.getItem('token');
    if (!token) {
      // console.log('Token not found in localStorage.');
      return;
    }

    const url = 'http://localhost:8080/vital_aid/profile';

    try {
      setLoading(true);
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      if (response.status === 401) {
        console.log('Unauthorized: Token is invalid or expired.');
        setIsLoggedIn(false);
        localStorage.removeItem('token');
        localStorage.removeItem('profile');
        return;
      }

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setProfile(data);
      localStorage.setItem('profile', JSON.stringify(data));

      // console.log('Fetched Profile:', profile);

    } catch (err) {
      setError(err.message);
      console.error('Error fetching profile:', err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (isLoggedIn) {
      fetchProfile();
    }
  }, [isLoggedIn]);

  const fetchData = async (url, dataType) => {
    const headers = {
      'Content-Type': 'application/json',
    };

    try {
      const response = await fetch(url, { method: 'GET', headers });
      if (!response.ok) {
        throw new Error(`Failed to fetch data from ${url}`);
      }
      const data = await response.json();

      switch (dataType) {
        case 'doctor':
          setDoctorData(data);
          break;
        case 'hospital':
          setHospitalData(data);
          break;
        case 'ambulance':
          setAmbulanceData(data);
          break;
        case 'product':
          setProductData(data);
          break;
        default:
          throw new Error(`Unknown data type: ${dataType}`);
      }

      // console.log(dataType, data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchData('http://localhost:8080/vital_aid/doctors/allDoctors', 'doctor');
    fetchData('http://localhost:8080/vital_aid/hospitals/allHospitals', 'hospital');
    fetchData('http://localhost:8080/vital_aid/medical_store/allProducts', 'product');
    fetchData('http://localhost:8080/vital_aid/ambulance/allAmbulances', 'ambulance');
  }, []);

  return (
    <GlobalContext.Provider
      value={{
        isLoggedIn,
        setIsLoggedIn,
        profile,
        setProfile,
        loading,
        error,
        doctorData,
        hospitalData,
        ambulanceData,
        productData
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export const useGlobalContext = () => useContext(GlobalContext);
