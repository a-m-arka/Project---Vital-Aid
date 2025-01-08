import React from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import '../style/DoctorDetails.css';

export default function DoctorDetails() {
    const { state } = useLocation();
    const doctor = state?.doctor;
    const navigate = useNavigate();

    const handleAppoinment = (doctor) => {
        navigate('/appointment', { state: { doctor } });
    };

    // Function to convert 24-hour format to 12-hour format
    const convertTo12HourFormat = (time) => {
        const [hour, minute] = time.split(':');
        const period = +hour < 12 ? 'AM' : 'PM';
        const convertedHour = +hour % 12 || 12;
        return `${convertedHour}:${minute} ${period}`;
    };

    if (!doctor) {
        return <p>Doctor details not available.</p>;
    }

    return (
        <div className="rout-container">
            <div className="parent-container-of-doctor-profile-main-container">
                <div className="doctor-profile-main-container">
                    <div className="doctor-profile-picture-container">
                        <img src={doctor.doctorPhotoUrl} alt="doctor-profile-pic" />
                    </div>
                    <div className="doctor-profile-details-container">
                        <div className="doctor-profile-details">
                            <div className="doctor-name-section">
                                <span className="doctor-name-span">{doctor.doctorName}</span>
                            </div>

                            <div className="doctor-speciality-section">
                                <span className="speciality-span">{doctor.doctorSpecialization.join(', ')}</span>
                            </div>

                            <div className="doctor-hospital-name-section">
                                <span className="hospital-name-span">{doctor.hospitalName}</span>
                            </div>

                            <div className="doctor-address-section">
                                <span className="address-value">{doctor.doctorCity}</span>
                            </div>
                        </div>

                        <div className="consultation-days-section">
                            <div className="consultation-day-heading">Consultation Days:</div>
                            <span className="consultation-day-values">
                                {doctor.consultationDays ? doctor.consultationDays.join(', ') : 'Not available'}
                            </span>
                        </div>

                        <div className="consultation-time-fees-section">
                            <div className="consultation-time-section">
                                <div className="consultation-time-heading">Time:</div>
                                <span className="consultation-time-values">
                                    {doctor.consultingTime
                                        ? `${convertTo12HourFormat(doctor.consultingTime.startTime)} - ${convertTo12HourFormat(doctor.consultingTime.endTime)}`
                                        : 'Not available'}
                                </span>
                            </div>
                            <div className="consultation-fees-section">
                                <div className="consultation-fees-heading">Fees:</div>
                                <span className="consultation-fees-values">{doctor.doctorFee ? `BDT ${doctor.doctorFee}` : 'Not available'}</span>
                            </div>
                        </div>

                        <div className="doctor-contact-information-section">
                            <div className="contact-phone-number-section">
                                <div className="contact-headings">Contact:</div>
                                <span className="contact-phone-number">{doctor.doctorPhone || 'Not available'}</span>
                            </div>
                            <div className="contact-email-section">
                                <div className="contact-headings">Email:</div>
                                <span className="contact-email">{doctor.doctorEmail || 'Not available'}</span>
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
