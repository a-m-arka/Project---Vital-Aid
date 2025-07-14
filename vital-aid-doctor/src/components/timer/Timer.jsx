import React, { useEffect, useState } from 'react';
import './Timer.scss'

const Timer = ({ startTime, onComplete }) => {
    const [timeLeft, setTimeLeft] = useState(startTime);

    useEffect(() => {
        if (timeLeft <= 0) {
            if (onComplete) onComplete(); // Call the onComplete callback if provided
            return;
        }

        const timer = setInterval(() => {
            setTimeLeft((prevTime) => prevTime - 1);
        }, 1000);

        return () => clearInterval(timer); // Cleanup on unmount or when timeLeft changes
    }, [timeLeft, onComplete]);

    const formatTime = (time) => {
        const minutes = Math.floor(time / 60);
        const seconds = time % 60;
        return `${minutes.toString().padStart(2, '0')} : ${seconds.toString().padStart(2, '0')}`;
    };

    return (
        <div className='timer'>
            <p>{formatTime(timeLeft)}</p>
        </div>
    );
};

export default Timer;
