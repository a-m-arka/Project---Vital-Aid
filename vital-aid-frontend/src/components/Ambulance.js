import React from 'react';
import '../style/Ambulance.scss';
import { useNavigate } from 'react-router-dom';
import ambulanceData from '../data/ambulanceData';
// import AmbulanceForm from './AmbulanceForm';

export default function Ambulance() {
  const navigate = useNavigate();
  // const [isCalled, setIsCalled] = useState(false);

  const handleCall = (ambulanceData) => {
    navigate('/ambulanceform', { state: { ambulanceData: ambulanceData } });
  }

  return (
    <div className="rout-container">
      <div className="ambulance-container">
        <div className="heading caption">
          <h1>Vital Aid Ambulance</h1>
        </div>
        <div className="ambulance-table-container">
          <table className="ambulance-table">
            <thead>
              <tr>
                <th>Ambulance Number Plate</th>
                <th>Driver Contact</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {ambulanceData.map((ambulance, index) => (
                <tr key={index}>
                  <td>{ambulance.id}</td>
                  <td>{ambulance.ambulanceDriverContact}</td>
                  <td>
                    <button className='ambulance-call-btn' onClick={() => handleCall(ambulance)}>
                      <i className="fa-solid fa-phone"></i>
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
