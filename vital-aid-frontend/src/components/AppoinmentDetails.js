import React from 'react';
import '../style/AppoinmentDetails.scss';
import { useLocation, useNavigate } from 'react-router-dom';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';
import ReactDOMServer from 'react-dom/server';


const AppoinmentDetails = () => {
    const { state } = useLocation();
    const data = state?.appointmentData;
    const navigate = useNavigate();

    const formatDate = (dateStr) => {
        const date = new Date(dateStr);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}-${month}-${year}`;
    };

    const formatTime = (timeStr) => {
        const timeArray = timeStr.split(':');
        let hours = parseInt(timeArray[0], 10);
        let minutes = timeArray[1] || '00';
        const ampm = hours >= 12 ? 'PM' : 'AM';

        hours = hours % 12;
        hours = hours ? hours : 12; // handle midnight and noon
        minutes = String(minutes).padStart(2, '0');

        return `${hours}:${minutes} ${ampm}`;
    };

    const pdf = (
        <div className="apoointment-pdf">
            <div className="appointment-details">
                <header className="header">
                    <h1>Vital Aid Appointment Details</h1>
                </header>
                <main className="form-container">
                    <div className="form-item">
                        <label>Appointment Number :</label>
                        <span>{data?.appointmentToken}</span>
                    </div>
                    <div className="form-item">
                        <label>Doctor Name:</label>
                        <span>{data?.appointmentWith}</span>
                    </div>
                    <div className="form-item">
                        <label>Patient Name :</label>
                        <span>{data?.patientName}</span>
                    </div>
                    <div className="form-item">
                        <label>Age:</label>
                        <span>{data?.patientAge}</span>
                    </div>
                    <div className="form-item">
                        <label>Phone Number:</label>
                        <span>{data?.patientPhone}</span>
                    </div>
                    <div className="form-item">
                        <label>Email:</label>
                        <span>{data?.patientEmail}</span>
                    </div>
                    <div className="form-item">
                        <label>Gender:</label>
                        <span>{data?.patientGender}</span>
                    </div>
                    <div className="form-item">
                        <label>Appointment Date:</label>
                        <span>{formatDate(data?.appointmentDate)}</span>
                    </div>
                    <div className="form-item">
                        <label>Appointment Day:</label>
                        <span>{data?.visitDay}</span>
                    </div>
                    <div className="form-item">
                        <label>Appointment Time:</label>
                        <span>{formatTime(data?.doctorAppointmentStartTime)}</span>
                    </div>
                    <div className="form-item">
                        <label>Reason For Visit:</label>
                        <span>{data?.reasonForVisit}</span>
                    </div>
                </main>
                <footer className="footer">
                    <div>Vital Aid</div>
                    <p>Always At Your Need</p>
                    <p>Email: vitalaid2104@gmail.com</p>
                    <p>© 2024 Vital Aid Copyright</p>
                </footer>
            </div>
        </div>
    );

    const generatePDF = async () => {
        const element = document.createElement('div');
        element.classList.add('apoointment-pdf');
        const container = document.createElement('div');
        container.innerHTML = ReactDOMServer.renderToStaticMarkup(pdf);
        element.appendChild(container);
        document.body.appendChild(element);
        const canvas = await html2canvas(element, { scale: 2 });
        const imgData = canvas.toDataURL('image/png');
        const pdfDoc = new jsPDF('p', 'mm', 'a4');
        const pdfWidth = pdfDoc.internal.pageSize.getWidth();
        const pdfHeight = (canvas.height * pdfWidth) / canvas.width;
        pdfDoc.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight);
        pdfDoc.save('Vital-Aid-Appointment.pdf');
        document.body.removeChild(element);
    };



    const handleDownloadPdf = () => {
        generatePDF();
        navigate('/appointmentlist');
    };

    return (
        <div className="rout-container">
            <div className="appointment-details-container">
                <div className="heading caption">
                    <h1>Appointment Details</h1>
                </div>
                <div className="details-section">
                    <div className="data">
                        <div className="label">Appointment Token</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.appointmentToken}</div>
                    </div>
                    <div className="data">
                        <div className="label">Doctor Name</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.appointmentWith}</div>
                    </div>
                    <div className="data">
                        <div className="label">Patient Name</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.patientName}</div>
                    </div>
                    <div className="data">
                        <div className="label">Patient Age</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.patientAge}</div>
                    </div>
                    <div className="data">
                        <div className="label">Patient Gender</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.patientGender}</div>
                    </div>
                    <div className="data">
                        <div className="label">Patient Phone</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.patientPhone}</div>
                    </div>
                    <div className="data">
                        <div className="label">Patient Email</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.patientEmail}</div>
                    </div>
                    <div className="data">
                        <div className="label">Appointment Date</div>
                        <div className="colon">:</div>
                        <div className="label-data">{formatDate(data?.appointmentDate)}</div>
                    </div>
                    <div className="data">
                        <div className="label">Appointment Day</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.visitDay}</div>
                    </div>
                    <div className="data">
                        <div className="label">Appointment Time</div>
                        <div className="colon">:</div>
                        <div className="label-data">{formatTime(data?.doctorAppointmentStartTime)}</div>
                    </div>
                    <div className="data">
                        <div className="label">Reason</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.reasonForVisit}</div>
                    </div>
                </div>
                <div className="download-btn" onClick={handleDownloadPdf}>
                    <i className="fa-solid fa-download"></i>
                    Download PDF
                </div>
            </div>
        </div>
    );
};

export default AppoinmentDetails;
