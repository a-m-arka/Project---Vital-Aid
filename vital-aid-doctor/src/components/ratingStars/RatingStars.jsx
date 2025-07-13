import Rating from 'react-rating';
import { FaStar, FaRegStar } from 'react-icons/fa';

function RatingStars({ ratingValue, starSize = 32 }) {
  return (
    <Rating
      initialRating={ratingValue}
      readonly
      fractions={10}
      emptySymbol={<FaRegStar color="#ffd9007e" size={starSize} />}
      fullSymbol={<FaStar color="#ffd700" size={starSize} />}
    />
  );
}

export default RatingStars;
