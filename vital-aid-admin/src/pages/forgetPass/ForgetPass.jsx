import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import './ForgetPass.scss';
import Stepper from '../../components/stepper/Stepper';
import { EnterEmail, EnterOtp, NewPassword, Success } from '../../components/forgetPassForm/ForgetPassForm';

const ForgetPass = () => {
    const navigate = useNavigate();
    const [currentStep, setCurrentStep] = useState(1);

    const handleNext = () => {
        setCurrentStep(currentStep + 1);
    };

    const steps = [
        {
            name: "Email",
            Component: <EnterEmail email="arka@admin.com" onSubmit={handleNext}/>,
        },
        {
            name: "Otp",
            Component: <EnterOtp otp="1234" onSubmit={handleNext}/>,
        },
        {
            name: "New Password",
            Component: <NewPassword onSubmit={handleNext}/>,
        },
        {
            name: "Success",
            Component: <Success />,
        }
    ];

    const handleFinish = () => {
        navigate('/login');
    };

    const ActiveComponent = steps[currentStep - 1]?.Component;

    return (
        <div className='forget-pass-container'>
            <div className="forget-pass-form">
                <h2>Forgot Password?</h2>
                <Stepper stepsConfig={steps} currentStep={currentStep} />
                {ActiveComponent}
                {currentStep === steps.length && (
                    <button className="btn" onClick={handleFinish}>
                        Finish
                    </button>
                )}
            </div>
        </div>
    );
};

export default ForgetPass;
