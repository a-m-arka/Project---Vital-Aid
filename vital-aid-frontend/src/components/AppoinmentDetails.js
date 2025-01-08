import React from 'react';
import '../style/AppoinmentDetails.scss';
import { useLocation, useNavigate } from 'react-router-dom';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';

const AppoinmentDetails = () => {
    const { state } = useLocation();
    const data = state?.appointmentData;
    const navigate = useNavigate();

    const generatePDF = async () => {
        const element = document.querySelector('.details-section');
        const canvas = await html2canvas(element, { scale: 2 });
        const imgData = canvas.toDataURL('image/png');
        const pdf = new jsPDF('p', 'mm', 'a4');
        const pdfWidth = pdf.internal.pageSize.getWidth();
        const pdfHeight = (canvas.height * pdfWidth) / canvas.width;
        pdf.setFontSize(18);
        pdf.setFont('helvetica', 'bold');
        pdf.text('Vital Aid Appointment', pdfWidth / 2, 10, { align: 'center' });
        pdf.addImage(imgData, 'PNG', 10, 20, pdfWidth - 20, pdfHeight - 30);
        pdf.save('Vital-Aid-Appoinment.pdf');
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
                        <div className="label">Visiting Day</div>
                        <div className="colon">:</div>
                        <div className="label-data">{data?.visitDay}</div>
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
