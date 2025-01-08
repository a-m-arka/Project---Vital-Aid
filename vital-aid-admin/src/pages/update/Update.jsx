import React from 'react';
import './Update.scss';
import Sidebar from '../../components/sidebar/Sidebar';
import { useLocation } from 'react-router-dom';
import UpdateDoctorForm from '../../components/updateForms/UpdateDoctorForm';
import UpdateHospitalForm from '../../components/updateForms/UpdateHospitalForm';
import UpdateAmbulanceForm from '../../components/updateForms/UpdateAmbulanceForm';
import UpdateProductForm from '../../components/updateForms/UpdateProductForm';

const Update = ({ type }) => {
    const { state } = useLocation();
    const data = state?.row;

    // console.log(data);

    return (
        <div className='update'>
            <Sidebar />
            <div className="updateContainer">
                <div className="top">
                    <h1>UPDATE {String(type).toUpperCase()}</h1>
                </div>
                <div className="formContainer">
                    {type === 'doctor' && <UpdateDoctorForm data={data} />}
                    {type === 'hospital' && <UpdateHospitalForm data={data} />}
                    {type === 'ambulance' && <UpdateAmbulanceForm data={data} />}
                    {type === 'product' && <UpdateProductForm data={data} />}
                </div>
            </div>
        </div>
    );
};

export default Update;
