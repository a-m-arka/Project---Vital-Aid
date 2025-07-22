import React, { useState } from 'react'
import './UpdateProfile.scss';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import { useGlobalContext } from '../../context/GlobalContext';
import { useNavigate } from 'react-router-dom';

// import { doctorData } from '../../temporaryData/doctorData';

const UpdateImage = () => {
    const navigate = useNavigate();
    const { profile } = useGlobalContext();
    const data = profile;
    const prevImg = data.doctorProfileImageUrl;
    const [errorMessages, setErrorMessages] = useState('');
    const [img, setImg] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleImgUpload = (event) => {
        setImg(event.target.files[0]);
    };

    const handleSave = async () => {
        setLoading(true); // Start loading
        const token = localStorage.getItem('doctorToken');
        const formData = new FormData();
        formData.append('file', img);

        try {
            const response = await fetch(
                'http://localhost:8080/vital_aid/uploadImage/doctorProfilePhoto',
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
                setLoading(false);
                navigate("/profile");
                window.location.reload();
                console.log("Updated photo")
                // const result = await response.text();
                // setMessage('Image uploaded successfully!');
                // updateUserData({ ...data, profileImageUrl: img.preview }); // Update parent
            } else {
                console.log("Failed to update image");
                const error = await response.text();
                setErrorMessages(error || 'Failed to update profile photo. Please try again.');
            }
        } catch (error) {
            console.error(error);
            setErrorMessages('An error occurred while uploading the image.');
        } finally {
            setLoading(false); // Stop loading
            // setNewImgUploaded(false);
        }
    };

    return (
        <div className='update-image-container'>
            <h2>Update Image</h2>
            <div className="bottom">
                <div className="left">
                    <img src={img ? URL.createObjectURL(img) : prevImg} alt="" />
                    <div className="imgUpload">
                        <label htmlFor='img'>
                            <AddPhotoAlternateIcon className='icon' />
                            Upload New Image
                        </label>
                        <input type="file" id='img' style={{ display: "none" }} onChange={handleImgUpload} />
                        <p className="error-message">{errorMessages}</p>
                        {loading ? (
                            <p className='loading-message'>Updating doctor profile...</p>
                        ) : (
                            <button className='img-save-btn' onClick={() => handleSave()}>Save Image</button>
                        )}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UpdateImage
