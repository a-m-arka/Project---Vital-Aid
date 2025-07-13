import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Slider from 'react-slick';
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import '../style/Home.css';
import Footer from './Footer';

import servicesData from '../data/services.json';
import testimonialsData from '../data/testimonials.json';

import { useGlobalContext } from '../context/GlobalContext';

export default function Home() {
    const [isMobile, setIsMobile] = useState(false);
    const { doctorData } = useGlobalContext();

    useEffect(() => {
        const handleResize = () => {
            setIsMobile(window.innerWidth <= 768);
        };
        handleResize();
        window.addEventListener('resize', handleResize);
        return () => window.removeEventListener('resize', handleResize);
    }, []);

    const PrevArrow = ({ onClick }) => (
        <div className="slider-arrow slider-arrow-prev" onClick={onClick}>
            &#10094;
        </div>
    );

    const NextArrow = ({ onClick }) => (
        <div className="slider-arrow slider-arrow-next" onClick={onClick}>
            &#10095;
        </div>
    );

    const settings = {
        dots: true,
        infinite: true,
        speed: 1000,
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
        draggable: isMobile,
        arrows: !isMobile,
        prevArrow: !isMobile ? <PrevArrow /> : null,
        nextArrow: !isMobile ? <NextArrow /> : null
    };

    const doctorToShow = doctorData.slice(0, 5);

    return (
        <div className="rout-container">
            <div className="home-container">
                <div className="hero-content">
                    <h4>Welcome to <span className="highlight highlight-name">Vital Aid</span></h4>
                    <p>WE ARE <br /> <span className="highlight">ALWAYS</span> WITH<br /> YOU</p>
                    <Link to="/doctors" className="appointment-btn">
                        Book Appointment
                    </Link>
                </div>

                <div className="slider-container">
                    <h2 className='caption'>Explore Our Range of Services</h2>
                    <Slider {...settings}>
                        {servicesData.map((service, index) => (
                            <div key={index} className="slider-item">
                                <div
                                    className="service-card"
                                    style={{ backgroundImage: `url(${service.img})` }}
                                >
                                    <div className="service-card-content">
                                        <h2 className='service-card-heading'>{service.caption}</h2>
                                        <h4 className='service-card-msg'>{service.line1}<br />{service.line2}</h4>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </Slider>
                </div>

                <div className="doctor-section">
                    <h2 className="caption">Meet Your Ideal Doctors</h2>
                    <div className="home-doctor-container">
                        <div className="home-doctor-grid">
                            {doctorToShow.map((doctor, index) => (
                                <div className="doctor-item" key={index}>
                                    <div className="doctor-content">
                                        <img src={doctor.doctorPhotoUrl} alt="" className="doctor-photo" />
                                        <h4 className="doctor-name">{doctor.doctorName}</h4>
                                        <p className="doctor-speciality"><span className='doctor-highlight'>Field: </span>{doctor.specializationField}</p>
                                        <p className="doctor-hospital">{doctor.hospitalName}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <Link to="/doctors" className="more-btn">
                            <div>View All Doctors</div>
                            <i className="fa-solid fa-arrow-right"></i>
                        </Link>
                    </div>
                </div>

                <div className="testimonials-section">
                    <h2 className='caption'>What Our Clients Say?</h2>
                    <div className="testimonials-grid">
                        {testimonialsData.map((testimonial, index) => (
                            <div key={index} className="testimonial-item">
                                <div className="testimonial-content">
                                    <img src={testimonial.photo} alt={`${testimonial.name}`} className="testimonial-photo" />
                                    <h4 className="testimonial-name">{testimonial.name}</h4>
                                    <p className="testimonial-feedback">"{testimonial.feedback}"</p>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
                <Footer />
            </div>
        </div>
    );
}
