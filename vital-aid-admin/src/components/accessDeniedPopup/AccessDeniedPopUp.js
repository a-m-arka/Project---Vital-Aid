import React from 'react'
import './PopUp.scss'
import { useNavigate } from 'react-router-dom'
import LockIcon from '@mui/icons-material/Lock';

const AccessDeniedPopUp = () => {
    const navigate = useNavigate()

    const onClose = () => {
        navigate('/');
    };

    return (
        <div className="popup-overlay" >
            <div className="popup-content" onClick={(e) => e.stopPropagation()}>
                <LockIcon className='icon'/>
                <p>Access Denied. Please log in.</p>
                <div className="pop-up-buttons">
                    {/* <button className="ok-button" onClick={onOk}>Ok</button> */}
                    <button className="close-button" onClick={onClose}>Close</button>
                </div>
            </div>
        </div>
    )
}

export default AccessDeniedPopUp
