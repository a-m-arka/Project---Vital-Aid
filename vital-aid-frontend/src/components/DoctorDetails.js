import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import '../style/DoctorDetails.css';

export default function DoctorDetails() {
    const { state } = useLocation();
    const doctor = state?.doctor;
    const navigate = useNavigate();

    const handleAppoinment = (doctor) => {
        navigate('/appoinment', { state: { doctor } });
    };

    if (!doctor) {
        return <p>Doctor details not available.</p>;
    }

    return (
        <div className="rout-container">
            <div className="parent-container-of-doctor-profile-main-container">
                <div className="doctor-profile-main-container">
                    <div className="doctor-profile-picture-container">
                        <img src={doctor.img} alt="doctor-profile-pic" />
                    </div>
                    <div className="doctor-profile-details-container">
                        <div className="doctor-profile-details">
                            <div className="doctor-name-section">
                                <span className="doctor-name-span">{doctor.name}</span>
                            </div>

                            <div className="doctor-degree-section">
                                <span className="degree-span">{doctor.degree || "N/A"}</span>
                            </div>

                            <div className="doctor-speciality-section">
                                <span className="speciality-span">{doctor.speciality}</span>
                            </div>
                            <div className="doctor-hospital-name-section">
                                <span className="hospital-name-span">{doctor.hospital}</span>
                            </div>
                            <div className="doctor-address-section">
                                <span className="address-value">{doctor.location}</span>
                            </div>
                        </div>

                        <div className="consultation-days-section">
                            <div className="consultation-day-heading">Consultation Days:</div>
                            <span className="consultation-day-values">
                                {doctor.consultationDays ? doctor.consultationDays.join(', ') : "Not available"}
                            </span>
                        </div>

                        <div className="consultation-time-fees-section">
                            <div className="consultation-time-section">
                                <div className="consultation-time-heading">Time:</div>
                                <span className="consultation-time-values">{doctor.consultationTime || "Not available"}</span>
                            </div>
                            <div className="consultation-fees-section">
                                <div className="consultation-fees-heading">Fees:</div>
                                <span className="consultation-fees-values">{doctor.fees ? `${doctor.fees}` : "Not available"}</span>
                            </div>
                        </div>

                        <div className="doctor-contact-information-section">
                            <div className="contact-phone-number-section">
                                <div className="contact-headings">Contact:</div>
                                <span className="contact-phone-number">{doctor.contact || "Not available"}</span>
                            </div>
                            <div className="contact-email-section">
                                <div className="contact-headings">Email:</div>
                                <span className="contact-email">{doctor.email || "Not available"}</span>
                            </div>
                        </div>

                        <div className="doctor-book-appointment-button">
                            <button onClick={() => handleAppoinment(doctor)}>Book Appointment</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
