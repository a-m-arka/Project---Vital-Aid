import React from 'react'
import './AppointmentDetails.scss'
import { useLocation, useNavigate } from 'react-router-dom'
import { formatDate } from '../../helperFunctions/formatDate'

const AppointmentDetails = () => {
  const { state } = useLocation()
  const appointment = state?.appointment
  const navigate = useNavigate()

  if (!appointment) {
    return (
      <div className="appointment-details-container">
        <p>No appointment data available.</p>
        <button onClick={() => navigate(-1)}>Go Back</button>
      </div>
    )
  }

  return (
    <div className="appointment-details-container">
      <div className="details-grid">
        <h2>Appointment Details</h2>
        <div className="row"><span className="label">Appointment No.</span><span className="value">{appointment.appointmentToken}</span></div>
        <div className="row"><span className="label">Patient Name</span><span className="value">{appointment.patientName}</span></div>
        <div className="row"><span className="label">Gender</span><span className="value">{appointment.patientGender}</span></div>
        <div className="row"><span className="label">Date of Birth</span><span className="value">{formatDate(appointment.patientDob)}</span></div>
        <div className="row"><span className="label">Phone</span><span className="value">{appointment.patientPhone}</span></div>
        <div className="row"><span className="label">Email</span><span className="value">{appointment.patientEmail}</span></div>
        <div className="row"><span className="label">Appointment Date</span><span className="value">{formatDate(appointment.appointmentDate)}</span></div>
        <div className="row"><span className="label">Visit Day</span><span className="value">{appointment.visitDay}</span></div>
        <div className="row"><span className="label">Reason for Visit</span><span className="value">{appointment.reasonForVisit}</span></div>
        <div className="row"><span className="label">Booked By</span><span className="value">{appointment.appointmentBy}</span></div>
      </div>

      <button className="back-button" onClick={() => navigate(-1)}>Back</button>
    </div>
  )
}

export default AppointmentDetails
