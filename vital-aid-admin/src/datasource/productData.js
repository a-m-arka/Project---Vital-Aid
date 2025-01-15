export const productColumns = [
    { field: 'id', headerName: 'ID', width: 100 },
    {
        field: 'product', headerName: 'Product', width: 400,
        renderCell: (params) => {
            return (
                <div className="cellWithImg">
                    <img src={params.row.productPhotoUrl} alt="" className="cellImg" />
                    {params.row.productName}
                </div>
            )
        }
    }
];