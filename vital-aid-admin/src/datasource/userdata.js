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

export const userRows = [];


// async () => {
//     const token = localStorage.getItem('adminToken');
//     const url = 'http://localhost:8080/vital_aid/allUsers';

//     try {
//         const response = await fetch(url, {
//             method: 'GET',
//             headers: {
//                 Authorization: `Bearer ${token}`,
//                 'Content-Type': 'application/json',
//             }
//         });

//         if (!response.ok) {
//             // throw new Error(HTTP error! status: ${response.status});
//             const error = await response.text();
//             console.log(error || 'Failed to fetch users');
//         }
//         else {
//             const data = await response.json();
//             console.log('Success: ', data);
//             // window.location.reload();
//             return { data, error: null };
//         }

//     } catch (err) {
//         console.log('Error fetching profile:', err.message);
//         return { data: null, error: err.message };
//     }
// }; 




//     {
//         id: 1,
//         name: 'Jon Snow',
//         email: 'jon.snow@example.com',
//         date_of_birth: '1988-01-01',
//         image: "https://media.assettype.com/thenewsminute%2Fimport%2Fsites%2Fdefault%2Ffiles%2FJon_Snow_(Image_-_HBO).jpg?w=480&auto=format%2Ccompress&fit=max",
//         contact: '123-456-7890',
//         gender: 'Male',
//         blood_group: 'O+',
//         address: 'Castle Black, The Wall, Westeros'
//     },
//     {
//         id: 2,
//         name: 'Cersei Lannister',
//         email: 'cersei.lannister@example.com',
//         date_of_birth: '1981-05-12',
//         image: 'https://upload.wikimedia.org/wikipedia/en/2/22/Cersei_Lannister_in_Black_Dress_in_Season_5.jpg',
//         contact: '234-567-8901',
//         gender: 'Female',
//         blood_group: 'B+',
//         address: 'Red Keep, King\'s Landing, Westeros'
//     },
//     {
//         id: 3,
//         name: 'Jaime Lannister',
//         email: 'jaime.lannister@example.com',
//         date_of_birth: '1978-03-15',
//         image: 'https://media.wired.com/photos/5933640ba88f414d9a8c7d8f/191:100/w_1280,c_limit/jaime.jpg',
//         contact: '345-678-9012',
//         gender: 'Male',
//         blood_group: 'A+',
//         address: 'Red Keep, King\'s Landing, Westeros'
//     },
//     {
//         id: 4,
//         name: 'Arya Stark',
//         email: 'arya.stark@example.com',
//         date_of_birth: '2006-07-20',
//         image: 'https://pyxis.nymag.com/v1/imgs/846/9bb/440e83edacba3579e42bb6ad20860b50b0-18-arya-stark.rsquare.w400.jpg',
//         contact: '456-789-0123',
//         gender: 'Female',
//         blood_group: 'AB+',
//         address: 'Winterfell, The North, Westeros'
//     },
//     {
//         id: 5,
//         name: 'Daenerys Targaryen',
//         email: 'daenerys.targaryen@example.com',
//         date_of_birth: '1985-11-04',
//         image: 'https://thoughtcatalog.com/wp-content/uploads/2019/05/dany.jpg?w=768&h=768&crop=1',
//         contact: '567-890-1234',
//         gender: 'Female',
//         blood_group: 'O-',
//         address: 'Meereen, Slaver\'s Bay, Essos'
//     },
//     {
//         id: 6,
//         name: 'Melisandre',
//         email: 'melisandre@example.com',
//         date_of_birth: '0874-02-17',
//         image: 'https://cdn.mos.cms.futurecdn.net/9YmHuw75NzaLeZJR4mH5dU-1200-80.jpg',
//         contact: '678-901-2345',
//         gender: 'Female',
//         blood_group: 'B-',
//         address: 'Dragonstone, Narrow Sea, Westeros'
//     },
//     {
//         id: 7,
//         name: 'Oberyn Martell',
//         email: 'oberyn@example.com',
//         date_of_birth: '1979-09-30',
//         image: 'https://upload.wikimedia.org/wikipedia/en/a/ac/Oberyn_Martell-Pedro_Pascal.jpg',
//         contact: '789-012-3456',
//         gender: 'Male',
//         blood_group: 'A-',
//         address: 'Sunspear, Dorne, Westeros'
//     },
//     {
//         id: 8,
//         name: 'Jaqen H\'ghar',
//         email: 'jaqen@example.com',
//         date_of_birth: '1987-03-22',
//         image: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiscMkgsSYL4pP175QZrlpY59fS99jCvilCA&s',
//         contact: '890-123-4567',
//         gender: 'Male',
//         blood_group: 'AB-',
//         address: 'Braavos, Free Cities, Essos'
//     },
//     {
//         id: 9,
//         name: 'Robin Arryn',
//         email: 'robin@example.com',
//         date_of_birth: '1959-12-14',
//         image: 'https://img.buzzfeed.com/buzzfeed-static/static/2019-05/21/19/asset/buzzfeed-prod-web-05/sub-buzz-13944-1558482146-1.jpg',
//         contact: '901-234-5678',
//         gender: 'Male',
//         blood_group: 'O+',
//         address: 'The Eyrie, The Vale, Westeros'
//     }
// ];
