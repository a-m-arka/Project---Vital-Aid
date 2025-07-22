import Rating from 'react-rating';
import { FaStar, FaRegStar } from 'react-icons/fa';

function RatingStars({ ratingValue, setRatingValue, starSize = 32, readOnly = true }) {
  return (
    <Rating
      initialRating={ratingValue}
      onChange={(value) => setRatingValue && setRatingValue(value)}
      readonly={readOnly}
      fractions={10}
      emptySymbol={<FaRegStar color="#9d4edd" size={starSize} />}
      fullSymbol={<FaStar color="#3c096c" size={starSize} />}
    />
  );
}

export default RatingStars;
