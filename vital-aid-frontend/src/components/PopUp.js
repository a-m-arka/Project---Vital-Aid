import React from 'react'
import '../style/PopUp.scss'

const PopUp = ({ message, onClose }) => {
    return (
        <div className="popup-overlay" onClick={onClose}>
            <div className="popup-content" onClick={(e) => e.stopPropagation()}>
                <i className="fa-regular fa-circle-check"></i>
                <p>{message}</p>
                <div className="pop-up-buttons">
                    <button className="close-button" onClick={onClose}>Close</button>
                </div>
            </div>
        </div>
    );
}

export default PopUp
