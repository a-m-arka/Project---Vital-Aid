import React, { useState } from 'react';
import '../../style/UserProfile/ChangePhoto.scss';

const ChangePhoto = ({ data, updateUserData }) => {
    const [img, setImg] = useState(data.profileImageUrl);
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState('');
    const [newImgUploaded, setNewImgUploaded] = useState(false);

    const handleImgUpload = (event) => {
        const uploadedImg = event.target.files[0];
        const newImgUrl = URL.createObjectURL(uploadedImg);
        setImg({ file: uploadedImg, preview: newImgUrl }); // Set both file and preview URL
        setNewImgUploaded(true);
    };

    const handleUpdateClick = async () => {
        if (!newImgUploaded) {
            setMessage('Please upload a new image');
            return;
        }

        setLoading(true); // Start loading
        const token = localStorage.getItem('token');
        const formData = new FormData();
        formData.append('file', img.file);

        try {
            const response = await fetch(
                'http://localhost:8080/vital_aid/uploadImage/userProfilePhoto',
                {
                    method: 'PUT',
                    body: formData,
                    headers: {
                        Authorization: `Bearer ${token}`,
                        // 'Content-Type': 'multipart/form-data',
                    },
                }
            );

            if (response.ok) {
                // const result = await response.text();
                setMessage('Image uploaded successfully!');
                updateUserData({ ...data, profileImageUrl: img.preview }); // Update parent
            } else {
                const error = await response.text();
                setMessage(error || 'Failed to update profile photo. Please try again.');
            }
        } catch (error) {
            console.error(error);
            setMessage('An error occurred while uploading the image.');
        } finally {
            setLoading(false); // Stop loading
            setNewImgUploaded(false);
        }
    };

    return (
        <div className="changePhoto">
            <div className="changePhoto-heading">Change Profile Picture</div>
            <img src={img.preview || img} alt="Profile" />
            {!loading && (
                <div className="imgUpload">
                    <label htmlFor="img">
                        <i className="fa-solid fa-upload icon"></i>
                        Upload Image
                    </label>
                    <input
                        type="file"
                        id="img"
                        style={{ display: 'none' }}
                        onChange={handleImgUpload}
                    />
                </div>
            )}
            {message && <div className="message">{message}</div>}
            {loading ? (
                <div className="loading-message">Updating profile photo...</div>
            ) : (
                <div className="update-btn" onClick={handleUpdateClick}>
                    Update
                </div>
            )}

        </div>
    );
};

export default ChangePhoto;
