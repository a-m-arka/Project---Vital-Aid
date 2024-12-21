import React from 'react'
import './Single.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import { useLocation, useNavigate } from 'react-router-dom'

const Single = ({ type, adminControl }) => {

  const noImg = "https://static.vecteezy.com/system/resources/thumbnails/004/141/669/small_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";

  const { state } = useLocation();
  const data = state?.row;
  const detailsArray = Object.entries(data).filter(([key]) => key !== 'image' && key !== 'id').map(([key, value]) => [key, String(value)]);

  const navigate = useNavigate();
  const handleUpdateClick = (row) => {
    navigate(`/${type}/${row.id}/update`, { state: { row } });
  };

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
        {data.image && (
          <div className="imgSection">
            <img src={data.image ? data.image : noImg} alt="" />
          </div>
        )}
        <div className="detailSection">
          <ul>
            {detailsArray.map((detail) => (
              <li>
                <span className="key">{(detail[0].toUpperCase()).replaceAll('_', ' ')}</span>
                <span className="colon">:</span>
                <span className="value">{detail[1]}</span>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  )
}

export default Single