import React, { createContext, useContext, useState, useEffect } from 'react';

const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    const token = localStorage.getItem('doctorToken');
    return token !== null;
  });

  const [profile, setProfile] = useState(() => {
    const storedProfile = localStorage.getItem('doctorProfile');
    return storedProfile ? JSON.parse(storedProfile) : null;
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const [ratings, setRatings] = useState(() => {
    const storedRatings = localStorage.getItem('doctorRatings');
    return storedRatings ? JSON.parse(storedRatings) : [];
  });

  const [appointments, setAppointments] = useState(() => {
    const storedAppointments = localStorage.getItem('doctorAppointments');
    return storedAppointments ? JSON.parse(storedAppointments) : [];
  });

  const fetchProfile = async () => {
    const token = localStorage.getItem('doctorToken');
    if (!token) {
      // console.log('Token not found in localStorage.');
      return;
    }

    const url = 'http://localhost:8080/vital_aid/doctors/doctorProfile';

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
        localStorage.removeItem('doctorToken');
        localStorage.removeItem('doctorProfile');
        localStorage.removeItem('doctorRatings');
        localStorage.removeItem('doctorAppointments');
        return;
      }

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setProfile(data);
      localStorage.setItem('doctorProfile', JSON.stringify(data));

      // console.log('Fetched doctor Profile:', profile);

    } catch (err) {
      setError(err.message);
      console.error('Error fetching profile:', err.message);
    } finally {
      setLoading(false);
    }
  };

  const fetchAppointments = async () => {
    const token = localStorage.getItem('doctorToken');
    if (!token) {
      // console.log('Token not found in localStorage.');
      return;
    }

    const url = 'http://localhost:8080/vital_aid/appointment/doctorAppointments';

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
        localStorage.removeItem('doctorToken');
        localStorage.removeItem('doctorProfile');
        localStorage.removeItem('doctorRatings');
        localStorage.removeItem('doctorAppointments');
        return;
      }

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setAppointments(data);
      localStorage.setItem('doctorAppointments', JSON.stringify(data));

      // console.log('Fetched doctor Appointments:', appointments);

    } catch (err) {
      setError(err.message);
      console.error('Error fetching appointments:', err.message);
    } finally {
      setLoading(false);
    }
  };

  const fetchRatings = async () => {
    const token = localStorage.getItem('doctorToken');
    if (!token) {
      // console.log('Token not found in localStorage.');
      return;
    }

    const url = 'http://localhost:8080/vital_aid/doctorRating/ratingsMadeForDoctorEmail';

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
        localStorage.removeItem('doctorToken');
        localStorage.removeItem('doctorProfile');
        localStorage.removeItem('doctorRatings');
        localStorage.removeItem('doctorAppointments');
        return;
      }

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      setRatings(data);
      localStorage.setItem('doctorRatings', JSON.stringify(data));

      // console.log('Fetched doctor Ratings:', ratings);

    } catch (err) {
      setError(err.message);
      console.error('Error fetching ratings:', err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (isLoggedIn) {
      fetchProfile();
      fetchRatings();
      fetchAppointments();
    }
  }, [isLoggedIn]);

  return (
    <GlobalContext.Provider
      value={{
        isLoggedIn,
        setIsLoggedIn,
        profile,
        setProfile,
        ratings,
        setRatings,
        appointments,
        setAppointments,
        loading,
        error
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export const useGlobalContext = () => useContext(GlobalContext);
