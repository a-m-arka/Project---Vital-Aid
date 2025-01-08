import React, { useState } from "react";
import '../style/ProductCard.css';
import { useGlobalContext } from "../context/GlobalContext";

const ProductCard = ({ product, onOrderConfirmed, onUnauthorizedOrder }) => {
  const [isOdered, setIsOrdered] = useState(false);
  const [quantity, setQuantity] = useState(1);
  const [totalPrice, setTotalPrice] = useState(product.productPrice);
  const [userLocation, setUserLocation] = useState('');
  const [error, setError] = useState(null);
  const { isLoggedIn } = useGlobalContext();
  const [loading, setLoading] = useState(false);

  const handleOrder = () => {
    if (isLoggedIn) {
      setIsOrdered(true);
    }
    else {
      onUnauthorizedOrder();
    }
  };

  const handleCancelOrder = () => {
    setIsOrdered(false);
    setQuantity(1);
    setTotalPrice(product.productPrice);
    setUserLocation(null);
    setError(null);
  };

  const handleConfirmOrder = async () => {
    if (!userLocation) {
      setError('Please enter your location');
      return;
    }
    else {
      setError('');
      setLoading(true);
      const token = localStorage.getItem('token');

      try {
        const response = await fetch(`http://localhost:8080/vital_aid/orderProduct/makeOrder/${product.id}`, {
          method: 'POST',
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            quantity: quantity,
            totalPrice: totalPrice,
            location: userLocation
          }),
        });

        if (response.ok) {
          console.log("Successfull order");
          onOrderConfirmed();
        } else {
          const error = await response.text();
          setError(error || 'Failed to book appoinment. Please try again.');
        }
      } catch (error) {
        console.error('Error updating profile:', error);
        setError(error.message);
      } finally {
        setLoading(false);
      }
    }
  };

  const handlePlus = () => {
    setQuantity(quantity + 1);
    setTotalPrice(product.productPrice * (quantity + 1));
  };

  const handleMinus = () => {
    setQuantity(Math.max(1, quantity - 1));
    setTotalPrice(product.productPrice * (Math.max(1, quantity - 1)));
  };

  const handleLocationChange = (e) => {
    setUserLocation(e.target.value);
  };

  return (
    <div className="medical-store-card-main-container">
      <div className="product-photo-container">
        <div className="product-photo-box">
          <img src={product.productPhotoUrl} alt="" />
        </div>
      </div>
      <div className="product-info-and-button-container">

        <div className="product-name-section">
          <span className="product-name">{product.productName}</span>
        </div>
        <div className="product-category-section">
          <span className="product-heading">Category:</span>
          <span className="product-Category">{product.productCategory}</span>
        </div>

        {/* <div className="product-stock-number-section">
          <span className="product-heading">Stock</span>
          <span className="product-stock-number">{product.product_quantity}</span>
        </div> */}

        <div className="product-price-section">
          <span className="product-heading">Price:</span>
          <span className="product-price">{`BDT ${product.productPrice}`}</span>
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
              {loading ? (
                <p className="loading-message">Placing Order...</p>
              ) : (
                <>
                  <div className="buy-now-button-section">
                    <button className="buy-now-button" onClick={handleConfirmOrder}>Place Order</button>
                  </div>
                  <div className="cancel-order-button-section">
                    <button className="cancel-order-button" onClick={handleCancelOrder}>Cancel Order</button>
                  </div>
                </>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProductCard;