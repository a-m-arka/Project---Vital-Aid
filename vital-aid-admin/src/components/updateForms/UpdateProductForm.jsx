import React, { useState } from 'react'
import '../addNewForms/newForm.scss'
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import { useNavigate } from 'react-router-dom';

const UpdateProductForm = ({ data }) => {
  const navigate = useNavigate();
  const prevImg = data.productPhotoUrl;
  const [errorMessages, setErrorMessages] = useState('');
  const [img, setImg] = useState(null);
  const [formData, setFormData] = useState({
    productName: data.productName,
    productCategory: data.productCategory,
    productPrice: data.productPrice,
    productStockQuantity: data.productStockQuantity || 0
  });
  const [loading, setLoading] = useState(false);

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

  async function urlToFile(url, filename, mimeType) {
    const response = await fetch(url);
    const data = await response.blob();
    return new File([data], filename, { type: mimeType });
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrorMessages('');
    setLoading(true);

    const token = localStorage.getItem('adminToken');
    const { productName, productCategory, productPrice, productStockQuantity } = formData;

    try {
      const multipartFormData = new FormData();

      multipartFormData.append(
        'productDTO',
        new Blob(
          [
            JSON.stringify({
              productName,
              productCategory,
              productPrice: parseInt(productPrice, 10),
              productStockQuantity: parseInt(productStockQuantity, 10)
            }),
          ],
          { type: 'application/json' }
        )
      );

      // Fetch the file from the URL if no new image is uploaded
      if (!img && prevImg) {
        const fetchedFile = await urlToFile(prevImg, 'previous-image.jpg', 'image/jpeg');
        multipartFormData.append('file', fetchedFile);
      } else if (img) {
        multipartFormData.append('file', img);
      }

      const response = await fetch(
        `http://localhost:8080/vital_aid/medical_store/updateProduct/${data.id}`,
        {
          method: 'PUT',
          headers: {
            Authorization: `Bearer ${token}`,
          },
          body: multipartFormData,
        }
      );

      if (response.ok) {
        console.log('Success');
        navigate('/product');
      } else {
        const errorData = await response.text();
        console.log(errorData);
        setErrorMessages(errorData || 'An error occurred');
      }
    } catch (error) {
      console.log('Error:', error);
      setErrorMessages(error || 'Failed to submit form');
    } finally {
      setLoading(false);
    }
  };


  const inputFields = [
    ["productName", "Product Name"],
    ["productCategory", "Product Category"],
    ["productPrice", "Product Price"],
    ["productStockQuantity", "Stock"]
  ];

  return (
    <div className="bottom">
      <div className="left">
        <img src={img ? URL.createObjectURL(img) : prevImg} alt="" />
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
                  <option value="Medicine">Medicine</option>
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
            <p className='loading-message'>Updating Product Data...</p>
          ) : (
            <button type="submit">Update</button>
          )}
        </form>
      </div>
    </div>
  );
}

export default UpdateProductForm
