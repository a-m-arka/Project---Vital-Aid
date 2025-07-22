import React from 'react'
import { Outlet } from 'react-router-dom'
import { useGlobalContext } from '../../context/GlobalContext';
import AccessDenied from '../popUps/AccessDenied';

const PrivateRoute = () => {
    const { isLoggedIn } = useGlobalContext();
    // const navigate = useNavigate();

    // const onClose = () => {
    //   navigate(-1);
    // };

    if (!isLoggedIn) {
        return <AccessDenied />;
    }

    return <Outlet />;
}

export default PrivateRoute
