import React, { useState } from 'react'
import './New.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import { useLocation } from 'react-router-dom'
import AddDoctorForm from '../../components/addNewForms/AddDoctorForm'
import AddProductForm from '../../components/addNewForms/AddProductForm'
import AddHospitalForm from '../../components/addNewForms/AddHospitalForm'
import AddAmbulanceForm from '../../components/addNewForms/AddAmbulanceForm'

const New = ({ type }) => {




  return (
    <div className='new'>
      <Sidebar />
      <div className="newContainer">
        <div className="top">
          <h1>ADD NEW {String(type).toUpperCase()}</h1>
        </div>
        {type === 'doctor' && <AddDoctorForm />}
        {type === 'product' && <AddProductForm />}
        {type === 'hospital' && <AddHospitalForm />}
        {type === 'ambulance' && <AddAmbulanceForm />}
      </div>
    </div>
  )
}

export default New