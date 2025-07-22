import React, { useState, useEffect } from 'react';
import '../style/AppoinmentList.scss';
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

    const today = new Date();
    today.setHours(0, 0, 0, 0); // Remove time part for accurate comparison

    const upcomingAppointments = appoinmentList.filter(a => new Date(a.appointmentDate) >= today);
    const pastAppointments = appoinmentList.filter(a => new Date(a.appointmentDate) < today);

    // console.log(upcomingAppointments);

    const renderTable = (appointments, heading) => (
        <div className="appoinment-table-container" style={{ borderRadius: '10px', overflow: 'hidden' }}>
            <h2 className="table-heading" style={{padding: '10px'}}>{heading}</h2>
            <table className="appoinment-table">
                <thead>
                    <tr>
                        <th>Appointment Token</th>
                        <th>Patient</th>
                        <th>Doctor</th>
                        <th>Appointment Date</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {appointments.length > 0 ? appointments.map((appoinment, index) => (
                        <tr key={index}>
                            <td>{appoinment.appointmentToken}</td>
                            <td>{appoinment.patientName}</td>
                            <td>{appoinment.appointmentWith}</td>
                            <td>{appoinment.appointmentDate}</td>
                            <td>
                                <button className='appoinment-book-btn' onClick={() => handleView(appoinment)}>
                                    View Details
                                </button>
                            </td>
                        </tr>
                    )) : (
                        <tr>
                            <td colSpan="5">No {heading.toLowerCase()}.</td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    );

    return (
        <div className='rout-container'>
            <div className="appoinment-container">
                <div className="heading caption">
                    <h1>Your Appointments</h1>
                </div>
                {renderTable(upcomingAppointments, 'Upcoming Appointments')}
                {renderTable(pastAppointments, 'Past Appointments')}
            </div>
        </div>
    );
};

export default AppoinmentList;
