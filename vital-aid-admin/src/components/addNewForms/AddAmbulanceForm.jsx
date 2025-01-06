import React, { useState } from 'react';
import './newForm.scss';
import { useNavigate } from 'react-router-dom';

const AddAmbulanceForm = () => {
  const navigate = useNavigate();
  const [errorMessages, setErrorMessages] = useState('');
  const [formData, setFormData] = useState({
    id: '',
    ambulanceDriverContact: ''
  });
  const [loading, setLoading] = useState(false);  // Added loading state

  const handleInputChange = (event, field) => {
    const { value } = event.target;
    setFormData({
      ...formData,
      [field]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessages('');
    setLoading(true);  // Set loading to true when the form is submitted

    const token = localStorage.getItem('adminToken');
    const formDataToSend = {
      ...formData
    };

    try {
      const response = await fetch('http://localhost:8080/vital_aid/ambulance/registerAmbulance', {
        method: 'POST',
        body: JSON.stringify(formDataToSend),
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`
        }
      });

      if (response.ok) {
        console.log('Success');
        // Navigate to a success or list page after successful form submission
        navigate('/ambulance');  // Redirect to the ambulance page (or any other page you prefer)
      } else {
        const errorData = await response.text();
        setErrorMessages(errorData.message || 'An error occurred');
      }
    } catch (error) {
      console.log('Error:', error);
      setErrorMessages('Failed to submit form');
    } finally {
      setLoading(false);  // Set loading to false after request completes
    }
  };

  const inputFields = [
    ["id", "Ambulance Number Plate"],
    ["ambulanceDriverContact", "Driver Contact"]
  ];

  return (
    <div className="bottom">
      <div className="right">
        <div className="form-container">
          <p className="error-message">{errorMessages}</p>
          <form onSubmit={handleSubmit}>
            {inputFields.map(([key, label]) => (
              <div className="formInput" key={key}>
                <label>{label}</label>
                <input
                  type="text"
                  value={formData[key]}
                  onChange={(event) => handleInputChange(event, key)}
                />
              </div>
            ))}

            {loading ? (
              <p className='loading-message'>Adding new ambulance...</p>
            ) : (
              <button type="submit">Submit</button>
            )}
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddAmbulanceForm;
