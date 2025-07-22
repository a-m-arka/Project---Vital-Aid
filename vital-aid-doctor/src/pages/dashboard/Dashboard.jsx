import React from 'react'
import { useNavigate } from 'react-router-dom';
import './Dashboard.scss'
import RatingCircle from '../../components/ratingCircle/RatingCircle'
import Graph from '../../components/graph/Graph'
import { formatDate } from '../../helperFunctions/formatDate';
import { useGlobalContext } from '../../context/GlobalContext';
import { calculateAverageRating } from '../../helperFunctions/avgRating';
import { getWeekDay } from '../../helperFunctions/getWeekDay';

const Dashboard = () => {
    const { ratings, appointments } = useGlobalContext();
    const feedbackCount = ratings.length;
    const rating = calculateAverageRating(ratings);

    const today = new Date().toISOString().split('T')[0];

    const navigate = useNavigate();

    const handleRatingClick = () => {
        navigate('/ratings');
    }

    const handleViewDetails = (appointment) => {
        navigate('/appointment-details', { state: { appointment } });
    }

    const pastAppointments = appointments.filter(
        (appt) => appt.appointmentDate < today
    );

    const todayAppointments = appointments.filter(
        (appt) => appt.appointmentDate === today
    );

    const frequencyMap = pastAppointments.reduce((acc, appt) => {
        const date = appt.appointmentDate;
        if (!acc[date]) acc[date] = 0;
        acc[date]++;
        return acc;
    }, {});

    const graphData = Object.entries(frequencyMap)
        .map(([date, count]) => ({
            date: formatDate(date),
            count,
        }))
        .sort((a, b) => new Date(a.date) - new Date(b.date));

    return (
        <div className="dashboard">
            <div className="top">
                <div className="left" onClick={handleRatingClick}>
                    <h2>Current Rating</h2>
                    <RatingCircle rating={rating} feedbackCount={feedbackCount} />
                </div>

                <div className="right">
                    <h2>Past Appointments Chart</h2>
                    <div className='graph-container' style={{ width: '900px', height: '230px' }}>
                        <Graph data={graphData} xDataKey="date" yDataKey="count" />
                    </div>
                </div>
            </div>

            <div className="bottom">
                <h2>Today's Appointments</h2>
                <div className="appointments-container">
                    <div className="day-group">
                        <h3>{getWeekDay(today)}, {formatDate(today)} <span>({todayAppointments.length} Appointments)</span></h3>
                        <ul>
                            {todayAppointments.length === 0 ? (
                                <p>No appointments.</p>
                            ) : (
                                todayAppointments.map((appt, index) => (
                                    <li key={index} onClick={() => handleViewDetails(appt)}>
                                        {appt.patientName} - {appt.reasonForVisit}
                                    </li>
                                ))
                            )}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Dashboard
