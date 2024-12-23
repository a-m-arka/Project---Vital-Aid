import React, { useState } from 'react'
import './Profile.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import View from '../../components/adminAcoount/View'
import Update from '../../components/adminAcoount/Update'
import ChangePass from '../../components/adminAcoount/ChangePass'

const Profile = () => {

    const [status, setStatus] = useState("view");

    const handleViewClick = () => {
        setStatus("view");
    };

    const handleUpdateClick = () => {
        setStatus("update");
    };

    const handleChangePassClick = () => {
        setStatus("changePass");
    };

    const adminData = {
        name: "A. M. Arka",
        id: "2104028",
        email: "arka@admin.com",
        phone: "01234567890",
        password: "1234"
    };

    return (
        <div className='profile'>
            <Sidebar />
            <div className="profile-container">
                <div className="top">
                    <h1>Admin Profile</h1>
                </div>
                <div className="bottom">
                    <div className="left">
                        {status === 'view' && (
                            <View data={adminData} />
                        )}
                        {status === 'update' && (
                            <Update data={adminData} />
                        )}
                        {status === 'changePass' && (
                            <ChangePass password={adminData.password} />
                        )}
                    </div>
                    <div div className="right" >
                        {status !== 'view' && (
                            <div className="view-btn" onClick={handleViewClick}>
                                View Profile
                            </div>
                        )}
                        {status !== 'update' && (
                            <div className="update-btn" onClick={handleUpdateClick}>
                                Update Profile
                            </div>
                        )}
                        {status !== 'changePass' && (
                            <div className="change-pass-btn" onClick={handleChangePassClick}>
                                Change Password
                            </div>
                        )}
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Profile


