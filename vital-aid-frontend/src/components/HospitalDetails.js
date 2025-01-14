import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import '../style/HospitalDetails.css';

export default function HospitalDetails() {
    const { state } = useLocation();
    const hospital = state?.hospital;
    const navigate = useNavigate();
    const [doctorData, setDoctorData] = useState([]);

    const fetchDoctors = async () => {
        if (!hospital) return; // Ensure `hospital` exists before fetching
        const url = `http://localhost:8080/vital_aid/hospitals/allDoctors/${hospital.id}`;

        try {
            const response = await fetch(url, {
                method: 'GET',
            });

            if (response.ok) {
                const data = await response.json();
                // console.log(data);
                setDoctorData(data);
            }
        } catch (err) {
            console.error('Error fetching doctors:', err.message);
        }
    };

    useEffect(() => {
        fetchDoctors();
    }, [hospital]);

    if (!hospital) {
        return <p>Hospital details not available.</p>;
    }

    const handleViewDoctor = () => {
        // setDoctorView(true);
        navigate('/hospital_doctors', { state: { doctorData } });
    };

    return (
        <div className="rout-container">
            <div className="hospital-profile-main-container">
                <div className="hospital-name-description-container">
                    <div className="hospital-name-contact-address-section">
                        <div className="hospital-name-section">
                            <span className="name-of-the-hospital">
                                {hospital.hospitalName}
                            </span>
                        </div>
                        <div className="hospital-address-section">
                            <span className="address-of-the-hospital">
                                {hospital.hospitalLocation}
                            </span>
                        </div>
                        <div className="hopital-contact-phone-section">
                            <span className="phone-number">
                                {hospital.hospitalContact}
                            </span>
                        </div>
                        <div className="hospital-contact-email-section">
                            <span className="hospital-email">
                                {hospital.hospitalEmail}
                            </span>
                        </div>
                    </div>
                </div>

                <div className="hospital-facilities-container">
                    <div className="doctor-number-total-bed-section">
                        <div className="doctor-number-section">
                            <div className="total-doctors">
                                <span className="doctor-number numbers">
                                    {hospital.hospitalTotalDoctor}
                                </span>
                            </div>
                            <span className="heading">Total Doctors</span>
                        </div>

                        <div className="total-beds-section">
                            <div className="total-beds">
                                <span className="bed-number numbers">
                                    {hospital.totalGeneralBeds}
                                </span>
                            </div>
                            <span className="heading">Total Beds</span>
                        </div>
                    </div>

                    <div className="hospital-equipment-facilities-section">
                        <div className="icu-bed-number-section">
                            <div className="total-icu-beds">
                                <span className="icu-bed-number numbers">
                                    {hospital.totalIcuBeds}
                                </span>
                            </div>
                            <span className="heading">ICU Beds</span>
                        </div>

                        <div className="ventilator-number-section">
                            <div className="total-ventilators">
                                <span className="ventilator-number numbers">
                                    {hospital.totalVentilators}
                                </span>
                            </div>
                            <span className="heading">Ventilators</span>
                        </div>
                    </div>

                    <div className="other-facilities">
                        <h3 className="title">Other Facilities</h3>
                        <ul>
                            {hospital.hospitalFacilities.map((facility, index) => (
                                <li key={index}>{facility}</li>
                            ))}
                        </ul>
                    </div>

                    <div className="view-doctor-btn" onClick={handleViewDoctor}>
                        View Doctors
                    </div>

                </div>

                <div className="hospital-photo-container">
                    <div className="image-box">
                        <img src={hospital.hospitalPhotoUrl} alt="Hospital" />
                    </div>
                </div>


            </div>
        </div>
    );
}
