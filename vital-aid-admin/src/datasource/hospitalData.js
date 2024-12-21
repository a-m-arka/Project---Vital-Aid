export const hospitalColumns = [
    { field: 'id', headerName: 'ID', width: 70 },
    {
        field: 'hospital', headerName: 'Hospital', width: 400,
        renderCell: (params) => {
            return (
                <div className="cellWithImg">
                    <img src={params.row.image} alt="" className="cellImg" />
                    {params.row.name}
                </div>
            )
        }
    }
];

export const hospitalRows = [
    {
        id: 0,
        name: "",
        address: "",
        phone: "",
        email: "",
        location: "",
        total_doctors: "",
        total_beds: "",
        icu_beds: "",
        mri_machines: "",
        xray_machines: "",
        ventilators: "",
        image: ""
    },
    {
        id: 1,
        name: "Evercare Hospital",
        address: "5/1, Block A, Lalmatia, Satmosjid Road, Dhaka 1207",
        phone: "+880 2-55037242",
        email: "evercarehospital@gmail.com",
        location: "Dhaka",
        total_doctors: 45,
        total_beds: 500,
        icu_beds: 50,
        mri_machines: 10,
        xray_machines: 12,
        ventilators: 50,
        image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQ3Cz9g5o_wA665F182Vk8EQCXV0drr52WUw&s"
    },
    {
        id: 2,
        name: "Square Hospital",
        address: "18/F, Bir Uttam Qazi Nuruzzaman Sarak, Dhaka 1205",
        phone: "+880 2-8144400",
        email: "squarehospital@gmail.com",
        location: "Dhaka",
        total_doctors: 40,
        total_beds: 400,
        icu_beds: 60,
        mri_machines: 8,
        xray_machines: 10,
        ventilators: 40,
        image: "https://www.tritechbd.com/wp-content/uploads/2018/12/square2.jpg"
    },
    {
        id: 3,
        name: "Apollo Hospital Chattogram",
        address: "9th Floor, A.K. Khan Tower, Agrabad, Chattogram",
        phone: "+880 31-2513782",
        email: "apolloctg@gmail.com",
        location: "Chattogram",
        total_doctors: 35,
        total_beds: 300,
        icu_beds: 40,
        mri_machines: 5,
        xray_machines: 8,
        ventilators: 25,
        image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4xp6xdk2ImSvcogvW0NNeiZkR2tsg0bki_w&s"
    },
    {
        id: 4,
        name: "Sylhet MAG Osmani Medical College Hospital",
        address: "Medical College Road, Sylhet",
        phone: "+880 821-713487",
        email: "osmanimedical@gmail.com",
        location: "Sylhet",
        total_doctors: 50,
        total_beds: 600,
        icu_beds: 75,
        mri_machines: 12,
        xray_machines: 15,
        ventilators: 70,
        image: "https://magosmanimedical.com/wp-content/uploads/2014/12/somc12.jpg"
    },
    {
        id: 5,
        name: "Khulna Medical College Hospital",
        address: "Khulna Medical College Rd, Khulna",
        phone: "+880 41-760540",
        email: "kmchospital@gmail.com",
        location: "Khulna",
        total_doctors: 32,
        total_beds: 500,
        icu_beds: 50,
        mri_machines: 6,
        xray_machines: 10,
        ventilators: 40,
        image: "https://upload.wikimedia.org/wikipedia/commons/0/01/Khulna_medical_college_hospital_building.jpg"
    },
    {
        id: 6,
        name: "Mymensingh Medical College Hospital",
        address: "Charpara, Mymensingh",
        phone: "+880 91-66350",
        email: "mmchospital@gmail.com",
        location: "Mymensingh",
        total_doctors: 42,
        total_beds: 700,
        icu_beds: 60,
        mri_machines: 8,
        xray_machines: 14,
        ventilators: 50,
        image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlxE2yznBm1VouInl8fl6Iq-gpr7k2p5hAcg&s"
    },
    {
        id: 7,
        name: "Rajshahi Medical College Hospital",
        address: "Sadar Hospital Road, Rajshahi",
        phone: "+880 721-772150",
        email: "rmchospital@gmail.com",
        location: "Rajshahi",
        total_doctors: 38,
        total_beds: 750,
        icu_beds: 70,
        mri_machines: 10,
        xray_machines: 15,
        ventilators: 60,
        image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEy9ws71m11Hagwz_kELKTd-MGlPIdKkygPA&s"
    },
    {
        id: 8,
        name: "Barisal Sher-E-Bangla Medical College Hospital",
        address: "Hospital Rd, Barisal",
        phone: "+880 431-2172426",
        email: "sherbanglahospital@gmail.com",
        location: "Barisal",
        total_doctors: 36,
        total_beds: 500,
        icu_beds: 50,
        mri_machines: 6,
        xray_machines: 10,
        ventilators: 35,
        image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMAsaSyZuG7nXGq7Cy4ziO9UoJIRGS7SRlig&s"
    },
    {
        id: 9,
        name: "Rangpur Medical College Hospital",
        address: "Medical East Gate, Rangpur",
        phone: "+880 521-62351",
        email: "rmchospital@gmail.com",
        location: "Rangpur",
        total_doctors: 47,
        total_beds: 600,
        icu_beds: 60,
        mri_machines: 7,
        xray_machines: 12,
        ventilators: 50,
        image: "https://rpmc.edu.bd/wp-content/uploads/2023/08/RPMCH-300x225-1.jpg"
    },
    {
        id: 10,
        name: "BIRDEM General Hospital",
        address: "122 Kazi Nazrul Islam Ave, Dhaka 1000",
        phone: "+880 2-9661551",
        email: "birdemhospital@gmail.com",
        location: "Dhaka",
        total_doctors: 43,
        total_beds: 400,
        icu_beds: 60,
        mri_machines: 10,
        xray_machines: 12,
        ventilators: 40,
        image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCqTPtS1A-i7_bsSnOW54JwOQwpuKqFSWzlw&s"
    }
];
