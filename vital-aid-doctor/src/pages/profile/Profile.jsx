import React from 'react'
import './Profile.scss'
import { useLocation, useNavigate } from 'react-router-dom'

import { doctorData } from '../../temporaryData/doctorData'

const Profile = () => {
    const noImg = "https://static.vecteezy.com/system/resources/thumbnails/004/141/669/small_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";
    //   const { state } = useLocation();
    const data = doctorData;

    const detailsArray = Object.entries(data)
        .filter(([key]) => key !== 'doctorProfileImageUrl' && key !== 'consultingTime')
        .map(([key, value]) => [key, String(value)]);

    const img = () => {
        if (data.doctorProfileImageUrl) {
            return data.doctorProfileImageUrl;
        }
        return null;
    };

    const navigate = useNavigate();

    const handleUpdateClick = () => {
        navigate("/update-profile");
    };

    const handleChangeClick = () => {
        navigate("/change-password");
    };

    function camelCaseToWords(str) {
        return str
            .replace(/([a-z])([A-Z])/g, '$1 $2')
            .replace(/^./, match => match.toUpperCase());
    }

    return (
        <div className="profileContainer">
            <h2>PROFILE</h2>
            {img() && (
                <div className="imgSection">
                    <img src={img() ? img() : noImg} alt="" />
                </div>
            )}
            <div className="detailSection">
                <ul>
                    {detailsArray.map(([key, value]) => (
                        <li key={key}>
                            <span className="key">{camelCaseToWords(key)}</span>
                            <span className="colon">:</span>
                            <span className="value">{value}</span>
                        </li>
                    ))}
                    {data.consultingTime && (
                        <li key="consultingTime">
                            <span className="key">Consulting Time</span>
                            <span className="colon">:</span>
                            <span className="value">
                                {data.consultingTime.startTime} - {data.consultingTime.endTime}
                            </span>
                        </li>
                    )}
                </ul>
            </div>
            <div className="btn-section">
                <div className="updateButton" onClick={() => handleUpdateClick()}>
                    Edit Profile
                </div>
                <div className="changePassButton" onClick={() => handleChangeClick()}>
                    Change Password
                </div>
            </div>
        </div>
    )
}

export default Profile;
