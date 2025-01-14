import React, { useState } from 'react'
import '../addNewForms/newForm.scss'
import { useNavigate } from 'react-router-dom';

const UpdateAmbulanceForm = ({ data }) => {
  const navigate = useNavigate();
  const [errorMessages, setErrorMessages] = useState('');
  const [formData, setFormData] = useState({
    id: data.id,
    ambulanceDriverContact: data.ambulanceDriverContact
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
      const response = await fetch(`http://localhost:8080/vital_aid/ambulance/updateAmbulanceDetails/${data.id}`, {
        method: 'PUT',
        body: JSON.stringify(formDataToSend),
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`
        }
      });

      if (response.ok) {
        console.log('Success');
        navigate('/ambulance');
      } else {
        const errorData = await response.text();
        setErrorMessages(errorData || 'An error occurred');
      }
    } catch (error) {
      console.log('Error:', error);
      setErrorMessages(error || 'Failed to submit form');
    } finally {
      setLoading(false);  // Set loading to false after request completes
    }
  };

  const inputFields = [
    ["id", "Ambulance Number Plate", true], // Added a flag to indicate if the field should be disabled
    ["ambulanceDriverContact", "Driver Contact", false]
  ];

  return (
    <div className="bottom">
      <div className="right">
        <div className="form-container">
          <p className="error-message">{errorMessages}</p>
          <form onSubmit={handleSubmit}>
            {inputFields.map(([key, label, isDisabled]) => (
              <div className="formInput" key={key}>
                <label>{label}</label>
                <input
                  type="text"
                  value={formData[key]}
                  onChange={(event) => handleInputChange(event, key)}
                  disabled={isDisabled}
                />
              </div>
            ))}

            {loading ? (
              <p className='loading-message'>Updating Ambulance Data...</p>
            ) : (
              <button type="submit">Update</button>
            )}
          </form>
        </div>
      </div>
    </div>
  );

}

export default UpdateAmbulanceForm
