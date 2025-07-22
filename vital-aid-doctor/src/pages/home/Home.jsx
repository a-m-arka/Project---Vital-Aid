import React from 'react'
import './Home.scss';
import { useNavigate } from 'react-router-dom';

const Home = () => {
    const navigate = useNavigate();

    const handleRegister = () => {
        navigate('/signup');
    }

    const handleLogin = () => {
        navigate('/login');
    }

    return (
        <div className='landing-page'>
            <div className="content">
                <h1>Vital Aid</h1>
                <h3 className="tagline">Connecting You to Those Who Need You Most</h3>
                <button className='home-btn' onClick={handleRegister}>Register as a Doctor</button>
                <p>Already registered?</p>
                <button className='home-btn' onClick={handleLogin}>Login</button>
            </div>
        </div>
    )
}

export default Home
