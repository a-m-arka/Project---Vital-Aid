import React, { useState, useEffect } from 'react';
import '../style/Store.css';
import SearchBox from './SearchBox';
import ProductCard from './ProductCard';
import productList from '../data/products.json';

export default function Store() {
  const [searchTerm, setSearchTerm] = useState('');
  const [isMobile, setIsMobile] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState('');

  const categories = ['Medicine', 'Medical Equipment'];

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 768);
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  const handleBuyNow = (product) => {
    console.log("Buying product:", product);
    // Handle Buy Now logic
  };

  const handleAddToCart = (product) => {
    console.log("Adding product to cart:", product);
    // Handle Add to Cart logic
  };

  const handleSearch = (term) => {
    setSearchTerm(term);
  };

  const filteredProducts = productList.filter((product) => {
    const matchesSearch = product.product_name.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesCategory = selectedCategory ? product.product_category === selectedCategory : true;
    return matchesSearch && matchesCategory;
  });

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

          <button className="store-cart">
            <label>My Cart</label>
            <i class="fa-solid fa-cart-shopping"></i>
          </button>

        </div>

        <div className="product-list">
          {filteredProducts.map((product) => (
            <ProductCard
              key={product.product_id}
              product={product}
              onBuyNow={handleBuyNow}
              onAddToCart={handleAddToCart}
            />
          ))}
        </div>

      </div>
    </div>
  );
}
