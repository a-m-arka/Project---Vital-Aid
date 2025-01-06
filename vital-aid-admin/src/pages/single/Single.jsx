import React from 'react'
import './Single.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import { useLocation, useNavigate } from 'react-router-dom'

const Single = ({ type, adminControl }) => {
  const noImg = "https://static.vecteezy.com/system/resources/thumbnails/004/141/669/small_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";
  const { state } = useLocation();
  const data = state?.row;
  const detailsArray = Object.entries(data)
    .filter(([key]) => key !== 'profileImageUrl' && key !== 'doctorPhotoUrl' && key !== 'hospitalPhotoUrl' && key !== 'productPhotoUrl' && key !== 'id' && key !== 'consultingTime')
    .map(([key, value]) => [key, String(value)]);

  const img = () => {
    if (data.profileImageUrl) {
      return data.profileImageUrl;
    }
    if (data.doctorPhotoUrl) {
      return data.doctorPhotoUrl;
    }
    if (data.hospitalPhotoUrl) {
      return data.hospitalPhotoUrl;
    }
    if (data.productPhotoUrl) {
      return data.productPhotoUrl;
    }
    return null;
  };

  const navigate = useNavigate();
  const handleUpdateClick = (row) => {
    navigate(`/${type}/${row.id}/update`, { state: { row } });
  };

  function camelCaseToWords(str) {
    return str
      .replace(/([a-z])([A-Z])/g, '$1 $2')
      .replace(/^./, match => match.toUpperCase());
  }

  return (
    <div className='single'>
      <Sidebar />
      <div className="singleContainer">
        <div className="top">
          <h1>{String(type).toUpperCase()} PROFILE</h1>
          {adminControl && (
            <div className="updateButton" onClick={() => handleUpdateClick(data)}>
              Edit
            </div>
          )}
        </div>
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
      </div>
    </div>
  )
}

export default Single;
