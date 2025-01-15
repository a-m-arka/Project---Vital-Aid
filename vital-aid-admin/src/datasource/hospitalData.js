export const hospitalColumns = [
    { field: 'id', headerName: 'ID', width: 70 },
    {
        field: 'hospital', headerName: 'Hospital', width: 400,
        renderCell: (params) => {
            return (
                <div className="cellWithImg">
                    <img src={params.row.hospitalPhotoUrl} alt="" className="cellImg" />
                    {params.row.hospitalName}
                </div>
            )
        }
    }
];