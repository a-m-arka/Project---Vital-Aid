import React, { useState } from 'react'
import '../../style/UserProfile/ChangePhoto.scss'

const ChangePhoto = ({ data, updateUserData }) => {
    const [img, setImg] = useState(data.image);

    const handleImgUpload = (event) => {
        const uploadedImg = event.target.files[0];
        const newImgUrl = URL.createObjectURL(uploadedImg);
        setImg(newImgUrl); // Update local preview
    };

    const handleUpdateClick = () => {
        updateUserData({ ...data, image: img }); // Update image in parent
    };

    return (
        <div className="changePhoto">
            <div className="changePhoto-heading">Change Profile Picture</div>
            <img src={img} alt="Profile" />
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
            <div className="update-btn" onClick={handleUpdateClick}>
                Update
            </div>
        </div>
    );
};

export default ChangePhoto;

