import React, { useState } from 'react';
import '../profile/UpdateProfile.scss';
import { useNavigate } from 'react-router-dom';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';

const Signup = () => {
    const navigate = useNavigate();
    const noImg = "https://static.vecteezy.com/system/resources/previews/004/141/669/non_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";
    const [errorMessages, setErrorMessages] = useState('');
    const [img, setImg] = useState(null);
    const [formData, setFormData] = useState({
        personName: '',
        personEmail: '',
        personPhone: '',
        loginPassword: '',
        confirmPassword: '',
        hospitalName: '',
        doctorFee: '',
        specializationField: '',
        doctorSpecialization: [],
        consultationDays: [],
        consultingTime: {
            startTime: '',
            endTime: ''
        },
        doctorGender: '',
        doctorCity: ''
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
        // code below
    };


    const inputFields = [
        ["personName", "Doctor Name"],
        ["personEmail", "Doctor Email"],
        ["personPhone", "Doctor Phone"],
        ["hospitalName", "Hospital Name"],
        ["doctorFee", "Doctor Fee"],
        ["specializationField", "Specialization Field"],
        ["doctorCity", "Doctor City"]
    ];

    return (
        <div className="signup-container">
            <h2>Register as a Doctor</h2>
            <div className="bottom">
                <div className="left">
                    <img src={img ? URL.createObjectURL(img) : noImg} alt="" />
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

                        <div className="formInput">
                            <label>Password</label>
                            <input
                                type='password'
                                value={formData.loginPassword}
                                onChange={(e) => handleInputChange(e, "loginPassword")}
                            />
                        </div>

                        <div className="formInput">
                            <label>Confirm Password</label>
                            <input
                                type='password'
                                value={formData.confirmPassword}
                                onChange={(e) => handleInputChange(e, "confirmPassword")}
                            />
                        </div>

                        {loading ? (
                            <p className='loading-message'>Adding new doctor...</p>
                        ) : (
                            <button className='submit-btn' type="submit">Submit</button>
                        )}
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Signup


// setErrorMessages('');
// setLoading(true);

// const token = localStorage.getItem('adminToken');
// const { personName, personEmail, personPhone, hospitalName, doctorFee, specializationField, doctorSpecialization, consultationDays, consultingTime, doctorGender, doctorCity } = formData;
// const { hospitalFile } = img ? { hospitalFile: img } : {};

// try {
//     const multipartFormData = new FormData();

//     // Adding JSON data as a blob to the FormData object
//     multipartFormData.append(
//         'doctorDTO',
//         new Blob(
//             [
//                 JSON.stringify({
//                     personName,
//                     personEmail,
//                     personPhone,
//                     hospitalName,
//                     doctorFee,
//                     specializationField,
//                     doctorSpecialization,
//                     consultationDays,
//                     consultingTime,
//                     doctorGender,
//                     doctorCity
//                 })
//             ],
//             { type: 'application/json' }
//         )
//     );

//     // Adding the file to the FormData object
//     if (hospitalFile) {
//         multipartFormData.append('file', hospitalFile);
//     }

//     const response = await fetch('http://localhost:8080/vital_aid/doctors/registerDoctor', {
//         method: 'POST',
//         headers: {
//             'Authorization': `Bearer ${token}`,
//             // Do not set Content-Type header here, as FormData automatically handles it
//         },
//         body: multipartFormData
//     });

//     if (response.ok) {
//         console.log('Success');
//         navigate('/doctor');
//     } else {
//         const errorData = await response.json();
//         setErrorMessages(errorData.message || 'An error occurred');
//     }
// } catch (error) {
//     console.log('Error:', error);
//     setErrorMessages('Failed to submit form');
// } finally {
//     setLoading(false);
// }