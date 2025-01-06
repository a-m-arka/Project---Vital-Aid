import React from 'react'
import '../style/PopUp.scss'
import { useNavigate } from 'react-router-dom'

const AccessDeniedPopUp = ({ onClose }) => {
    const navigate = useNavigate()

    const onOk = () => {
        navigate('/login');
    };

    return (
        <div className="popup-overlay" >
            <div className="popup-content" onClick={(e) => e.stopPropagation()}>
                <i className="fa-solid fa-lock"></i>
                <p>Please log in to access this service</p>
                <div className="pop-up-buttons">
                    <button className="ok-button" onClick={onOk}>Ok</button>
                    <button className="close-button" onClick={onClose}>Close</button>
                </div>
            </div>
        </div>
    )
}

export default AccessDeniedPopUp
