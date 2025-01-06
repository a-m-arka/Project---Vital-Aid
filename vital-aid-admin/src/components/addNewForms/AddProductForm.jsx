import React, { useState } from 'react';
import './newForm.scss';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import { useNavigate } from 'react-router-dom';

const AddProductForm = () => {
    const navigate = useNavigate();
    const noImg = "https://static.vecteezy.com/system/resources/previews/004/141/669/non_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg";
    const [errorMessages, setErrorMessages] = useState('');
    const [img, setImg] = useState(null);
    const [formData, setFormData] = useState({
        productName: '',
        productCategory: '', 
        productPrice: ''
    });
    const [loading, setLoading] = useState(false);  // Added loading state

    const handleImgUpload = (event) => {
        setImg(event.target.files[0]);
    };

    const handleInputChange = (event, field) => {
        const { value } = event.target;
        setFormData({
            ...formData,
            [field]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMessages('');
        setLoading(true); 

        const token = localStorage.getItem('adminToken');
        const { productName, productCategory, productPrice } = formData;
        const { productFile } = img ? { productFile: img } : {};

        try {
            const multipartFormData = new FormData();

            multipartFormData.append(
                'productDTO',
                new Blob(
                    [
                        JSON.stringify({
                            productName,
                            productCategory,
                            productPrice: parseInt(productPrice, 10) 
                        })
                    ],
                    { type: 'application/json' }
                )
            );

            if (productFile) {
                multipartFormData.append('file', productFile);
            }

            const response = await fetch('http://localhost:8080/vital_aid/medical_store/addProduct', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
                body: multipartFormData
            });

            if (response.ok) {
                console.log('Success');
                navigate('/product');
            } else {
                const errorData = await response.text();
                setErrorMessages(errorData.message || 'An error occurred');
            }
        } catch (error) {
            console.log('Error:', error);
            setErrorMessages('Failed to submit form');
        } finally {
            setLoading(false);  
        }
    };

    const inputFields = [
        ["productName", "Product Name"],
        ["productCategory", "Product Category"],
        ["productPrice", "Product Price"]
    ];

    return (
        <div className="bottom">
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
            <div className="right">
                <p className="error-message">{errorMessages}</p>
                <form onSubmit={handleSubmit}>
                    {inputFields.map(([key, label]) => (
                        key === "productCategory" ? (
                            <div className="formInput" key={key}>
                                <label>{label}</label>
                                <select
                                    value={formData[key]}
                                    onChange={(event) => handleInputChange(event, key)}
                                >
                                    <option value="">Select Category</option>
                                    <option value="medicine">Medicine</option>
                                    <option value="Medical Equipment">Medical Equipment</option>
                                </select>
                            </div>
                        ) : (
                            <div className="formInput" key={key}>
                                <label>{label}</label>
                                <input
                                    type="text"
                                    value={formData[key]}
                                    onChange={(event) => handleInputChange(event, key)}
                                />
                            </div>
                        )
                    ))}

                    {loading ? (
                        <p className='loading-message'>Adding new product...</p>  
                    ) : (
                        <button type="submit">Submit</button>  
                    )}
                </form>
            </div>
        </div>
    );
};

export default AddProductForm;
