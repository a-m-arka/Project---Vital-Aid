import React from 'react'
import '../style/HospitalDoctors.scss'
import { useLocation, useNavigate } from 'react-router-dom'

const HospitalDoctors = () => {
    const { state } = useLocation();
    const doctorList = state?.doctorData;
    const navigate = useNavigate();

    const handleView = (doctor) => {
        navigate('/doctor_details', { state: { doctor } });
    };

    return (
        <div className='rout-container'>
            <div className="hospital-doctor-container">
                <div className="heading caption">
                    <h1>Hospital Doctor List</h1>
                </div>
                <div className="hospital-doctor-table-container">
                    <table className="hospital-doctor-table">
                        <thead>
                            <tr>
                                <th>Doctor Name</th>
                                <th>Field</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {doctorList.map((doctor, index) => (
                                <tr key={index}>
                                    <td>{doctor.doctorName}</td>
                                    <td>{doctor.specializationField}</td>
                                    <td>
                                        <button className='hospital-doctor-book-btn' onClick={() => handleView(doctor)}>
                                            View Details
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default HospitalDoctors
