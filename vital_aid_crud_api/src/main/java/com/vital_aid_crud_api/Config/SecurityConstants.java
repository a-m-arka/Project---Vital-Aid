package com.vital_aid_crud_api.Config;

public class SecurityConstants {

    // User and Admin Registration Paths
    public static final String USER_REGISTER_PATH = "/vital_aid/user/register"; // Accessible for all
    public static final String ADMIN_REGISTER_PATH = "/vital_aid/admin/register"; // Accessible for all



    // User and Admin login paths
    public static final String USER_LOGIN_PATH = "/vital_aid/user/login"; // Accessible for all
    public static final String ADMIN_LOGIN_PATH = "/vital_aid/admin/login"; // Accessible for all



    // All user and admin List paths
    public static final String ALL_ADMIN_GET_PATH = "/vital_aid/admin/allAdmins"; // List of all Admins Path (Accessible only to Admin)                                                                                   
    public static final String ALL_USER_GET_PATH = "/vital_aid/allUsers"; // List of all Users Path (Accessible only to Admin) 



    // Profile details of a single user by passing ID as path variable
    public static final String SINGLE_USER_VIEW_PATH = "/vital_aid/viewUser/*"; // Accessible only to Admin



    // User and Admin profile details paths
    public static final String USER_PROFILE_DETAILS = "/vital_aid/profile"; // User Profile Details Path (Accessible to User) 
    public static final String ADMIN_PROFILE_DETAILS = "/vital_aid/admin/profile"; // Admin Profile Details Path (Accessible to Admin)



    // User and Admin profile details update paths
    public static final String USER_PROFILE_UPDATE = "/vital_aid/updateUserProfile"; // User Profile Update Path (Accessible to User)
    public static final String ADMIN_PROFILE_UPDATE = "/vital_aid/admin/updateAdminProfile"; // Admin Profile Update Path (Accessible to Admin)



    // User and Admin Account delete paths
    public static final String USER_DELETE_PATH = "/vital_aid/deleteUser"; // User Account delete path (Accessible to User)
    public static final String ADMIN_DELETE_PATH = "/vital_aid/admin/deleteAdmin"; // Admin Account delete path (Accessible to Admin)



    // User and Admin password change paths
    public static final String USER_CHANGE_PASSWORD = "/vital_aid/changePassword"; // User Password Change Path (Accessible to User)
    public static final String ADMIN_CHANGE_PASSWORD = "/vital_aid/admin/changePassword"; // Admin Password Change Path (Accessible to Admin)



    // Details related to JWT token
    public static final int EXPIRATION_TIME = 7200000; // 2 hours
    public static final String SECRET_KEY = "i42KhHsybMggrlbL7jiA4Wfq6HylHOAVifijYBZR2tvST5O0i/aZTYbGGaHYDMi7q+Ng/V+lTYvnm41alFB4uA=="; // Secret Key
    public static final String HEADER_STRING = "Authorization"; // Header String
    public static final String TOKEN_PREFIX = "Bearer "; // Token Prefix



    // OTP expiry time in minutes
    public static final long OTP_EXPIRY_TIME_MINUTES = 2; // 2 minutes



    // User forget password paths
    public static final String USER_FORGET_PASSWORD_SEND_CODE_PATH = "/vital_aid/forgotPassword/sendCode"; // User forget password send code path (Accessible to all)
                                                                                                            
    public static final String USER_VALIDATE_OTP_PATH = "/vital_aid/forgotPassword/validateOtp"; // User forget password validate OTP path (Accessible to all)

    public static final String USER_RESET_PASSWORD_PATH = "/vital_aid/forgotPassword/resetPassword"; // User reset password path (Accessible to all)



    // Admin forget password paths
    public static final String ADMIN_FORGET_PASSWORD_SEND_CODE_PATH = "/vital_aid/forgotPassword/admin/sendCode"; // Admin forget password send code path (Accessible to all)

    public static final String ADMIN_VALIDATE_OTP_PATH = "/vital_aid/forgotPassword/admin/validateOtp"; // Admin forget password validate OTP path (Accessible to all)

    public static final String ADMIN_RESET_PASSWORD_PATH = "/vital_aid/forgotPassword/admin/resetPassword"; // Admin reset password path (Accessible to all)



    // User profile photo upload/update path
    public static final String USER_PROFILE_PHOTO_UPLOAD_PATH = "/vital_aid/uploadImage/userProfilePhoto"; //(Accessible to User)



    // Doctor paths

    public static final String ALL_DOCTOR_GET_PATH = "/vital_aid/doctors/allDoctors"; // Getting All Doctors List Path
    public static final String DOCTOR_REGISTER_PATH = "/vital_aid/doctors/registerDoctor"; // Registering a new Doctor
                                                                                           // Path
    public static final String SINGLE_DOCTOR_VIEW_PATH = "/vital_aid/doctors/viewDoctor/*"; // Getting a Doctor by ID
                                                                                            // Path
    public static final String DOCTOR_UPDATE_PATH = "/vital_aid/doctors/updateDoctor/*"; // Updating a Doctor by ID Path
    public static final String DOCTOR_DELETE_PATH = "/vital_aid/doctors/deleteDoctor/*"; // Deleting a Doctor by ID Path



