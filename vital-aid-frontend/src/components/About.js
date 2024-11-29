import React from 'react'
import '../style/About.css';
import arkaImg from '../Images/arka.jpg';
import onimImg from '../Images/onim.jpg';
import fahimImg from '../Images/fahim.jpg';

export default function About() {
  const teamMembers = [
    { name: 'A. M. Arka', id: '2104028', image: arkaImg, phone: '014034555858'},
    { name: 'Omar Faiaz Onim', id: '2104029', image: onimImg, phone: '01890087192'},
    { name: 'Shahriar Khaled Fahim', id: '2104031', image: fahimImg, phone: '01978112764'}
  ];

  return (
    <div className="rout-container">

      <div className="about-us">

        <h1 className='caption'>About Us</h1>

        <section>
          <h2>Our Mission</h2>
          <p>Our mission is to provide compassionate, high-quality healthcare to our community. We strive to be a trusted partner in health and wellness, ensuring that every patient receives personalized care and support. By integrating advanced medical technologies and compassionate service, we aim to enhance the health and well-being of all we serve. We are committed to improving access to healthcare and advancing medical education to build a healthier future.</p>
        </section>

        <section>
          <h2>Our Vision</h2>
          <p>To be the leading provider of integrated healthcare services in the region, fostering an environment where patients receive exceptional care, medical professionals thrive, and communities flourish. We envision a world where quality healthcare is accessible to all, where innovation and patient-centric care drive excellence, and where we continuously improve the health standards of our society. Our vision extends to empowering individuals to make informed health decisions, supporting healthier lifestyles, and promoting sustainable healthcare practices.</p>
        </section>

        <section>
          <h2>Meet Our Team</h2>
          <div className="team">
            {teamMembers.map((member, index) => (
              <div key={index} className="team-member">
                <img src={member.image} alt={member.name} />
                <h3>{member.name}</h3>
                <p>Computer Science & Engineering<br/>CUET<br />{`ID: ${member.id}`}</p>
              </div>
            ))}
          </div>
        </section>

        <section>
          <h2>Our History</h2>
          <p>Founded in 2024, our organization began with a simple yet profound goal: to provide accessible, high-quality healthcare to underserved communities. In just a few months, we have grown from a small team of dedicated healthcare professionals to a robust organization, serving thousands of patients each year. With each milestone, we expanded our services, built state-of-the-art facilities, and forged partnerships with leading medical institutions. Our journey has been one of innovation, compassion, and resilience, and we remain steadfast in our commitment to improving healthcare access and quality for generations to come.</p>
        </section>

        <section>
          <h2>Services Overview</h2>
          <ul>
            <li>Online Appointments</li>
            <li>Ambulance Services</li>
            <li>Medicine Store</li>
          </ul>
        </section>

        <section>
          <h2>Contact Us</h2>
          <p>{`Phone: ${teamMembers[0].phone}, ${teamMembers[1].phone}, ${teamMembers[2].phone}`}</p>
          <p>Email: vitalaid@gmail.com</p>
        </section>

        <section>
          <h2>Get in Touch</h2>
          <p>Contact us today to register as a doctor or to be our partner!</p>
        </section>

      </div>

    </div>
  );
}
