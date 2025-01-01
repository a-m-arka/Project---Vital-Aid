package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.Payloads.AdminDTO;
import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.RegisterDTO;

public interface AdminService {

    // get all admins
    List<AdminDTO> getAllAdmins();

    // register admin
    RegisterDTO registerAdmin(RegisterDTO adminDTO);

    // update admin by id
    // AdminDTO updateAdminbyID(AdminDTO adminDTO, Long person_id);

   

    // delete admin by id
    void deleteAdmin(String personEmail);

    // get admin by email
    AdminDTO getAdminByEmail(String personEmail);

    // update admin profile
    AdminDTO updateAdminProfile(String personEmail, AdminDTO adminDTO);

    // change admin password
    void changeAdminPassword(String adminEmail, ChangePasswordDTO changePasswordDTO);

    // Forgot Password: Send verification code
    void sendVerificationCode(String email);

    // Forgot Password: Validate OTP
    void validateOtp(String otp);

    // Forgot Password: Reset password
    void resetPassword(String newPassword, String confirmPassword);
}
