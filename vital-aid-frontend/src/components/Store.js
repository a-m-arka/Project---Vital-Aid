import React, { useState, useEffect } from 'react';
import '../style/Store.css';
import SearchBox from './SearchBox';
import ProductCard from './ProductCard';
import Popup from './PopUp';
import AccessDeniedPopUp from './AccessDeniedPopUp';
import { useGlobalContext } from '../context/GlobalContext';

export default function Store() {
  const [searchTerm, setSearchTerm] = useState('');
  const [isMobile, setIsMobile] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [orderConfirmed, setOrderConfirmed] = useState(false);
  const [unauthorizedOrder, setUnauthorizedOrder] = useState(false);
  const { productData } = useGlobalContext();
  const productList = productData;
  const categories = ['Medicine', 'Medical Equipment'];

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 768);
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  const handleSearch = (term) => {
    setSearchTerm(term);
  };

  const filteredProducts = productList.filter((product) => {
    const matchesSearch = product.productName.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesCategory = selectedCategory ? product.productCategory === selectedCategory : true;
    return matchesSearch && matchesCategory;
  });

  const onOrderConfirmed = () => {
    setOrderConfirmed(true);
  };

  const onUnauthorizedOrder = () => {
    setUnauthorizedOrder(true);
  };

  const onClose = () => {
    if (orderConfirmed) {
      window.location.reload();
    }
    if (unauthorizedOrder) {
      setUnauthorizedOrder(false);
    }
  };

  const handleMyCart = () => {

  };


  return (
    <div className="rout-container">
      <div className="store-container">

        <div className="heading caption">
          <h1>Medical Store</h1>
        </div>

        <div className="category-search-cart">

          <SearchBox className="store-search" placeholder="Search Product" onSearch={handleSearch} style={{ width: isMobile ? '100%' : '60%', height: isMobile ? "40%" : "100%", boxShadow: "0 0 8px black", margin: "0 0" }} />

          <div className="product-category">
            <div className="category-name">
              <label>Category</label>
            </div>
            <select
              className="category-select"
              value={selectedCategory}
              onChange={(e) => setSelectedCategory(e.target.value)}
            >
              <option value="">All</option>
              {categories.map((category, index) => (
                <option key={index} value={category}>
                  {category}
                </option>
              ))}
            </select>
          </div>

          <button className="store-cart" onClick={() => handleMyCart()}>
            <label>My Orders</label>
            {/* <i class="fa-solid fa-cart-shopping"></i> */}
          </button>

        </div>

        <div className="product-list">
          {filteredProducts.map((product) => (
            <ProductCard
              key={product.id}
              product={product}
              onOrderConfirmed={onOrderConfirmed}
              onUnauthorizedOrder={onUnauthorizedOrder}
            />
          ))}
        </div>

      </div>
      {orderConfirmed && <Popup message="Order Confirmed!" onClose={onClose} />}
      {unauthorizedOrder && <AccessDeniedPopUp onClose={onClose} />}
    </div>
  );
}
