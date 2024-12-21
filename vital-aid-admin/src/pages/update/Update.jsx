import React from 'react'
import './Update.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import { useState } from 'react';
import { useLocation } from 'react-router-dom'

const Update = ({ type }) => {

    const { state } = useLocation();
    const data = state?.row;
    const inputFields = Object.entries(data).filter(([key]) => key !== 'image' && key !== 'id').map(([key, value]) => [key, String(value)]);

    const [img, setImg] = useState();
    const handleImgUpload = (event) => {
        setImg(event.target.files[0]);
    };

    const [inputValues, setInputValues] = useState(
        Object.fromEntries(inputFields.map(([key, value]) => [key, value]))
    );
    const handleInput = (event, fieldName) => {
        setInputValues({
            ...inputValues,
            [fieldName]: event.target.value,
        });
    };

    return (
        <div className='update'>
            <Sidebar />
            <div className="updateContainer">
                <div className="top">
                    <h1>UPDATE {String(type).toUpperCase()}</h1>
                </div>
                <div className="bottom">
                    {data.image && (
                        <div className="left">
                            <img src={img ? URL.createObjectURL(img) : data.image} alt="" />
                            <div className="imgUpload">
                                <label htmlFor='img'>
                                    <AddPhotoAlternateIcon className='icon' />
                                    Change Image
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
                                        value={inputValues[key]}
                                        onChange={(event) => handleInput(event, key)}
                                    />
                                </div>
                            ))}
                            <button>Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Update
