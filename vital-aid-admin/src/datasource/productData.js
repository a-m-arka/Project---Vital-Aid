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

export const productRows = [
    {
        id: 0,
        product_name: "",
        product_price: "",
        product_quantity: "",
        product_category: "",
        image: ""
    },
    {
        id: "MED001",
        product_name: "Paracetamol",
        product_price: 599,
        product_quantity: 100,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "MED002",
        product_name: "Ibuprofen",
        product_price: 850,
        product_quantity: 150,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP001",
        product_name: "Stethoscope",
        product_price: 2500,
        product_quantity: 50,
        product_category: "Medical Equipment",
        image: "/Images/Stethoscope.jpg"
    },
    {
        id: "EQP002",
        product_name: "Thermometer",
        product_price: 1275,
        product_quantity: 200,
        product_category: "Medical Equipment",
        image: "/Images/Thermometer.jpg"
    },
    {
        id: "EQP003",
        product_name: "Blood Pressure Monitor",
        product_price: 4599,
        product_quantity: 30,
        product_category: "Medical Equipment",
        image: "/Images/Blood Pressure Monitor.jpg"
    },
    {
        id: "MED003",
        product_name: "Amoxicillin",
        product_price: 1500,
        product_quantity: 75,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP004",
        product_name: "Oxygen Cylinder",
        product_price: 9999,
        product_quantity: 20,
        product_category: "Medical Equipment",
        image: "/Images/Oxygen Cylinder.jpg"
    },
    {
        id: "MED004",
        product_name: "Aspirin",
        product_price: 499,
        product_quantity: 200,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP005",
        product_name: "Glucometer",
        product_price: 2950,
        product_quantity: 40,
        product_category: "Medical Equipment",
        image: "/Images/Glucometer.jpg"
    },
    {
        id: "MED005",
        product_name: "Cough Syrup",
        product_price: 625,
        product_quantity: 120,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP006",
        product_name: "Surgical Mask",
        product_price: 50,
        product_quantity: 1000,
        product_category: "Medical Equipment",
        image: "/Images/Surgical Mask.jpg"
    },
    {
        id: "MED006",
        product_name: "Antiseptic Cream",
        product_price: 350,
        product_quantity: 80,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP007",
        product_name: "Syringe",
        product_price: 125,
        product_quantity: 500,
        product_category: "Medical Equipment",
        image: "/Images/Syringe.jpg"
    },
    {
        id: "MED007",
        product_name: "Antibiotic Ointment",
        product_price: 775,
        product_quantity: 60,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP008",
        product_name: "Nebulizer",
        product_price: 3500,
        product_quantity: 25,
        product_category: "Medical Equipment",
        image: "/Images/Nebulizer.jpg"
    },
    {
        id: "MED008",
        product_name: "Hydrocortisone Cream",
        product_price: 925,
        product_quantity: 40,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP009",
        product_name: "Pulse Oximeter",
        product_price: 2200,
        product_quantity: 70,
        product_category: "Medical Equipment",
        image: "/Images/Pulse Oximeter.jpg"
    },
    {
        id: "MED009",
        product_name: "Vitamin C Tablets",
        product_price: 1200,
        product_quantity: 150,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP010",
        product_name: "Defibrillator",
        product_price: 30000,
        product_quantity: 5,
        product_category: "Medical Equipment",
        image: "/Images/Defibrillator.jpg"
    },
    {
        id: "MED010",
        product_name: "Antifungal Cream",
        product_price: 1050,
        product_quantity: 55,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP011",
        product_name: "Sphygmomanometer",
        product_price: 4999,
        product_quantity: 20,
        product_category: "Medical Equipment",
        image: "/Images/Sphygmomanometer.jpg"
    },
    {
        id: "MED011",
        product_name: "Multivitamins",
        product_price: 1400,
        product_quantity: 200,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP012",
        product_name: "Wheelchair",
        product_price: 15000,
        product_quantity: 10,
        product_category: "Medical Equipment",
        image: "/Images/Wheelchair.jpg"
    },
    {
        id: "MED012",
        product_name: "Antacid Tablets",
        product_price: 575,
        product_quantity: 180,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    },
    {
        id: "EQP013",
        product_name: "Crutches",
        product_price: 3000,
        product_quantity: 15,
        product_category: "Medical Equipment",
        image: "/Images/Crutches.jpg"
    },
    {
        id: "MED013",
        product_name: "Laxatives",
        product_price: 700,
        product_quantity: 100,
        product_category: "Medicine",
        image: "/Images/medicine.jpg"
    }
];
