export const ambulanceColumns = [
    // { field: 'id', headerName: 'ID', width: 70 },
    { field: 'id', headerName: 'Number Plate', width: 200 },
    // {
    //     field: 'status', headerName: 'Status', width: 200,
    //     renderCell: (params) => {
    //         return (
    //             <div className="cellWithStatus"
    //                 style={{
    //                     color: params.row.status === 'Available' ? "darkgreen" : "darkred",
    //                     backgroundColor: params.row.status === 'Available' ? "rgba(0, 100, 0, 0.3)" : "rgba(139, 0, 0, 0.3)"
    //                 }}
    //             >{params.row.status}</div>
    //         )
    //     }
    // }
];

export const ambulanceRows = [
    {
        // id: 0,
        id: "",
        driver_contact_number: "",
        status: ""
    },
    {
        // id: 1,
        id: "AMB001",
        driver_contact_number: "9876543210",
        status: "Available"
    },
    {
        // id: 2,
        id: "AMB002",
        driver_contact_number: "9876543220",
        status: "Not Available"
    },
    {
        // id: 3,
        id: "AMB003",
        driver_contact_number: "9876543230",
        status: "Available"
    },
    {
        // id: 4,
        id: "AMB004",
        driver_contact_number: "9876543240",
        status: "Not Available"
    },
    {
        // id: 5,
        id: "AMB005",
        driver_contact_number: "9876543250",
        status: "Available"
    },
    {
        // id: 6,
        id: "AMB006",
        driver_contact_number: "9876543260",
        status: "Not Available"
    },
    {
        // id: 7,
        id: "AMB007",
        driver_contact_number: "9876543270",
        status: "Available"
    },
    {
        // id: 8,
        id: "AMB008",
        driver_contact_number: "9876543280",
        status: "Not Available"
    },
    {
        // id: 9,
        id: "AMB009",
        driver_contact_number: "9876543290",
        status: "Available"
    },
    {
        // id: 10,
        id: "AMB010",
        driver_contact_number: "9876543300",
        status: "Not Available"
    }
];