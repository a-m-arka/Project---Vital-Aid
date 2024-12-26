import React, { useState } from 'react';
import '../../style/UserProfile/Update.scss';

const Update = ({ data, updateUserData }) => {
    const [formData, setFormData] = useState({
        name: data.name || '',
        contact: data.contact || '',
        gender: data.gender || '',
        dateOfBirth: data.dateOfBirth || '', // Assume format 'YYYY-MM-DD'
        email: data.email || '',
        bloodGroup: data.bloodGroup || '',
        address: data.address || '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleUpdateClick = () => {
        updateUserData(formData); // Update parent data
    };

    return (
        <div className="profileUpdate">
            <div className="update-heading">Edit Profile</div>
            <form>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                        <label>Name</label>
                    </div>
                    <div className="update-section-input-Data">
                        <input
                            type="number"
                            name="contact"
                            value={formData.contact}
                            onChange={handleChange}
                            required
                        />
                        <label>Phone Number</label>
                    </div>
                </div>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input
                            type="text"
                            name="gender"
                            value={formData.gender}
                            onChange={handleChange}
                            required
                        />
                        <label>Gender</label>
                    </div>
                    <div className="update-section-input-Data" id="date-of-birth">
                        <input
                            type="date"
                            name="dateOfBirth"
                            value={formData.dateOfBirth}
                            onChange={handleChange}
                            required
                        />
                        <label>Date of Birth</label>
                    </div>
                </div>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                        <label>Email</label>
                    </div>
                    <div className="update-section-input-Data">
                        <input
                            type="text"
                            name="bloodGroup"
                            value={formData.bloodGroup}
                            onChange={handleChange}
                            required
                        />
                        <label>Blood Group</label>
                    </div>
                </div>
                <div className="form-Row adressText">
                    <div className="update-section-input-Data">
                        <input
                            type="text"
                            name="address"
                            value={formData.address}
                            onChange={handleChange}
                            required
                        />
                        <label>Address</label>
                    </div>
                </div>
            </form>
            <div className="update-btn" onClick={handleUpdateClick}>
                Update
            </div>
        </div>
    );
};

export default Update;

