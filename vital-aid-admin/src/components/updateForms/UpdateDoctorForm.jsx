import React, { useState } from 'react'
import '../addNewForms/newForm.scss'
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import { useNavigate } from 'react-router-dom';

const UpdateDoctorForm = ({data}) => {
  const navigate = useNavigate();
  const prevImg = data.doctorPhotoUrl;
  const [errorMessages, setErrorMessages] = useState('');
  const [img, setImg] = useState(null);
  const [formData, setFormData] = useState({
    doctorName: data.doctorName,
    doctorEmail: data.doctorEmail,
    doctorPhone: data.doctorPhone,
    hospitalName: data.hospitalName,
    doctorFee: data.doctorFee,
    specializationField: data.specializationField,
    doctorSpecialization: data.doctorSpecialization,
    consultationDays: data.consultationDays,
    consultingTime: {
      startTime: data.consultingTime.startTime,
      endTime: data.consultingTime.endTime
    },
    doctorGender: data.doctorGender,
    doctorCity: data.doctorCity
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

  const handleCheckboxChange = (e, day) => {
    const isChecked = e.target.checked;
    setFormData(prev => ({
      ...prev,
      consultationDays: isChecked
        ? [...prev.consultationDays, day]
        : prev.consultationDays.filter(d => d !== day)
    }));
  };

  const handleSpecializationChange = (event) => {
    const value = event.target.value;
    setFormData({
      ...formData,
      doctorSpecialization: value.split(',').map(specialization => specialization.trim())
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessages('');
    setLoading(true);

    const token = localStorage.getItem('adminToken');
    const { doctorName, doctorEmail, doctorPhone, hospitalName, doctorFee, specializationField, doctorSpecialization, consultationDays, consultingTime, doctorGender, doctorCity } = formData;
    const { hospitalFile } = img ? { hospitalFile: img } : {};

    try {
      const multipartFormData = new FormData();

      // Adding JSON data as a blob to the FormData object
      multipartFormData.append(
        'doctorDTO',
        new Blob(
          [
            JSON.stringify({
              doctorName,
              doctorEmail,
              doctorPhone,
              hospitalName,
              doctorFee,
              specializationField,
              doctorSpecialization,
              consultationDays,
              consultingTime,
              doctorGender,
              doctorCity
            })
          ],
          { type: 'application/json' }
        )
      );

      // Adding the file to the FormData object
      if (hospitalFile) {
        multipartFormData.append('file', hospitalFile);
      }

      const response = await fetch(`http://localhost:8080/vital_aid/doctors/updateDoctor/${data.id}`, {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${token}`,
          // Do not set Content-Type header here, as FormData automatically handles it
        },
        body: multipartFormData
      });

      if (response.ok) {
        console.log('Success');
        navigate('/doctor');
      } else {
        const errorData = await response.text();
        setErrorMessages(errorData || 'An error occurred');
      }
    } catch (error) {
      console.log('Error:', error);
      setErrorMessages('Failed to submit form');
    } finally {
      setLoading(false);
    }
  };


  const inputFields = [
    ["doctorName", "Doctor Name"],
    ["doctorEmail", "Doctor Email"],
    ["doctorPhone", "Doctor Phone"],
    ["hospitalName", "Hospital Name"],
    ["doctorFee", "Doctor Fee"],
    ["specializationField", "Specialization Field"],
    ["doctorCity", "Doctor City"]
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
            <label>Doctor Specialization</label>
            <input
              type="text"
              value={formData.doctorSpecialization.join(', ')}
              onChange={handleSpecializationChange}
              placeholder="Enter specializations separated by commas"
            />
          </div>

          <div className="formInput">
            <label>Gender</label>
            <select
              value={formData.doctorGender}
              onChange={(e) => handleInputChange(e, "doctorGender")}
            >
              <option value="">Select Gender</option>
              <option value="Male">Male</option>
              <option value="Female">Female</option>
            </select>
          </div>

          <div className="formInput">
            <label>Consultation Days</label>
            <div className="checkboxGroup">
              {["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"].map((day) => (
                <label key={day}>
                  <input
                    type="checkbox"
                    value={day}
                    checked={formData.consultationDays.includes(day)}
                    onChange={(e) => handleCheckboxChange(e, day)}
                  />
                  {day}
                </label>
              ))}
            </div>
          </div>

          <div className="formInput">
            <label>Start Time</label>
            <input
              type="time"
              value={formData.consultingTime.startTime}
              onChange={(e) => setFormData({
                ...formData,
                consultingTime: {
                  ...formData.consultingTime,
                  startTime: e.target.value
                }
              })}
            />
          </div>

          <div className="formInput">
            <label>End Time</label>
            <input
              type="time"
              value={formData.consultingTime.endTime}
              onChange={(e) => setFormData({
                ...formData,
                consultingTime: {
                  ...formData.consultingTime,
                  endTime: e.target.value
                }
              })}
            />
          </div>

          {loading ? (
            <p className='loading-message'>Updating doctor profile...</p>
          ) : (
            <button type="submit">Update</button>
          )}
        </form>
      </div>
    </div>
  );
}

export default UpdateDoctorForm
