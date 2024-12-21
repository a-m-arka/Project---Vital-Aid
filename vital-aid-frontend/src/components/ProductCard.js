import React from "react";
import '../style/ProductCard.css';

const ProductCard = ({ product, onBuyNow }) => {
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
        <div className="cart-and-buy-now-button-section">
          <div className="buy-now-button-section">
            <button className="buy-now-button">Order</button>
          </div>
        </div>
      </div>

    </div>
  );
};

export default ProductCard;





// <div className="product-card">
//       <img src={product.product_photo} alt={product.product_name} className="product-image" />
//       <div className="product-details">
//         <h3 className="product-name">{product.product_name}</h3>
//         <p className="product-category">{product.product_category}</p>
//         <p className="product-price">BDT {product.product_price}</p>
//         <p className="product-quantity">Stock: {product.product_quantity}</p>
//         <div className="product-actions">
//           <button className="buy-now" onClick={() => onBuyNow(product)}>Buy Now</button>
//           <button className="add-to-cart" onClick={() => onAddToCart(product)}>Add to Cart</button>
//         </div>
//       </div>
//     </div>