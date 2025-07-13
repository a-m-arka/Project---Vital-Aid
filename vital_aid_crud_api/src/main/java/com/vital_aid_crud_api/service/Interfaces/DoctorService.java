package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.DoctorDTO;

public interface DoctorService {

    // List all doctors
    List<DoctorDTO> getAllDoctors();

    // Register a new doctor
    DoctorDTO registerDoctor(DoctorDTO doctorDTO);

    // Update doctor profile details
    DoctorDTO updateDoctorProfileDetails(String doctorEmail, DoctorDTO doctorDTO);

    // View doctor profile by id
    DoctorDTO getDoctorbyId(Long doctorId);

    // View doctor profile by email
    DoctorDTO getDoctorbyEmail(String personEmail);

    // change password
    void changeDoctorPassword(String userEmail, ChangePasswordDTO changePasswordDTO);

    // Forgot Password: Send verification code
    void sendVerificationCode(String personEmail);

    // Forgot Password: Validate OTP
    void validateOtp(String otp);

    // Forgot Password: Reset password
    void resetPassword(String newPassword, String confirmPassword);

    // Update doctor profile image
    void updateLoggedInDoctorImage(MultipartFile file);

    // Update availability status
    void updateAvailabilityStatus(String availabilityStatus);

    // Get Doctor Availability Status
    DoctorDTO getDoctorAvailabilityStatus(String doctorEmail);

    // Delete doctor account
    void deleteDoctor(String doctorEmail);
}




    // DoctorDTO registerDoctorWithImage(DoctorDTO doctorDTO, MultipartFile file);
    // DoctorDTO updateDoctorWithDetailsAndImage(Long doctorId, DoctorDTO doctorDTO, MultipartFile file);
    
    // DoctorDTO updateDoctorImage(Long doctorId, MultipartFile file);