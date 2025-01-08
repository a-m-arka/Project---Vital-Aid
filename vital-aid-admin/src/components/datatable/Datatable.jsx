import React, { useState } from 'react'
import './Datatable.scss'
import { DataGrid } from '@mui/x-data-grid';
import Paper from '@mui/material/Paper';
import { useNavigate } from 'react-router-dom';

const paginationModel = { page: 0, pageSize: 10 };

const Datatable = ({ type, rows, columns, adminControl }) => {

    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState('');

    const deleteRow = async (url) => {
        setMessage('');
        setLoading(true);
        const token = localStorage.getItem('adminToken');

        try {
            const response = await fetch(url, {
                method: 'DELETE',
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            if (response.ok) {
                console.log('Success');
                window.location.reload();
            } else {
                const errorData = await response.text();
                setMessage(errorData.message || 'An error occurred');
            }

        } catch (error) {
            console.log('Error: ', error);
            setMessage('Failed to delete ', type);
        } finally {
            setLoading(false);
        }

    };

    const handleViewClick = (row) => {
        navigate(`/${type}/${row.id}`, { state: { row } });
    };

    const handleUpdateClick = (row) => {
        navigate(`/${type}/${row.id}/update`, { state: { row } });
    };

    const handleDeleteClick = (row) => {
        switch (type) {
            case ('doctor'):
                deleteRow(`http://localhost:8080/vital_aid/doctors/deleteDoctor/${row.id}`);
                break;
            case ('hospital'):
                deleteRow(`http://localhost:8080/vital_aid/hospitals/deleteHospital/${row.id}`);
                break;
            case ('ambulance'):
                deleteRow(`http://localhost:8080/vital_aid/ambulance/deleteAmbulanceByNumPlate/${row.id}`);
                break;
            case ('product'):
                deleteRow(`http://localhost:8080/vital_aid/medical_store/deleteProduct/${row.id}`);
                break;
            default:
                break;
        }
    };

    const actionColumn = [
        {
            field: 'action', headerName: 'Action', width: 400,
            renderCell: (params) => {
                return (
                    <div className="cellWithAction">
                        {type !== 'ambulance' && (
                            <button className="viewButton" onClick={() => handleViewClick(params.row)} disabled={loading}>View</button>
                        )}
                        {adminControl && (
                            <>
                                <button className="updateButton" onClick={() => handleUpdateClick(params.row)} disabled={loading}>Update</button>
                                <button className="deleteButton" onClick={() => handleDeleteClick(params.row)} disabled={loading}>Delete</button>
                            </>
                        )}
                    </div>
                )
            }
        }
    ];

    return (
        <div className='datatable'>
            {message && (
                <p className="message">{message}</p>
            )}
            <Paper sx={{ height: '100%', width: '100%' }}>
                <DataGrid
                    rows={rows}
                    columns={columns.concat(actionColumn)}
                    initialState={{ pagination: { paginationModel } }}
                    pageSizeOptions={[10, 20]}
                    // checkboxSelection
                    sx={{ border: 0 }}
                />
            </Paper>
        </div>
    )
}

export default Datatable
