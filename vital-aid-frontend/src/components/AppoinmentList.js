import React, { useState, useEffect } from 'react'
import '../style/AppoinmentList.scss'
import { useNavigate } from 'react-router-dom';

const AppoinmentList = () => {
    const [appoinmentList, setAppoinmentList] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchOrders = async () => {
            const token = localStorage.getItem('token');
            const url = 'http://localhost:8080/vital_aid/appointment/userAppointments';

            try {
                const response = await fetch(url, {
                    method: 'GET',
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });

                if (response.ok) {
                    const data = await response.json();
                    setAppoinmentList(data);
                    // console.log(data);
                } else {
                    const errorMessage = await response.text();
                    console.log(errorMessage);
                }
            } catch (err) {
                console.error('Error fetching order data:', err.message);
            }
        };

        fetchOrders();
    }, []);

    const handleView = (appointment) => {
        navigate('/appointmentdetails', { state: { appointmentData: appointment } });
    };


    return (
        <div className='rout-container'>
            <div className="appoinment-container">
                <div className="heading caption">
                    <h1>Your Appointments</h1>
                </div>
                <div className="appoinment-table-container">
                    <table className="appoinment-table">
                        <thead>
                            <tr>
                                <th>Appointment Token</th>
                                <th>Patient</th>
                                <th>Doctor</th>
                                <th>Visiting Day</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {appoinmentList.map((appoinment, index) => (
                                <tr key={index}>
                                    <td>{appoinment.appointmentToken}</td>
                                    <td>{appoinment.patientName}</td>
                                    <td>{appoinment.appointmentWith}</td>
                                    <td>{appoinment.visitDay}</td>
                                    <td>
                                        <button className='appoinment-book-btn' onClick={() => handleView(appoinment)}>
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

export default AppoinmentList
