export const calculateAverageRating = (ratings) => {
  if (!ratings || ratings.length === 0) return 0;

  const total = ratings.reduce((sum, r) => sum + r.rating, 0);
  const average = total / ratings.length;

  return Number(average.toFixed(1));
};
