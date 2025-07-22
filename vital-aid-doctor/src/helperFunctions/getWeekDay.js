export const getWeekDay = (dateString) => {
    if (!dateString) return '';
    return new Date(dateString).toLocaleDateString('en-US', { weekday: 'long' });
}
