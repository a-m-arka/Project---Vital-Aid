export const ambulanceColumns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'number_plate', headerName: 'Number Plate', width: 150 },
    {
        field: 'status', headerName: 'Status', width: 200,
        renderCell: (params) => {
            return (
                <div className="cellWithStatus"
                    style={{
                        color: params.row.status === 'Available' ? "darkgreen" : "darkred",
                        backgroundColor: params.row.status === 'Available' ? "rgba(0, 100, 0, 0.3)" : "rgba(139, 0, 0, 0.3)"
                    }}
                >{params.row.status}</div>
            )
        }
    }
];

export const ambulanceRows = [
    {
        id: 0,
        number_plate: "",
        driver_contact_number: "",
        status: ""
    },
    {
        id: 1,
        number_plate: "AMB001",
        driver_contact_number: "9876543210",
        status: "Available"
    },
    {
        id: 2,
        number_plate: "AMB002",
        driver_contact_number: "9876543220",
        status: "Not Available"
    },
    {
        id: 3,
        number_plate: "AMB003",
        driver_contact_number: "9876543230",
        status: "Available"
    },
    {
        id: 4,
        number_plate: "AMB004",
        driver_contact_number: "9876543240",
        status: "Not Available"
    },
    {
        id: 5,
        number_plate: "AMB005",
        driver_contact_number: "9876543250",
        status: "Available"
    },
    {
        id: 6,
        number_plate: "AMB006",
        driver_contact_number: "9876543260",
        status: "Not Available"
    },
    {
        id: 7,
        number_plate: "AMB007",
        driver_contact_number: "9876543270",
        status: "Available"
    },
    {
        id: 8,
        number_plate: "AMB008",
        driver_contact_number: "9876543280",
        status: "Not Available"
    },
    {
        id: 9,
        number_plate: "AMB009",
        driver_contact_number: "9876543290",
        status: "Available"
    },
    {
        id: 10,
        number_plate: "AMB010",
        driver_contact_number: "9876543300",
        status: "Not Available"
    }
];