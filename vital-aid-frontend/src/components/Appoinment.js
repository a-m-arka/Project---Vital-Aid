import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../style/Appoinment.css';
import PopUp from './PopUp';
import { useGlobalContext } from '../context/GlobalContext';

export default function Appoinment() {
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const { state } = useLocation();
  const doctor = state?.doctor;
  const navigate = useNavigate();
  const [showPopUp, setShowPopUp] = useState(false);
  const { profile } = useGlobalContext(); // Access the profile data
  const [formData, setFormData] = useState({
    fullName: '',
    gender: '',
    dob: '',
    phone: '',
    email: '',
    day: '',
    reason: '',
    isForSelf: false,
  });
  const successMessage = `Appointment with ${doctor.doctorName} has been successfully booked!`;

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    if (name === 'isForSelf') {
      if (checked) {
        setFormData({
          ...formData,
          fullName: profile.personName,
          gender: profile.userGender,
          dob: profile.userDob,
          phone: profile.personPhone,
          email: profile.personEmail,
          isForSelf: checked,
        });
      } else {
        setFormData({
          ...formData,
          fullName: '',
          gender: '',
          dob: '',
          phone: '',
          email: '',
          isForSelf: checked,
        });
      }
    } else {
      setFormData({
        ...formData,
        [name]: type === 'checkbox' ? checked : value,
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    setLoading(true);
    const token = localStorage.getItem('token');

    try {
      const response = await fetch(`http://localhost:8080/vital_aid/appointment/makeAppointment/${doctor.id}`, {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          patientName: formData.fullName,
          patientDob: formData.dob,
          patientPhone: formData.phone,
          patientEmail: formData.email,
          patientGender: formData.gender,
          reasonForVisit: formData.reason,
          visitDay: formData.day
        }),
      });

      if (response.ok) {
        // const appoinmentData = await response.json();
        // console.log(appoinmentData);
        setShowPopUp(true);
      } else {
        const error = await response.text();
        setMessage(error || 'Failed to book appoinment. Please try again.');
      }
    } catch (error) {
      console.error('Error updating profile:', error);
      setMessage(error.message);
    } finally {
      setLoading(false);
    }
  };

  const onClose = () => {
    navigate('/doctors');
  };

  return (
    <div className="rout-container">
      <div className="main-container-of-book-appointment-form">
        <form className="book-appointment-form" onSubmit={handleSubmit}>
          <div className="appointment-form-row">
            <div className="form-title-container">
              <h1>VITAL AID APPOINTMENT</h1>
            </div>
          </div>
          <div className="appointment-form-row appointment-checkbox-section">
            <input
              type="checkbox"
              id="appointment-for-myself"
              name="isForSelf"
              checked={formData.isForSelf}
              onChange={handleChange}
            />
            <label htmlFor="appointment-for-myself">Appointment for myself</label>
          </div>
          <div className="appointment-form-row patient-name-section">
            <div className="appointment-input-data">
              <input
                type="text"
                name="fullName"
                value={formData.fullName}
                onChange={handleChange}
                readOnly={formData.isForSelf}
                required
              />
              <label>Patient's Full Name:</label>
            </div>
          </div>
          <div className="appointment-form-row patient-gender-date-of-birth-section">
            <div className="appointment-input-data">
              <input
                type="text"
                name="gender"
                value={formData.gender}
                onChange={handleChange}
                readOnly={formData.isForSelf}
                required
              />
              <label>Gender</label>
            </div>
            <div className="appointment-input-data" id="dob">
              <input
                type="text"
                name="dob"
                value={formData.dob}
                onFocus={(e) => (e.target.type = 'date')}
                onBlur={(e) => {
                  if (!e.target.value) e.target.type = 'text';
                }}
                onChange={handleChange}
                readOnly={formData.isForSelf}
                required
              />
              <label>Date of Birth:</label>
            </div>
          </div>
          <div className="appointment-form-row patient-contact-section">
            <div className="appointment-input-data">
              <input
                type="number"
                name="phone"
                value={formData.phone}
                onChange={handleChange}
                readOnly={formData.isForSelf}
                required
              />
              <label>Phone:</label>
            </div>
            <div className="appointment-input-data">
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                readOnly={formData.isForSelf}
                required
              />
              <label>Email:</label>
            </div>
          </div>
          <div className="appointment-form-row day-selection-reason-for-visit">
            <div className="appointment-input-data">
              <select
                name="day"
                value={formData.day}
                onChange={handleChange}
                required
              >
                <option value="" disabled hidden></option>
                {doctor.consultationDays.map((day, i) => (
                  <option key={i} value={day}>
                    {day}
                  </option>
                ))}
              </select>
              <label>Day:</label>
            </div>
            <div className="appointment-input-data">
              <textarea
                name="reason"
                maxLength="100"
                value={formData.reason}
                onChange={handleChange}
                required
              ></textarea>
              <label>Reason for Visit:</label>
            </div>
          </div>
          {message && (
            <p className="message">{message}</p>
          )}
          {loading ? (
            <p className="loading-message">Booking Appoinment...</p>
          ) : (
            <div className="appointment-form-row make-an-appointment-button-section">
              <div className="appointment-input-data make-an-appointment-button">
                <input type="submit" value="Make An Appointment" />
              </div>
            </div>
          )}
        </form>
      </div>
      {showPopUp && <PopUp message={successMessage} onClose={onClose} />}
    </div>
  );
}
