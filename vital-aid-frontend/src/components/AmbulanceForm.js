import React, { useState } from 'react';
import '../style/AmbulanceForm.css';
import ambulanceIcon from '../Images/ambulance_icon.png';
import PopUp from './PopUp';
import { useNavigate, useLocation } from 'react-router-dom';

const AmbulanceForm = () => {
    const message = 'Our ambulance is dispatched and will arrive shortly at your location.';
    const [formData, setFormData] = useState({ address: '', contact: '' });
    const [submitted, setSubmitted] = useState(false);
    const navigate = useNavigate();
    const { state } = useLocation();
    const data = state?.ambulanceData;

    console.log('Ambulance Data:', data);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const onClose = () => {
        navigate('/');
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        // console.log('Form Submitted:', formData);
        setSubmitted(true);
        // You can add functionality here, such as sending the data to a backend
    };

    return (
        <div className='rout-container'>
            <div className="parent-container-of-ambulance-form-main-container">
                <div className="main-container-of-ambulance-form">
                    <div className="ambulance-form-container">
                        {submitted ? (
                            <div className="form-submitted-message">
                                <h3>Ambulance Request Submitted!</h3>
                                <p>We are on our way to <strong>{formData.address}</strong>. Contact: <strong>{formData.contact}</strong>.</p>
                            </div>
                        ) : (
                            <form className="ambulance-form" onSubmit={handleSubmit}>
                                <div className="ambulance-form-row">
                                    <div className="ambulance-form-data-input-section">
                                        <input
                                            type="text"
                                            name="address"
                                            value={formData.address}
                                            onChange={handleInputChange}
                                            required
                                        />
                                        <label>Address</label>
                                    </div>
                                </div>
                                <div className="ambulance-form-row">
                                    <div className="ambulance-form-data-input-section">
                                        <input
                                            type="number"
                                            name="contact"
                                            value={formData.contact}
                                            onChange={handleInputChange}
                                            required
                                        />
                                        <label>Contact</label>
                                    </div>
                                </div>
                                <div className="ambulance-form-row ambulance-form-button-container">
                                    <div className="ambulance-form-data-input-section">
                                        <div className="inner"></div>
                                        <input type="submit" value="Call Ambulance" />
                                    </div>
                                </div>
                            </form>
                        )}
                    </div>
                    <div className="right-side-icon-design-container">
                        <div className="ambulance-icon-container">
                            <img src={ambulanceIcon} alt="Ambulance Icon" />
                        </div>
                    </div>
                </div>
            </div>
            {submitted && <PopUp message={message} onClose={onClose} />}
        </div>
    );
}

export default AmbulanceForm
