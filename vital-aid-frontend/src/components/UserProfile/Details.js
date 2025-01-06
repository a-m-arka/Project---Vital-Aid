import React from 'react'
import '../../style/UserProfile/Details.scss'

const Details = ({ data }) => {
    const date = new Date(data.userDob);
    const formattedDate = date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
    return (
        <div className="profile-card">
            <div className="profile-image">
                <img
                    src={data.profileImageUrl}
                    alt="Profile"
                />
            </div>
            <div className="heading">
                <div className="name">{data.personName}</div>
                <div className="email">{data.personEmail}</div>
                <div className="address">{data.userAddress ? data.userAddress : 'Address Not Found'}</div>
            </div>
            <div className="details-section">
                <div className="detail">
                    <div className="value">{data.userBloodGroup ? data.userBloodGroup : 'Not Found'}</div>
                    <div className="key">Blood Group</div>
                </div>
                <div className="detail">
                    <div className="value">{data.personPhone ? data.personPhone : 'Not Found'}</div>
                    <div className="key">Contact Number</div>
                </div>
                <div className="detail">
                    <div className="value">{data.userGender ? data.userGender : 'Not Found'}</div>
                    <div className="key">Gender</div>
                </div>
                <div className="detail">
                    <div className="value">{data.userDob ? formattedDate : 'Not Found'}</div>
                    <div className="key">Date Of Birth</div>
                </div>
            </div>
        </div>
    )
}

export default Details
