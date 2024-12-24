import React, { useState } from 'react'
import '../style/ForgotPass.css'
import { useNavigate } from 'react-router-dom'
import Stepper from './Stepper';
import { EnterEmail, EnterOtp, NewPassword, Success } from './ForgetPassForm';

export default function ForgotPass() {
  const navigate = useNavigate();
  const [currentStep, setCurrentStep] = useState(1);

  const handleNext = () => {
    setCurrentStep(currentStep + 1);
  };

  const steps = [
    {
      name: "Email",
      Component: <EnterEmail email="arka@user.com" onSubmit={handleNext} />,
    },
    {
      name: "Otp",
      Component: <EnterOtp otp="1234" onSubmit={handleNext} />,
    },
    {
      name: "New Password",
      Component: <NewPassword onSubmit={handleNext} />,
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
    <div className="rout-container">
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
    </div>

  );
}
