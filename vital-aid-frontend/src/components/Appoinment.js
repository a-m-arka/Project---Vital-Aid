import React from 'react';
import { useLocation } from 'react-router-dom';
import '../style/Appoinment.css';

export default function Appoinment() {
  const { state } = useLocation();
  const doctor = state?.doctor;

  return (
    <div className="rout-container">
      <div className="main-container-of-book-appointment-form">
        <form className="book-appointment-form" action="#">
          <div className="appointment-form-row">
            <div className="form-title-container">
              <h1>VITAL AID APPOINTMENT</h1>
            </div>
          </div>
          <div class="appointment-form-row appointment-checkbox-section">
            <input type="checkbox" id="appointment-for-myself"/>
              <label htmlFor="appointment-for-myself">Appointment for myself</label>
          </div>
          <div className="appointment-form-row patient-name-section">
            <div className="appointment-input-data">
              <input type="text" required />
              <label>Patient's Full Name:</label>
            </div>
          </div>
          <div className="appointment-form-row patient-gender-date-of-birth-section">
            <div className="appointment-input-data">
              <input type="text" required />
              <label>Gender</label>
            </div>
            <div className="appointment-input-data" id="dob">
              <input
                type="text"
                name="date"
                placeholder=""
                onFocus={(e) => (e.target.type = 'date')}
                onBlur={(e) => {
                  if (!e.target.value) e.target.type = 'text';
                }}
                required
              />
              <label>Date of Birth:</label>
            </div>
          </div>
          <div className="appointment-form-row patient-contact-section">
            <div className="appointment-input-data">
              <input type="number" required />
              <label>Phone:</label>
            </div>
            <div className="appointment-input-data">
              <input type="email" required />
              <label>Email:</label>
            </div>
          </div>
          <div className="appointment-form-row day-selection-reason-for-visit">
            <div className="appointment-input-data">
              <select required>
                <option value="" disabled selected hidden></option>
                {doctor.consultationDays.map((day, i) => (
                  <option key={i} value={day}>
                    {day}
                  </option>
                ))}
              </select>
              <label>Day:</label>
            </div>
            <div className="appointment-input-data">
              <textarea maxLength="100" required></textarea>
              <label>Reason for Visit:</label>
            </div>
          </div>
          <div className="appointment-form-row make-an-appointment-button-section">
            <div className="appointment-input-data make-an-appointment-button">
              <input type="submit" value="Make An Appointment" />
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}
