import React from 'react';
import { useLocation } from 'react-router-dom';
import '../style/HospitalDetails.css';

export default function HospitalDetails() {
    const { state } = useLocation();
    const hospital = state?.hospital;

    if (!hospital) {
        return <p>Hospital details not available.</p>;
    }

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
                                <span className="doctor-number numbers">{hospital.hospitalTotalDoctor}</span>
                            </div>
                            <span className="heading">
                                Total Doctors
                            </span>
                        </div>


                        <div className="total-beds-section">
                            <div className="total-beds">
                                <span className="bed-number numbers">{hospital.totalGeneralBeds}</span>
                            </div>
                            <span className="heading">
                                Total Beds
                            </span>
                        </div>


                    </div>

                    <div className="hospital-equipment-facilities-section">

                        <div className="icu-bed-number-section">
                            <div className="total-icu-beds">
                                <span className="icu-bed-number numbers">{hospital.totalIcuBeds}</span>
                            </div>
                            <span className="heading">
                                ICU Beds
                            </span>
                        </div>

                        <div className="ventilator-number-section">
                            <div className="total-ventilators">
                                <span className="ventilator-number numbers">{hospital.totalVentilators}</span>
                            </div>
                            <span className="heading">
                                Ventilators
                            </span>
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

                </div>

                <div className="hospital-photo-container">
                    <div className="image-box">
                        <img src={hospital.hospitalPhotoUrl} />
                    </div>
                </div>
            </div>
        </div>
    );
}
