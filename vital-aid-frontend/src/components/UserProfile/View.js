import React, { useState } from 'react';
import '../../style/UserProfile/View.scss';
import Details from './Details';
import Update from './Update';
import ChangePassword from './ChagePassword';
import ChangePhoto from './ChangePhoto';
import coverPhoto from '../../Images/coverPhoto.jpg';
import { useGlobalContext } from '../../context/GlobalContext';
import { useNavigate } from 'react-router-dom';

const ViewProfile = () => {
    const { profile, setProfile } = useGlobalContext();
    const [currentPage, setCurrentPage] = useState('details');
    const navigate = useNavigate();

    const handleViewProfile = () => {
        setCurrentPage('details');
    };

    const handleMyAppoinments = () => {
        navigate('/appointmentlist');
    };

    const updateUserData = (updatedData) => {
        setProfile((prev) => ({ ...prev, ...updatedData }));
        setCurrentPage('details');
    };

    const renderComponent = () => {
        switch (currentPage) {
            case 'details':
                return <Details data={profile} />;
            case 'edit':
                return <Update data={profile} updateUserData={updateUserData} />;
            case 'changePhoto':
                return <ChangePhoto data={profile} updateUserData={updateUserData} />;
            case 'changePass':
                return <ChangePassword />;
            default:
                return <Details data={profile} />;
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
                        <div className="btn" onClick={() => handleMyAppoinments()}>My Appointments</div>
                    </div>
                    <div className="page">{renderComponent()}</div>
                </div>
            </div>
        </div>
    );
};

export default ViewProfile;

