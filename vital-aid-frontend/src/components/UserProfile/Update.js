import React, { useState } from 'react';
import '../../style/UserProfile/Update.scss';
import { useNavigate } from 'react-router-dom';
import { useGlobalContext } from '../../context/GlobalContext';

const Update = ({ data }) => {
    const [formData, setFormData] = useState({
        personName: data.personName || '',
        personEmail: data.personEmail || '',
        personPhone: data.personPhone || '',
        userGender: data.userGender || '',
        userDob: data.userDob || '', 
        userBloodGroup: data.userBloodGroup || '',
        userAddress: data.userAddress || '',
    });

    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState('');

    const { setIsLoggedIn } = useGlobalContext();
    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleUpdateClick = async () => {
        setLoading(true);
        setMessage('');
        const token = localStorage.getItem('token');

        try {
            const response = await fetch('http://localhost:8080/vital_aid/updateUserProfile', {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                setMessage('Profile updated successfully!');
                setIsLoggedIn(false);
                localStorage.removeItem('token');
                navigate('/login', {
                    state: { message: 'Your profile has been successfully updated. Please log in again.' },
                });
            } else {
                const error = await response.text();
                setMessage(error || 'Failed to update profile. Please try again.');
            }
        } catch (error) {
            console.error('Error updating profile:', error);
            setMessage(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="profileUpdate">
            <div className="update-heading">Edit Profile</div>
            <form>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <input
                            type="text"
                            name="personName"
                            value={formData.personName}
                            onChange={handleChange}
                            required
                        />
                        <label>Name</label>
                    </div>
                    <div className="update-section-input-Data">
                        <input
                            type="number"
                            name="personPhone"
                            value={formData.personPhone}
                            onChange={handleChange}
                            required
                        />
                        <label>Phone Number</label>
                    </div>
                </div>
                <div className="form-Row">
                    <div className="update-section-input-Data">
                        <select
                            name="userGender"
                            value={formData.userGender}
                            onChange={handleChange}
                            required
                        >
                            <option value="" disabled>
                            </option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                        <label>Gender</label>
                    </div>
                    <div className="update-section-input-Data" id="date-of-birth">
                        <input
                            type="date"
                            name="userDob"
                            value={formData.userDob}
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
                            name="personEmail"
                            value={formData.personEmail}
                            onChange={handleChange}
                            required
                        />
                        <label>Email</label>
                    </div>
                    <div className="update-section-input-Data">
                        <input
                            type="text"
                            name="userBloodGroup"
                            value={formData.userBloodGroup}
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
                            name="userAddress"
                            value={formData.userAddress}
                            onChange={handleChange}
                            required
                        />
                        <label>Address</label>
                    </div>
                </div>
            </form>
            {message && <div className="message">{message}</div>}
            {loading ? (
                <div className="loading-message">Updating profile...</div>
            ) : (
                <div className="update-btn" onClick={handleUpdateClick}>
                    Update
                </div>
            )}
        </div>
    );
};

export default Update;
