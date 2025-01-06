import React, { createContext, useContext, useState, useEffect } from 'react';

const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    const token = localStorage.getItem('adminToken');
    return token !== null;
  });

  const [profile, setProfile] = useState(() => {
    const storedProfile = localStorage.getItem('adminProfile');
    return storedProfile ? JSON.parse(storedProfile) : null;
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchProfile = async () => {
    const token = localStorage.getItem('adminToken');
    if (!token) {
      // console.log('Token not found in localStorage.');
      return;
    }

    const url = 'http://localhost:8080/vital_aid/admin/profile';

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
        localStorage.removeItem('adminToken');
        localStorage.removeItem('adminProfile');
        return;
      }

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setProfile(data); 
      localStorage.setItem('adminProfile', JSON.stringify(data)); 

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

  return (
    <GlobalContext.Provider value={{ isLoggedIn, setIsLoggedIn, profile, setProfile, loading, error }}>
      {children}
    </GlobalContext.Provider>
  );
};

export const useGlobalContext = () => useContext(GlobalContext);
