import React, { useEffect, useRef, useState } from 'react';
import './Stepper.scss';

const Stepper = ({ stepsConfig = [], currentStep }) => {
    const stepRef = useRef([]);
    const [margins, setMargins] = useState({
        marginLeft: 0,
        marginRight: 0,
    });

    useEffect(() => {
        setMargins({
            marginLeft: stepRef.current[0].offsetWidth / 2,
            marginRight: stepRef.current[stepsConfig.length - 1].offsetWidth / 2,
        });
    }, [stepRef, stepsConfig.length]);

    const calculateProgressBarWidth = () => {
        return ((Math.min((currentStep - 1), 3)) / (stepsConfig.length - 1)) * 100;
    };

    return (
        <div className="stepper">
            {stepsConfig.map((step, index) => (
                <div
                    key={step.name}
                    ref={(el) => (stepRef.current[index] = el)}
                    className={`step ${currentStep > index + 1 ? "complete" : ""} ${currentStep === index + 1 ? "active" : ""}`}
                >
                    <div className="step-number">
                        {currentStep > index + 1 ? (
                            <span>&#10003;</span>
                        ) : (
                            index + 1
                        )}
                    </div>
                </div>
            ))}

            <div
                className="progress-bar"
                style={{
                    // width: calc(100% - ${margins.marginLeft + margins.marginRight}px),
                    marginLeft: margins.marginLeft,
                    marginRight: margins.marginRight,
                }}
            >
                <div className="progress" style={{ width: `${calculateProgressBarWidth()}%` }}></div>
            </div>
        </div>
    );
};

export default Stepper;
