import React, { useState } from 'react'
import '../addNewForms/newForm.scss'
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import { useNavigate } from 'react-router-dom';

const UpdateHospitalForm = ({ data }) => {
  const navigate = useNavigate();
  const prevImg = data.hospitalPhotoUrl;
  const [errorMessages, setErrorMessages] = useState('');
  const [img, setImg] = useState(null);
  const [formData, setFormData] = useState({
    hospitalName: data.hospitalName || '',
    hospitalLocation: data.hospitalLocation || '',
    hospitalContact: data.hospitalContact || '',
    hospitalEmail: data.hospitalEmail || '',
    totalGeneralBeds: data.totalGeneralBeds || '',
    totalIcuBeds: data.totalIcuBeds || '',
    totalVentilators: data.totalVentilators || '',
    hospitalFacilities: data.hospitalFacilities || [],
  });
  const [loading, setLoading] = useState(false);

  const handleImgUpload = (event) => {
    setImg(event.target.files[0]);
  };

  const handleInputChange = (event, field) => {
    const { value } = event.target;
    setFormData({
      ...formData,
      [field]: value
    });
  };

  const handleFacilitiesChange = (event) => {
    const value = event.target.value;
    setFormData({
      ...formData,
      hospitalFacilities: value.split(',').map(facility => facility.trim())
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessages('');
    setLoading(true);

    const token = localStorage.getItem('adminToken');
    const { hospitalName, hospitalLocation, hospitalContact, hospitalEmail, totalGeneralBeds, totalIcuBeds, totalVentilators, hospitalFacilities } = formData;
    const { hospitalFile } = img ? { hospitalFile: img } : {};

    try {
      const multipartFormData = new FormData();

      // Adding JSON data as a blob to the FormData object
      multipartFormData.append(
        'hospitalDTO',
        new Blob(
          [
            JSON.stringify({
              hospitalName,
              hospitalLocation,
              hospitalContact,
              hospitalEmail,
              totalGeneralBeds: parseInt(totalGeneralBeds, 10),
              totalIcuBeds: parseInt(totalIcuBeds, 10),
              totalVentilators: parseInt(totalVentilators, 10),
              hospitalFacilities
            })
          ],
          { type: 'application/json' }
        )
      );

      // Adding the file to the FormData object
      if (hospitalFile) {
        multipartFormData.append('file', hospitalFile);
      }

      const response = await fetch(`http://localhost:8080/vital_aid/hospitals/updateHospital/${data.id}`, {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${token}`
        },
        body: multipartFormData
      });

      if (response.ok) {
        console.log('Success');
        navigate('/hospital');
        // Handle success (e.g., clear form, show success message, etc.)
      } else {
        const errorData = await response.text();
        setErrorMessages(errorData || 'An error occurred');
      }
    } catch (error) {
      console.log('Error:', error);
      setErrorMessages(error || 'Failed to submit form');
    } finally {
      setLoading(false);
    }
  };

  const inputFields = [
    ["hospitalName", "Hospital Name"],
    ["hospitalLocation", "Hospital Location"],
    ["hospitalContact", "Hospital Contact"],
    ["hospitalEmail", "Hospital Email"],
    ["totalGeneralBeds", "Total General Beds"],
    ["totalIcuBeds", "Total ICU Beds"],
    ["totalVentilators", "Total Ventilators"]
  ];

  return (
    <div className="bottom">
      <div className="left">
        <img src={img ? URL.createObjectURL(img) : prevImg} alt="" />
        <div className="imgUpload">
          <label htmlFor='img'>
            <AddPhotoAlternateIcon className='icon' />
            Upload Image
          </label>
          <input type="file" id='img' style={{ display: "none" }} onChange={handleImgUpload} />
        </div>
      </div>
      <div className="right">
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

          <div className="formInput">
            <label>Hospital Facilities</label>
            <input
              type="text"
              value={formData.hospitalFacilities.join(', ')}
              onChange={handleFacilitiesChange}
              placeholder="Enter facilities separated by commas"
            />
          </div>

          {loading ? (
            <p className='loading-message'>Updating Hospital Data...</p>
          ) : (
            <button type="submit">Update</button>
          )}
        </form>
      </div>
    </div>
  );
}

export default UpdateHospitalForm
