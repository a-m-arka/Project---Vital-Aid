import React, { useState } from 'react'
import './New.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate'
import { useLocation } from 'react-router-dom'

const New = ({ type }) => {

  const noImg = "https://static.vecteezy.com/system/resources/previews/004/141/669/non_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";

  const { state } = useLocation();

  const data = state?.row;

  const inputFields = Object.entries(data).filter(([key]) => key !== 'image' && key !== 'id').map(([key]) => [key]);

  const [img, setImg] = useState();

  const handleImgUpload = (event) => {
    setImg(event.target.files[0]);
  };


  return (
    <div className='new'>
      <Sidebar />
      <div className="newContainer">
        <div className="top">
          <h1>ADD NEW {String(type).toUpperCase()}</h1>
        </div>
        <div className="bottom">
          {data.image === "" && (
            <div className="left">
              <img src={img ? URL.createObjectURL(img) : noImg} alt="" />
              <div className="imgUpload">
                <label htmlFor='img'>
                  <AddPhotoAlternateIcon className='icon' />
                  Upload Image
                </label>
                <input type="file" id='img' style={{ display: "none" }} onChange={handleImgUpload} />
              </div>
            </div>
          )}
          <div className="right">
            <form>
              {inputFields.map(([key]) => (
                <div className="formInput" key={key}>
                  <label>{key.toUpperCase().replaceAll('_', ' ')}</label>
                  <input
                    type="text"
                  // value={inputValues[key]}
                  // onChange={(event) => handleInput(event, key)}
                  />
                </div>
              ))}
              <button>Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default New