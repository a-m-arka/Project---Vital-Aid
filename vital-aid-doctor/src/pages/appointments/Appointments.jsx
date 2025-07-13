import React from 'react'
import './Appointments.scss'
import { useNavigate } from 'react-router-dom';
import { formatDate } from '../../helperFunctions/formatDate';

import { appointments } from '../../temporaryData/appointments'


const Appointments = () => {
    const navigate = useNavigate();

    const today = new Date()
    today.setHours(0, 0, 0, 0)

    const pastAppointments = appointments.filter(app => {
        const appDate = new Date(app.appointmentDate)
        appDate.setHours(0, 0, 0, 0)
        return appDate < today
    })

    const todayAppointments = appointments.filter(app => {
        const appDate = new Date(app.appointmentDate)
        appDate.setHours(0, 0, 0, 0)
        return appDate.getTime() === today.getTime()
    })

    const upcomingAppointments = appointments.filter(app => {
        const appDate = new Date(app.appointmentDate)
        appDate.setHours(0, 0, 0, 0)
        return appDate > today
    })

    const handleViewDetails = (appointment) => {
        navigate('/appointment-details', { state: { appointment } });
    }

    const renderList = (title, list) => (
        <section className="appointments-section">
            <h2>{title}</h2>
            <div className="appointments-data">
                {list.length === 0 ? (
                    <p>No appointments.</p>
                ) : (
                    list.map(app => (
                        <div key={app.appointmentToken} className="appointment-card">
                            <div className="appointment-details">
                                <p><strong>Appointment No:</strong> {app.appointmentToken}</p>
                                <p><strong>Appointment Date:</strong> {formatDate(app.appointmentDate)}</p>
                                <p><strong>Patient Name:</strong> {app.patientName}</p>
                                <p><strong>Reason:</strong> {app.reasonForVisit}</p>
                            </div>
                            <button className='view-details-btn' onClick={() => handleViewDetails(app)}>
                                View Details
                            </button>
                        </div>
                    ))
                )}
            </div>
        </section>
    )

    return (
        <div className="appointments-container">
            {renderList('Today\'s Appointments', todayAppointments)}
            {renderList('Upcoming Appointments', upcomingAppointments)}
            {renderList('Past Appointments', pastAppointments)}
        </div>
    )
}

export default Appointments
