export const doctorColumns = [
    { field: 'id', headerName: 'ID', width: 70 },
    {
        field: 'doctor', headerName: 'Doctor', width: 400,
        renderCell: (params) => {
            return (
                <div className="cellWithImg">
                    <img src={params.row.doctorProfileImageUrl} alt="" className="cellImg" />
                    {params.row.personName}
                </div>
            )
        }
    }
];
