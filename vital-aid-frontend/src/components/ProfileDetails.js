import React from 'react'

export default function ProfileDetails() {
    return (
        <div className="profile-details pop-out">
            <div className="profile-item user-id">
                <div className="profile-items-heading">User Id</div>
                <div className="profile-data">2104031</div>
            </div>
            <div className="profile-item Name">
                <div className="profile-items-heading">Name</div>
                <div className="profile-data">Shahriar Khaled Fahim</div>
            </div>
            <div className="profile-item gender">
                <div className="profile-items-heading">Gender</div>
                <div className="profile-data">Male</div>
            </div>
            <div className="profile-item date-of-birth">
                <div className="profile-items-heading">Date of Birth</div>
                <div className="profile-data">1 January, 2003</div>
            </div>
            <div className="profile-item phone-Number">
                <div className="profile-items-heading">Contact</div>
                <div className="profile-data">01234567891</div>
            </div>
            <div className="profile-item email">
                <div className="profile-items-heading">Email</div>
                <div className="profile-data">bugijugiemailid@gmail.com</div>
            </div>
            <div className="profile-item blood-group">
                <div className="profile-items-heading">Blood Group</div>
                <div className="profile-data">F+</div>
            </div>
            <div className="profile-item adress">
                <div className="profile-items-heading">Adress</div>
                <div className="profile-data">Mars colony, Road 1, Mars city, Mars</div>
            </div>
        </div>
    )
}
