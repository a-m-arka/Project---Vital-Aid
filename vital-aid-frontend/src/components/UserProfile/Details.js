import React from 'react'
import '../../style/UserProfile/Details.scss'

const Details = ({ data }) => {
    const date = new Date(data.dateOfBirth);
    const formattedDate = date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
    return (
        <div className="profile-card">
            <div className="profile-image">
                <img
                    src={data.image}
                    alt="Profile"
                />
            </div>
            <div className="heading">
                <div className="name">{data.name}</div>
                <div className="email">{data.email}</div>
                <div className="address">{data.address ? data.address : 'Not Found'}</div>
            </div>
            <div className="details-section">
                <div className="detail">
                    <div className="value">{data.bloodGroup ? data.bloodGroup : 'Not Found'}</div>
                    <div className="key">Blood Group</div>
                </div>
                <div className="detail">
                    <div className="value">{data.contact ? data.contact : 'Not Found'}</div>
                    <div className="key">Contact Number</div>
                </div>
                <div className="detail">
                    <div className="value">{data.gender ? data.gender : 'Not Found'}</div>
                    <div className="key">Gender</div>
                </div>
                <div className="detail">
                    <div className="value">{data.dateOfBirth ? formattedDate : 'Not Found'}</div>
                    <div className="key">Date Of Birth</div>
                </div>
            </div>
        </div>
    )
}

export default Details
