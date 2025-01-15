export const userColumns = [
    { field: 'id', headerName: 'ID', width: 70 },
    {
        field: 'user', headerName: 'User', width: 400,
        renderCell: (params) => {
            return (
                <div className="cellWithImg">
                    <img src={params.row.profileImageUrl} alt="" className="cellImg" />
                    {params.row.personName}
                </div>
            )
        }
    }
];