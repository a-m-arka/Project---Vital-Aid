import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../style/Hospitals.css';
import hospitalList from '../data/hospitals.json';
import SearchBox from './SearchBox';

export default function Hospitals() {
  const [searchTerm, setSearchTerm] = useState('');
  const [isMobile, setIsMobile] = useState(false);
  const [selectedValues, setSelectedValues] = useState(Array(5).fill(''));
  const navigate = useNavigate();

  useEffect(() => {
    const handleResize = () => {
      setIsMobile(window.innerWidth <= 768);
    };
    handleResize();
    window.addEventListener('resize', handleResize);
    return () => window.removeEventListener('resize', handleResize);
  }, []);

  const handleSelectChange = (index, event) => {
    const updatedValues = [...selectedValues];
    updatedValues[index] = event.target.value;
    setSelectedValues(updatedValues);
  };

  const handleViewDetails = (hospital) => {
    navigate('/hospital_details', { state: { hospital } });
  };

  const filters = [
    { name: 'Location', options: ['Dhaka', 'Chattogram', 'Rajshahi', 'Khulna', 'Barisal', 'Sylhet', 'Rangpur', 'Mymensingh'] },
  ];

  const handleSearch = (term) => {
    setSearchTerm(term);
  };

  const filteredHospitals = hospitalList.filter((hospital) => {
    const matchesSearch = hospital.name.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesFilters = filters.every((filter, index) => {
      if (selectedValues[index] === '') return true; // No filter selected
      if (filter.name === 'Location') return hospital.location === selectedValues[index];
      return true;
    });
    return matchesSearch && matchesFilters;
  });

  return (
    <div className="rout-container">
      <div className="hospital-container">
        <div className="heading caption">
          <h1>Hospitals of Vital Aid</h1>
        </div>
        <div className="hospital-filter-and-search">
          <div className="hospital-filter-container">
            {filters.map((filter, index) => (
              <div key={index} className="filter">
                <div className="hospital-filter-name">
                  <label>Location</label>
                </div>
                <select
                  value={selectedValues[index]}
                  onChange={(event) => handleSelectChange(index, event)}
                  className="hospital-filter-select"
                >
                  <option value="">All</option>
                  {filter.options.map((option, i) => (
                    <option key={i} value={option}>
                      {option}
                    </option>
                  ))}
                </select>
              </div>
            ))}
          </div>
          <SearchBox className="hospital-search" placeholder="Search Hospitals" onSearch={handleSearch} style={{ width: isMobile ? '100%' : '73%', height: isMobile ? "40px" : "100%", boxShadow: "0 0 8px black", margin: "0 0" }} />
        </div>

        <div className="view-hospitals">
          {filteredHospitals.map((hospital, index) => (
            <div className="hospital-cards" key={index}>
              <div className="img-section">
                <img src={hospital.img || 'placeholder.jpg'} alt='' />
              </div>
              <div className="hospital-detail">
                <div className="hospital-data">
                  <p>
                    <span id="hospital-name">{hospital.name}</span><br />
                    <span id="hospital-location">{hospital.address}</span>
                  </p>
                </div>
                <div className="hospital-btn">
                  <button onClick={() => handleViewDetails(hospital)}>View Details</button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
