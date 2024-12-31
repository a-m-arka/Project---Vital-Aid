import React, { useState } from "react";
import '../style/ProductCard.css';
import Popup from './PopUp';

const ProductCard = ({ product }) => {
  const [isOdered, setIsOrdered] = useState(false);
  const [orderConfirmed, setOrderConfirmed] = useState(false);
  const [quantity, setQuantity] = useState(1);
  const [totalPrice, setTotalPrice] = useState(product.product_price);
  const [userLocation, setUserLocation] = useState(null);
  const [error, setError] = useState(null);

  const handleOrder = () => {
    setIsOrdered(true);
  };

  const handleCancelOrder = () => {
    setIsOrdered(false);
    setQuantity(1);
    setTotalPrice(product.product_price);
    setUserLocation(null);
    setError(null);
  };

  const handleConfirmOrder = () => {
    if (!userLocation) {
      setError('Please enter your location');
      return;
    }
    else {
      setOrderConfirmed(true);
    }
  };

  const handlePlus = () => {
    setQuantity(Math.min(product.product_quantity, quantity + 1));
    setTotalPrice(product.product_price * (Math.min(product.product_quantity, quantity + 1)));
  };

  const handleMinus = () => {
    setQuantity(Math.max(1, quantity - 1));
    setTotalPrice(product.product_price * (Math.max(1, quantity - 1)));
  };

  const handleLocationChange = (e) => {
    setUserLocation(e.target.value);
  };

  const onClose = () => {
    window.location.reload();
  };

  return (
    <div className="medical-store-card-main-container">
      <div className="product-photo-container">
        <div className="product-photo-box">
          <img src={product.product_photo} alt="" />
        </div>
      </div>
      <div className="product-info-and-button-container">

        <div className="product-name-section">
          <span className="product-name">{product.product_name}</span>
        </div>
        <div className="product-category-section">
          <span className="product-heading">Category</span>
          <span className="product-Category">{product.product_category}</span>
        </div>

        <div className="product-stock-number-section">
          <span className="product-heading">Stock</span>
          <span className="product-stock-number">{product.product_quantity}</span>
        </div>

        <div className="product-price-section">
          <span className="product-heading">Price:</span>
          <span className="product-price">{`BDT ${product.product_price}`}</span>
        </div>
        {isOdered && (
          <div className="oreder-section">
            <div className="order-quantity-section">
              <div className="quantity-title">Quantity:</div>
              <div className="quantity-value-change">
                <div className="minus" onClick={handleMinus}>-</div>
                <div className="quantity-value">{quantity}</div>
                <div className="plus" onClick={handlePlus}>+</div>
              </div>
            </div>
            <div className="total-price-section">
              <div className="total-price-title">Total Price:</div>
              <div className="total-price-value">{`BDT ${totalPrice}`}</div>
            </div>
            <div className="user-loction-section">
              <div className="user-location-title">Location:</div>
              <input type="text" className="user-location" value={userLocation} onChange={handleLocationChange} />
            </div>
            <div className="order-error-message">
              {error && <span>{error}</span>}
            </div>
          </div>
        )}
        <div className="cart-and-buy-now-button-section">
          {!isOdered && (
            <div className="buy-now-button-section">
              <button className="buy-now-button" onClick={handleOrder}>Order</button>
            </div>
          )}
          {isOdered && (
            <>
              <div className="buy-now-button-section">
                <button className="buy-now-button" onClick={handleConfirmOrder}>Confirm Order</button>
              </div>
              <div className="cancel-order-button-section">
                <button className="cancel-order-button" onClick={handleCancelOrder}>Cancel Order</button>
              </div>
            </>
          )}
        </div>
      </div>
      {orderConfirmed && <Popup message="Order Confirmed!" onClose={onClose}/>}
    </div>
  );
};

export default ProductCard;