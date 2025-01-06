import React from 'react';
import { useNavigate, Outlet } from 'react-router-dom';
import { useGlobalContext } from '../context/GlobalContext';
import AccessDeniedPopUp from './AccessDeniedPopUp';

const PrivateRoute = () => {
  const { isLoggedIn } = useGlobalContext();
  const navigate = useNavigate();

  const onClose = () => {
    navigate(-1);
  };

  if (!isLoggedIn) {
    return <AccessDeniedPopUp onClose={onClose} />;
  }

  return <Outlet />;
};

export default PrivateRoute;
