import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../style/Doctors.css';
import SearchBox from './SearchBox';
import doctorList from '../data/doctors.json';
import filters from '../data/doctorFilters.json';

export default function Doctors() {
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedValues, setSelectedValues] = useState(Array(4).fill('')); // Update for the number of filters
    const navigate = useNavigate();

    const handleSearch = (term) => {
        setSearchTerm(term);
    };

    const handleSelectChange = (index, event) => {
        const updatedValues = [...selectedValues];
        updatedValues[index] = event.target.value;
        setSelectedValues(updatedValues);
    };

    const filteredDoctors = doctorList.filter((doctor) => {
        const matchesSearch = doctor.name.toLowerCase().includes(searchTerm.toLowerCase());
        const matchesFilters = filters.every((filter, index) => {
            if (selectedValues[index] === '') return true; // No filter selected
            if (filter.name === 'Location') return doctor.location === selectedValues[index];
            if (filter.name === 'Field') return doctor.medicalField === selectedValues[index];
            if (filter.name === 'Hospital') return doctor.hospital === selectedValues[index];
            if (filter.name === 'Availability') return doctor.consultationDays.includes(selectedValues[index]);
            return true;
        });
        return matchesSearch && matchesFilters;
    });

    return (
        <div className="rout-container">
            <div className="doctor-container">
                <div className="heading caption">
                    <h1>Doctors of Vital Aid</h1>
                </div>

                <div className="filter-and-search">
                    <SearchBox
                        placeholder="Search Doctors"
                        style={{ width: "100%", height: "40px", boxShadow: "0 0 8px black" }}
                        onSearch={handleSearch}
                    />

                    <div className="filter-container">
                        {filters.map((filter, index) => (
                            <div key={index} className="filter">
                                <div className="filter-name">
                                    <label>{filter.name}</label>
                                </div>
                                <select
                                    value={selectedValues[index]}
                                    onChange={(event) => handleSelectChange(index, event)}
                                    className="filter-select"
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
                </div>

                <div className="view-doctors">
                    {filteredDoctors.map((doctor, index) => (
                        <div className="doctor-cards" key={index}>
                            <div className="img-section">
                                <img src={doctor.img} alt="" />
                            </div>
                            <div className="doctor-detail">
                                <div className="doctor-data">
                                    <p>
                                        <span id="doctor-name">{doctor.name}</span><br />
                                        <span id="doctor-speciality">{doctor.speciality}</span><br />
                                        <span id="doctor-hospital">{doctor.hospital}</span><br />
                                        <span id="doctor-location">{doctor.location}</span>
                                    </p>
                                </div>
                                <div className="doctor-btn">
                                    <button onClick={() => navigate('/appoinment', { state: { doctor } })}>
                                        Book Appointment
                                    </button>
                                    <button onClick={() => navigate('/doctor_details', { state: { doctor } })}>
                                        View Details
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}
