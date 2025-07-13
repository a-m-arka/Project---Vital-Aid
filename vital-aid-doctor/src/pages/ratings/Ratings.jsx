import React from 'react';
import './Ratings.scss';
import RatingStars from '../../components/ratingStars/RatingStars';
import { Box, Typography, LinearProgress } from '@mui/material';
import { calculateAverageRating } from '../../helperFunctions/avgRating';

import { doctorRatings } from '../../temporaryData/doctorRatings';

const RatingBar = ({ rating, percentage }) => (
  <Box display="flex" alignItems="center" gap={1}>
    <Typography variant="body2" width={12}>{rating}</Typography>
    <Box flex={1}>
      <LinearProgress
        variant="determinate"
        value={percentage}
        sx={{
          height: 10,
          borderRadius: 5,
          backgroundColor: 'color-mix(in srgb, var(--secondary-color), white 5%)',
          '& .MuiLinearProgress-bar': {
            backgroundColor: 'var(--primary-color)',
            borderRadius: 5,
          }
        }}
      />
    </Box>
    <Typography variant="body2" width={24} textAlign="right">{percentage.toFixed(0)}%</Typography>
  </Box>
);

const Ratings = () => {
  const totalRatings = doctorRatings.length;
  const averageRating = calculateAverageRating(doctorRatings);

  const ratingCounts = [1, 2, 3, 4, 5].reduce((acc, rating) => {
    acc[rating] = doctorRatings.filter(r => Math.floor(r.rating) === rating).length;
    return acc;
  }, {});

  const distributionData = [5, 4, 3, 2, 1].map(star => ({
    rating: star,
    percentage: totalRatings > 0 ? (ratingCounts[star] / totalRatings) * 100 : 0,
  }));

  return (
    <div className='ratings-container'>
      <div className="overall-rating-section">
        <h2>Overall Rating</h2>
        <div className="rating-summary">
          <div className="avg-rating">
            <p className="rating-val">{averageRating}</p>
            <RatingStars ratingValue={averageRating} starSize={40} />
            <p className="feedback-count">{totalRatings} Feedbacks</p>
          </div>
          <div className="rating-distribution">
            <Box width="100%" display="flex" flexDirection="column" gap={1}>
              {distributionData.map(({ rating, percentage }) => (
                <RatingBar key={rating} rating={rating} percentage={percentage} />
              ))}
            </Box>
          </div>
        </div>
      </div>

      <div className="feedback-section">
        <h2>Feedbacks</h2>
        <div className="rating-grid">
          {doctorRatings.map((rating, index) => (
            <div key={index} className="rating-card">
              <h4 className="user-name">{rating.ratedByUserName}</h4>
              <div className="rating-stars">
                <RatingStars ratingValue={rating.rating} starSize={18} />
              </div>
              <p className="review-text">{rating.review}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Ratings;
