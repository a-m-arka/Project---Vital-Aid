export const doctorColumns = [
    { field: 'id', headerName: 'ID', width: 70 },
    {
        field: 'doctor', headerName: 'Doctor', width: 400,
        renderCell: (params) => {
            return (
                <div className="cellWithImg">
                    <img src={params.row.doctorPhotoUrl} alt="" className="cellImg" />
                    {params.row.doctorName}
                </div>
            )
        }
    }
];
