import React, { useState } from 'react';
import '../../style/UserProfile/View.scss';
import Details from './Details';
import Update from './Update';
import ChangePassword from './ChagePassword';
import ChangePhoto from './ChangePhoto';
import ProfileIcon from '../../Images/profileIcon.png';
import coverPhoto from '../../Images/coverPhoto.jpg';

const ViewProfile = () => {
    const [userData, setUserData] = useState({
        name: "A. M. Arka",
        userId: "2104028",
        email: "arka@user.com",
        address: "Mars colony, Road 1, Mars city, Mars",
        dateOfBirth: "2002-12-23",
        contact: "01234567890",
        bloodGroup: "O+",
        gender: "Male",
        image: ProfileIcon,
    });

    const [currentPage, setCurrentPage] = useState('details');

    const handleViewProfile = () => {
        setCurrentPage('details');
    };

    const updateUserData = (updatedData) => {
        setUserData((prev) => ({ ...prev, ...updatedData }));
        setCurrentPage('details');
    };

    const renderComponent = () => {
        switch (currentPage) {
            case 'details':
                return <Details data={userData} />;
            case 'edit':
                return <Update data={userData} updateUserData={updateUserData} />;
            case 'changePhoto':
                return <ChangePhoto data={userData} updateUserData={updateUserData} />;
            case 'changePass':
                return <ChangePassword />;
            default:
                return <Details data={userData} />;
        }
    };

    return (
        <div className="rout-container">
            <div className="profile-design">
                <div className="background-gradient">
                    <img src={coverPhoto} />
                </div>
                <div className="main-section">
                    <div className="profileBtn">
                        {currentPage !== 'details' && (
                            <div className="btn" onClick={handleViewProfile}>View Profile</div>
                        )}
                        {currentPage !== 'edit' && (
                            <div className="btn" onClick={() => setCurrentPage('edit')}>Edit Profile</div>
                        )}
                        {currentPage !== 'changePhoto' && (
                            <div className="btn" onClick={() => setCurrentPage('changePhoto')}>Change Photo</div>
                        )}
                        {currentPage !== 'changePass' && (
                            <div className="btn" onClick={() => setCurrentPage('changePass')}>Change Password</div>
                        )}
                    </div>
                    <div className="page">{renderComponent()}</div>
                </div>
            </div>
        </div>
    );
};

export default ViewProfile;

