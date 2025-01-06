import React from 'react'
import './Home.scss'
import Sidebar from '../../components/sidebar/Sidebar'
import Widget from '../../components/widget/Widget'

const Home = () => {
  return (
    <div>
      <div className="home">

        <Sidebar />
        <div className="homeContainer">
          <div className="widgets">
            <Widget type="user" />
            <Widget type="doctor" />
            <Widget type="hospital" />
            <Widget type="ambulance" />
            <Widget type="product" />
          </div>
        </div>

      </div>
    </div>
  )
}

export default Home
