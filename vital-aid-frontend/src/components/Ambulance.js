import React from 'react';
import '../style/Ambulance.css';
import ambulanceIcon from '../Images/ambulance_icon.png';

export default function Ambulance() {
  return (
    <div className='rout-container'>
      <div className="parent-container-of-ambulance-form-main-container">
        <div className="main-container-of-ambulance-form">
          <div className="ambulance-form-container">
            <form className="ambulance-form" action="#">
              <div className="ambulance-form-row">
                <div className="ambulance-form-data-input-section">
                  <input type="text" required />
                  <label>Address</label>
                </div>
              </div>
              <div className="ambulance-form-row">
                <div className="ambulance-form-data-input-section">
                  <input type="number" required />
                  <label>Contact</label>
                </div>
              </div>
              <div className="ambulance-form-row ambulance-form-button-container">
                <div className="ambulance-form-data-input-section">
                  <div className="inner"></div>
                  <input type="submit" value="Call Ambulance" />
                </div>
              </div>
            </form>
          </div>
          <div className="right-side-icon-design-container">
            <div className="ambulance-icon-container">
              <img src={ambulanceIcon} alt="Ambulance Icon" />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