    // Hospital paths

    public static final String HOSPITAL_REGISTER_PATH = "/vital_aid/hospitals/registerHospital"; // Registering a new a Hospital Path (Accessible to Admin)

    public static final String ALL_HOSPITAL_GET_PATH = "/vital_aid/hospitals/allHospitals"; // Getting All Hospital List Path(Accessible to all)

    public static final String SINGLE_HOSPITAL_VIEW_PATH = "/vital_aid/hospitals/viewHospital/*"; // Viewing a Hospital's details by ID Path (Accessible to all)

    public static final String HOSPITAL_UPDATE_PATH = "/vital_aid/hospitals/updateHospital/*"; // Updating a Hospital's details by ID Path (Accessible to Admin)

    public static final String HOSPITAL_DELETE_PATH = "/vital_aid/hospitals/deleteHospital/*"; // Deleting a Hospital by ID Path (Accessible to Admin)

    public static final String ALL_DOCTORS_OF_A_HOSPITAL = "/vital_aid/hospitals/allDoctors/*"; // Getting all Doctors of a Hospital Path (Accessible to all)



    // APPOINTMENT PATHS

    public static final String ALL_APPOINTMENTS_LIST_PATH = "/vital_aid/appointment/allAppointments"; // Getting all Appointments List Path (Accessible to Admin)

    public static final String VIEW_APPOINTMENT_BY_TOKEN_PATH = "/vital_aid/appointment/viewAppointment/*"; // Viewing an Appointment details by Token Path (Accessible to User and Admin)

    public static final String VIEW_USER_ALL_APPOINTMENTS_PATH = "/vital_aid/appointment/userAppointments"; // Viewing all Appointments of a User Path (Accessible to User)

    public static final String VIEW_DOCTOR_ALL_APPOINTMENTS_PATH = "/vital_aid/appointment/doctorAppointments/*"; // Viewing all Appointments of a Doctor Path (Accessible to Admin)

    public static final String MAKE_APPOINTMENT_PATH = "/vital_aid/appointment/makeAppointment/*"; // Making an Appointment Path (Accessible to User)



    // AMBULANCE PATHS

    public static final String ALL_AMBULANCES_LIST_PATH = "/vital_aid/ambulance/allAmbulances"; // Getting all Ambulances List Path (Accessible to all)

    public static final String VIEW_AMBULANCE_BY_NUM_PLATE_PATH = "/vital_aid/ambulance/viewAmbulanceDetailsByNumPlate/*"; // Viewing an Ambulance details by Num Plate Path (Accessible to all)

    public static final String REGISTER_AMBULANCE_PATH = "/vital_aid/ambulance/registerAmbulance"; // Registering a new Ambulance Path (Accessible to Admin)

    public static final String UPDATE_AMBULANCE_PATH = "/vital_aid/ambulance/updateAmbulanceDetails/*"; // Updating an Ambulance by ID Path (Accessible to Admin)

    public static final String DELETE_AMBULANCE_PATH = "/vital_aid/ambulance/deleteAmbulanceByNumPlate/*"; // Deleting an Ambulance by Num Plate Path (Accessible to Admin)



    // MEDICAL STORE PATHS

    public static final String ALL_PRODUCTS_LIST_PATH = "/vital_aid/medical_store/allProducts"; // Getting all Products List Path (Accessible to all)

    public static final String ADD_PRODUCT_PATH = "/vital_aid/medical_store/addProduct"; // Adding a new Product Path (Accessible to Admin)

    public static final String UPDATE_PRODUCT_PATH = "/vital_aid/medical_store/updateProduct/*"; // Updating a Product by ID Path (Accessible to Admin)

    public static final String DELETE_PRODUCT_PATH = "/vital_aid/medical_store/deleteProduct/*"; // Deleting a Product by ID Path (Accessible to Admin)



    // ORDER PATHS

    public static final String ALL_ORDERS_LIST_PATH = "/vital_aid/orderProduct/allOrders"; // Getting all Orders List Path (Accessible to Admin)

    public static final String VIEW_ORDER_BY_ID_PATH = "/vital_aid/orderProduct/orderDetails/*"; // Viewing an Order details by ID Path (Accessible to User and Admin)

    public static final String VIEW_USER_ALL_ORDERS_PATH = "/vital_aid/orderProduct/userOrders"; // Viewing all Orders of a User Path (Accessible to User)

    public static final String MAKE_ORDER_PATH = "/vital_aid/orderProduct/makeOrder/*"; // Making an Order Path (Accessible to User)

    public static final String DELETE_ORDER_PATH = "/vital_aid/orderProduct/deleteOrder/*"; // Deleting an Order by ID Path (Accessible to User)


    
    // CALL AMBULANCE PATHS

    public static final String ALL_CALLS_LIST_PATH = "/vital_aid/call_ambulance/allCalls"; // Getting all Calls List Path (Accessible to Admin)

    public static final String VIEW_CALL_BY_ID_PATH = "/vital_aid/call_ambulance/callDetails/*/*"; // Viewing a Call details by ID Path (Accessible to User and Admin)

    public static final String MAKE_CALL_PATH = "/vital_aid/call_ambulance/makeCall/*"; // Making a Call Path (Accessible to User)

}
