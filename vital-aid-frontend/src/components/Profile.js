import React, { useRef, useState } from 'react';
import '../style/Profile.css';
import profileIcon from "../Images/profile icon.png";
import ProfileDetails from './ProfileDetails';
import ProfileUpdate from './ProfileUpdate';
import ChangePass from './ChangePass';

export default function Profile() {
  const [currentView, setCurrentView] = useState('ProfileDetails');
  const fileInputRef = useRef(null);
  const profilePhotoRef = useRef(null);

  const handleButtonClick = () => {
    fileInputRef.current.click();
  };

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        profilePhotoRef.current.src = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="rout-container">
      <div className="profile-container">
        <div className="side-section">
          <div className="side-content">
            <div className="profile-picture">
              <img src={profileIcon} ref={profilePhotoRef} id="profile-photo" alt="profile icon" />
              <div className="upload-photo-button">
                <input
                  type="file"
                  ref={fileInputRef}
                  style={{ display: 'none' }}
                  onChange={handleFileChange}
                />
                <label onClick={handleButtonClick} id="upload-image-button">
                  <i className="fa fa-camera"></i>
                </label>
              </div>
            </div>
            <button
              className={`profile-buttons edit-Profile-button ${currentView === 'ProfileUpdate' ? 'active' : ''}`}
              onClick={() => setCurrentView('ProfileUpdate')}
            >
              <a>Edit Profile</a>
            </button>
            <button
              className={`profile-buttons change-password-button ${currentView === 'ChangePass' ? 'active' : ''}`}
              onClick={() => setCurrentView('ChangePass')}
            >
              <a>Change Password</a>
            </button>
          </div>
        </div>

        <div className="main-section">
          {currentView === 'ProfileDetails' && <ProfileDetails />}
          {currentView === 'ProfileUpdate' && <ProfileUpdate onCancel={() => setCurrentView('ProfileDetails')} />}
          {currentView === 'ChangePass' && <ChangePass onCancel={() => setCurrentView('ProfileDetails')} />}
        </div>
      </div>
    </div>
  );
}
