import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import '../style/DoctorDetails.css';
import RatingStars from './ratingStars/RatingStars';
import { useGlobalContext } from '../context/GlobalContext';
// import RatingStars from './ratingStars/RatingStars';

export default function DoctorDetails() {
    const { state } = useLocation();
    const doctor = state?.doctor;
    const navigate = useNavigate();
    const { isLoggedIn } = useGlobalContext();
    const [isReviewOpen, setIsReviewOpen] = useState(false);
    const [isFeedbackOpen, setIsFeedbackOpen] = useState(false);
    const [reviews, setReviews] = useState([]);
    const [ratingValue, setRatingValue] = useState(0);
    const [feedbackComment, setFeedbackComment] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleAppoinment = (doctor) => {
        navigate('/appointment', { state: { doctor } });
    };

    const handleReview = async (doctor) => {
        if(isFeedbackOpen){
            setIsFeedbackOpen(false);
        }
        setIsReviewOpen(true);
        const token = localStorage.getItem('token');

        try {
            const response = await fetch(`http://localhost:8080/vital_aid/doctorRating/ratingsMadeForDoctor/${doctor.id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
            });
            if (response.ok) {
                const data = await response.json();
                setReviews(data);
                // console.log(data)
            }
        } catch (error) {
            console.error('Error fetching doctor reviews:', error);
        }
    };

    const handleFeedback = () => {
        if (isReviewOpen) {
            setIsReviewOpen(false);
        }
        setIsFeedbackOpen(true);
    };

    const handleRatingChange = (value) => {
        setRatingValue(Math.ceil(value));
    };

    const handleFeedbackSubmit = async () => {
        const token = localStorage.getItem('token');
        const feedbackData = {
            rating: ratingValue,
            review: feedbackComment,
        };

        try {
            const response = await fetch(`http://localhost:8080/vital_aid/doctorRating/rateDoctor/${doctor.id}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify(feedbackData),
            });

            if (response.ok) {
                setIsFeedbackOpen(false);
                setRatingValue(0);
                setFeedbackComment('');
                // alert('Feedback submitted successfully!');
                window.location.reload();
            } else {
                // alert('Failed to submit feedback. Please try again.');
                setErrorMessage('Failed to submit feedback. Please try again.');
            }
        } catch (error) {
            console.error('Error submitting feedback:', error);
        }
    };

    // Function to convert 24-hour format to 12-hour format
    const convertTo12HourFormat = (time) => {
        const [hour, minute] = time.split(':');
        const period = +hour < 12 ? 'AM' : 'PM';
        const convertedHour = +hour % 12 || 12;
        return `${convertedHour}:${minute} ${period}`;
    };

    if (!doctor) {
        return <p>Doctor details not available.</p>;
    }

    return (
        <div className="rout-container">
            <div className="doctor-details-page">
                <div className="parent-container-of-doctor-profile-main-container">
                    <div className="doctor-profile-main-container">
                        <div className="doctor-profile-picture-container">
                            <img src={doctor.doctorProfileImageUrl} alt="doctor-profile-pic" />
                        </div>
                        <div className="doctor-profile-details-container">
                            <div className="doctor-profile-details">
                                <div className="doctor-name-section">
                                    <span className="doctor-name-span">{doctor.personName}</span>
                                </div>

                                <div className="doctor-rating-section">
                                    <RatingStars ratingValue={doctor.doctorAverageRating} starSize={24} />
                                </div>

                                <div className="doctor-speciality-section">
                                    <span className="speciality-span">{doctor.doctorSpecialization.join(', ')}</span>
                                </div>

                                <div className="doctor-hospital-name-section">
                                    <span className="hospital-name-span">{doctor.hospitalName}</span>
                                </div>

                                <div className="doctor-address-section">
                                    <span className="address-value">{doctor.doctorCity}</span>
                                </div>
                            </div>

                            <div className="consultation-days-section">
                                <div className="consultation-day-heading">Consultation Days:</div>
                                <span className="consultation-day-values">
                                    {doctor.consultationDays ? doctor.consultationDays.join(', ') : 'Not available'}
                                </span>
                            </div>

                            <div className="consultation-time-fees-section">
                                <div className="consultation-time-section">
                                    <div className="consultation-time-heading">Time:</div>
                                    <span className="consultation-time-values">
                                        {doctor.consultingTime
                                            ? `${convertTo12HourFormat(doctor.consultingTime.startTime)} - ${convertTo12HourFormat(doctor.consultingTime.endTime)}`
                                            : 'Not available'}
                                    </span>
                                </div>
                                <div className="consultation-fees-section">
                                    <div className="consultation-fees-heading">Fees:</div>
                                    <span className="consultation-fees-values">{doctor.doctorFee ? `BDT ${doctor.doctorFee}` : 'Not available'}</span>
                                </div>
                            </div>

                            <div className="doctor-contact-information-section">
                                <div className="contact-phone-number-section">
                                    <div className="contact-headings">Contact:</div>
                                    <span className="contact-phone-number">{doctor.personPhone || 'Not available'}</span>
                                </div>
                                <div className="contact-email-section">
                                    <div className="contact-headings">Email:</div>
                                    <span className="contact-email">{doctor.personEmail || 'Not available'}</span>
                                </div>
                            </div>

                            <div className="button-section">
                                {(isLoggedIn && !isReviewOpen) && (
                                    <div className="doctor--button">
                                        <button onClick={() => handleReview(doctor)}>See Reviews</button>
                                    </div>
                                )}
                                {(isLoggedIn && isReviewOpen) && (
                                    <div className="doctor--button">
                                        <button onClick={() => setIsReviewOpen(false)}>Close Reviews</button>
                                    </div>
                                )}
                                {(isLoggedIn && !isFeedbackOpen) && (
                                    <div className="doctor--button">
                                        <button onClick={() => handleFeedback()}>Give Feedback</button>
                                    </div>
                                )}
                                <div className="doctor--button">
                                    <button onClick={() => handleAppoinment(doctor)}>Book Appointment</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                {isReviewOpen && (
                    <div className="review-modal">
                        <div className="review-modal-content">
                            <h2>Reviews for {doctor.personName}</h2>
                            {reviews.length > 0 ? (
                                <div className="review-list">
                                    {reviews.map((review, index) => (
                                        <div key={index} className="review-item" style={{ marginBottom: '30px' }}>
                                            <p className="review-by" style={{ fontWeight: "bold", marginBottom: "0" }}>{review.ratedByUserName || 'Anonymous'}</p>
                                            <RatingStars ratingValue={review.rating} starSize={20} />
                                            <p className="review-text">{review.review}</p>
                                        </div>
                                    ))}
                                </div>
                            ) : (
                                <p>No reviews available for this doctor.</p>
                            )}
                        </div>
                    </div>
                )}
                {isFeedbackOpen && (
                    <div className="feedback-modal">
                        <div className="feedback-modal-content">
                            <h2>Feedback for {doctor.personName}</h2>
                            <RatingStars
                                ratingValue={ratingValue}
                                setRatingValue={handleRatingChange}
                                starSize={40}
                                readOnly={false}
                            />
                            <form onSubmit={() => handleFeedbackSubmit()}>
                                <textarea
                                    value={feedbackComment}
                                    onChange={(e) => setFeedbackComment(e.target.value)}
                                    placeholder="Write your feedback here..."
                                    rows="4"
                                    style={{ width: '100%', marginTop: '10px', padding: '10px', fontSize: '1rem' }}
                                    required
                                />
                                <div style={{ marginTop: '10px' }}>
                                    {errorMessage && <p className="error-message" style={{ color: 'red' }}>{errorMessage}</p>}
                                    <button type="submit" className='feedback-submit-btn'>
                                        Submit Feedback
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}
