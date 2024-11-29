import React, { useState, useRef } from 'react';
import '../style/SearchBox.css';

export default function SearchBox({ placeholder, style, onSearch }) {
    const [inputValue, setInputValue] = useState('');
    const inputRef = useRef();

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const handleSearchClick = () => {
        if (onSearch) {
            onSearch(inputValue); // Trigger search with the current input value
        }
    };

    const cancelSearch = () => {
        setInputValue('');
        if (onSearch) {
            onSearch(''); // Clear the search in the parent component
        }
        inputRef.current.focus();
    };

    return (
        <div className="search-box" style={style}>
            <div className="search-input">
                <input
                    type="text"
                    placeholder={placeholder}
                    value={inputValue}
                    onChange={handleInputChange}
                    ref={inputRef}
                />
            </div>
            {inputValue && (
                <div className="search-cancel">
                    <i className="fa-solid fa-xmark" onClick={cancelSearch}></i>
                </div>
            )}
            <div className="search-btn" onClick={handleSearchClick}>
                <i className="fa-solid fa-magnifying-glass"></i>
            </div>
        </div>
    );
}
