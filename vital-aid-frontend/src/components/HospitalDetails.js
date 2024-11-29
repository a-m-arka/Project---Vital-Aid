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
                                {hospital.name}
                            </span>
                        </div>
                        <div className="hospital-address-section">
                            <span className="address-of-the-hospital">
                                {hospital.address}
                            </span>
                        </div>
                        <div className="hopital-contact-phone-section">

                            <span className="phone-number">
                                {hospital.phone}
                            </span>
                        </div>
                        <div className="hospital-contact-email-section">
                            <span className="hospital-email">
                                {hospital.email}
                            </span>
                        </div>
                    </div>
                </div>

                <div className="hospital-facilities-container">
                    <div className="doctor-number-total-bed-section">


                        <div className="doctor-number-section">
                            <div className="total-doctors">
                                <span className="doctor-number numbers">{hospital.details.totalDoctors}</span>
                            </div>
                            <span className="heading">
                                Total Doctors
                            </span>
                        </div>


                        <div className="total-beds-section">
                            <div className="total-beds">
                                <span className="bed-number numbers">{hospital.details.totalBeds}</span>
                            </div>
                            <span className="heading">
                                Total Beds
                            </span>
                        </div>


                    </div>

                    <div className="hospital-equipment-facilities-section">

                        <div className="icu-bed-number-section">
                            <div className="total-icu-beds">
                                <span className="icu-bed-number numbers">{hospital.details.icuBeds}</span>
                            </div>
                            <span className="heading">
                                ICU Beds
                            </span>
                        </div>

                        <div className="mri-machine-number-section">
                            <div className="total-mri-machines">
                                <span className="mri-machine-number numbers">{hospital.details.mriMachines}</span>
                            </div>
                            <span className="heading">
                                MRI
                            </span>
                        </div>

                        <div className="xray-machine-number-section">
                            <div className="total-xray-machines">
                                <span className="xray-machine-number numbers">{hospital.details.xRayMachines}</span>
                            </div>
                            <span className="heading">
                                X-Ray
                            </span>
                        </div>

                        <div className="ventilator-number-section">
                            <div className="total-ventilators">
                                <span className="ventilator-number numbers">{hospital.details.ventilators}</span>
                            </div>
                            <span className="heading">
                                Ventilators
                            </span>
                        </div>
                    </div>
                </div>

                <div className="hospital-photo-container">
                    <div className="image-box">
                        <img src={hospital.img}/>
                    </div>
                </div>
            </div>
        </div>
    );
}
