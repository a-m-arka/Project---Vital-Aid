import React from 'react'
import './RatingCircle.scss'

const RatingCircle = ({ rating, feedbackCount }) => {
    const maxRating = 5
    const percentage = (rating / maxRating) * 100

    return (
        <div className="circular-rating">
            <svg viewBox="0 0 36 36" className="circle-chart">
                <path
                    className="circle-bg"
                    d="M18 2.0845
                               a 15.9155 15.9155 0 0 1 0 31.831
                               a 15.9155 15.9155 0 0 1 0 -31.831"
                />
                <path
                    className="circle"
                    strokeDasharray={`${percentage}, 100`}
                    d="M18 2.0845
                               a 15.9155 15.9155 0 0 1 0 31.831
                               a 15.9155 15.9155 0 0 1 0 -31.831"
                />
                <text x="18" y="17" className="circle-text rating-value">
                    {rating.toFixed(1)}/5
                </text>
                <text x="18" y="22.5" className="circle-text rating-count">
                    ({feedbackCount} Feedbacks)
                </text>
            </svg>
        </div>
    )
}

export default RatingCircle
