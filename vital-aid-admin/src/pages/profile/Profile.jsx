import React, { useState } from 'react'
import './Profile.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import View from '../../components/adminAcoount/View'
import Update from '../../components/adminAcoount/Update'
import ChangePass from '../../components/adminAcoount/ChangePass'
import { useGlobalContext } from '../../context/GlobalContext'

const Profile = () => {

    const [status, setStatus] = useState("view");
    const { profile } = useGlobalContext();

    const handleViewClick = () => {
        setStatus("view");
    };

    const handleUpdateClick = () => {
        setStatus("update");
    };

    const handleChangePassClick = () => {
        setStatus("changePass");
    };

    const adminData = profile;

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
                    <div className="right" >
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


