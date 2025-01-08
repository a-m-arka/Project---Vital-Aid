import React, { useState } from 'react';
import '../style/AmbulanceForm.css';
import ambulanceIcon from '../Images/ambulance_icon.png';
import PopUp from './PopUp';
import { useNavigate, useLocation } from 'react-router-dom';

const AmbulanceForm = () => {
    const [formData, setFormData] = useState({ address: '', contact: '' });
    const [submitted, setSubmitted] = useState(false);
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();
    const { state } = useLocation();
    const data = state?.ambulanceData;
    const message = `Our ambulance is dispatched and will arrive shortly at your location. Ambulance Number: ${data.id}, Driver Contact: ${data.ambulanceDriverContact}`;

    // console.log('Ambulance Data:', data);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const onClose = () => {
        navigate('/');
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        const token = localStorage.getItem('token');

        console.log(data.id);

        try {
            const response = await fetch(`http://localhost:8080/vital_aid/call_ambulance/makeCall/${data.id}`, {
                method: 'POST',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    address: formData.address,
                    contact: formData.contact
                }),
            });

            if (response.ok) {
                const callData = await response.text();
                console.log(callData);
                setSubmitted(true);
            } else {
                const error = await response.text();
                console.log(error);
                setErrorMessage('Failed to call ambulance. Please try again.');
            }
        } catch (error) {
            console.error('Error updating profile:', error);
            setErrorMessage(error.message);
        } finally {
            setLoading(false);
        }

    };

    return (
        <div className='rout-container'>
            <div className="parent-container-of-ambulance-form-main-container">
                <div className="main-container-of-ambulance-form">
                    <div className="ambulance-form-container">
                        {/* {submitted ? (
                            <div className="form-submitted-message">
                                <h3>Ambulance Request Submitted!</h3>
                                <p>We are on our way to <strong>{formData.address}</strong>. Contact: <strong>{formData.contact}</strong>.</p>
                            </div>
                        ) : ( */}
                        <form className="ambulance-form" onSubmit={handleSubmit}>
                            {errorMessage && (
                                <p style={{ color: "red", textAlign: "center", marginTop: "30px" }}>{errorMessage}</p>
                            )}
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
                                {loading ? (
                                    <p className="loading-message">Calling Ambulance...</p>
                                ) : (
                                    <div className="ambulance-form-data-input-section">
                                        <div className="inner"></div>
                                        <input type="submit" value="Call Ambulance" />
                                    </div>
                                )}
                            </div>
                        </form>
                        {/* )} */}
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
