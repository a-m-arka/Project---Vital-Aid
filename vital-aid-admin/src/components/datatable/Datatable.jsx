import React from 'react'
import './Datatable.scss'
import { DataGrid } from '@mui/x-data-grid';
import Paper from '@mui/material/Paper';
import { useNavigate } from 'react-router-dom';

const paginationModel = { page: 0, pageSize: 10 };

const Datatable = ({ type, rows, columns, adminControl }) => {

    const navigate = useNavigate();

    const handleViewClick = (row) => {
        navigate(`/${type}/${row.id}`, { state: { row } });
    };

    const handleUpdateClick = (row) => {
        navigate(`/${type}/${row.id}/update`, { state: { row } });
    };

    const actionColumn = [
        {
            field: 'action', headerName: 'Action', width: 400,
            renderCell: (params) => {
                return (
                    <div className="cellWithAction">
                        <div className="viewButton" onClick={() => handleViewClick(params.row)}>View</div>
                        {adminControl && (
                            <div className="updateButton" onClick={() => handleUpdateClick(params.row)}>Update</div>
                        )}
                        <div className="deleteButton">Delete</div>
                    </div>
                )
            }
        }
    ];

    return (
        <div className='datatable'>
            <Paper sx={{ height: '100%', width: '100%' }}>
                <DataGrid
                    rows={rows}
                    columns={columns.concat(actionColumn)}
                    initialState={{ pagination: { paginationModel } }}
                    pageSizeOptions={[ 10, 20 ]}
                    checkboxSelection
                    sx={{ border: 0 }}
                />
            </Paper>
        </div>
    )
}

export default Datatable
