package com.vital_aid_crud_api.service.ImplementationClasses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vital_aid_crud_api.Config.SecurityConstants;
import com.vital_aid_crud_api.Entity.Admin;
import com.vital_aid_crud_api.Entity.Ambulance;
import com.vital_aid_crud_api.Entity.Doctor;
import com.vital_aid_crud_api.Entity.Hospital;
import com.vital_aid_crud_api.Entity.Product;
import com.vital_aid_crud_api.Exception.IllegalPasswordException;
import com.vital_aid_crud_api.Exception.OtpExpirationException;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.AdminDTO;
import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.OtpDetails;
import com.vital_aid_crud_api.Payloads.RegisterDTO;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.AmbulanceRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.HospitalRepository;
import com.vital_aid_crud_api.repository.ProductRepository;
import com.vital_aid_crud_api.service.Interfaces.AdminService;
import com.vital_aid_crud_api.service.MailService.MailService;
import com.vital_aid_crud_api.service.OTPService.OTPService;
import com.vital_aid_crud_api.service.UniqueIdentityService.PersonValidationService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PersonValidationService personValidationService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private MailService mailService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @Autowired
    private ProductRepository medicalStoreRepository;

    @Override
    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminDTO> adminDTOs = admins.stream().map(admin -> AdminToDto(admin)).collect(Collectors.toList());

        return adminDTOs;
    }

                                                    // REGISTERING AN ADMIN

    @Transactional
    @Override
    public RegisterDTO registerAdmin(RegisterDTO adminDTO) {
        personValidationService.validateUniqueFields(
                adminDTO.getPersonEmail(),
                adminDTO.getPersonPhone());
        if (!adminDTO.getLoginPassword().equals(adminDTO.getConfirmPassword())) {
            throw new IllegalPasswordException("Passwords do not match");
        }

        adminDTO.setLoginPassword(bCryptPasswordEncoder.encode(adminDTO.getLoginPassword()));
        Admin admin = adminRepository.save(resgisterDTOtoAdmin(adminDTO));
        return AdminEntityToDto(admin);
    }


                                               // DELETING AN ADMIN
        
    @Transactional
    @Override
    public void deleteAdmin(String personEmail) {
        Admin admin = adminRepository.findByPersonEmail(personEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", personEmail));
        
        for(Doctor doctor: admin.getDoctors()) {
            doctor.setDoctorManagedBy(null);
            doctorRepository.save(doctor);
        }
        for(Hospital hospital: admin.getHospitals()) {
            hospital.setHospitalManagedBy(null);
            hospitalRepository.save(hospital);
        }

        for(Ambulance ambulance: admin.getAmbulances()) {
            ambulance.setAmbulanceManagedBy(null);
            ambulanceRepository.save(ambulance);
        }

        for(Product store: admin.getMedicalStoreProducts()) {
            store.setStoreManagedBy(null);
            medicalStoreRepository.save(store);
        }
        
        adminRepository.delete(admin);
    }

                                            //GETTING DETAILS OF AN ADMIN BY EMAIL
    
    @Transactional
    @Override
    public AdminDTO getAdminByEmail(String personEmail) {
        Admin admin = adminRepository.findByPersonEmail(personEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "Email", personEmail));
        return AdminToDto(admin);
    }

    // Update admin profile
    @Transactional
    @Override
    public AdminDTO updateAdminProfile(String personEmail, AdminDTO adminDTO) {
        Admin existingAdmin = adminRepository.findByPersonEmail(personEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", personEmail));

        // Validate uniqueness of fields
        personValidationService.validateUniqueFieldsForUpdate(
                adminDTO.getPersonEmail(),
                adminDTO.getPersonPhone(),
                existingAdmin.getPersonId());

        // Update user details
        existingAdmin.setPersonName(adminDTO.getPersonName());
        existingAdmin.setPersonEmail(adminDTO.getPersonEmail());
        existingAdmin.setPersonPhone(adminDTO.getPersonPhone());

        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return AdminToDto(updatedAdmin);
    }


                                                // CHANGING PASSWORD OF AN ADMIN

    @Transactional
    @Override

    public void changeAdminPassword(String adminEmail, ChangePasswordDTO changePasswordDTO) {

        Admin existingAdmin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        if(!bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(), existingAdmin.getLoginPassword())) {
            throw new IllegalPasswordException("Old password is incorrect");
        }
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new IllegalPasswordException("Passwords do not match");
        }
        existingAdmin.setLoginPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));

        adminRepository.save(existingAdmin);
    }


                                         // SENDING VERIFICATION CODE TO EMAIL
    
    @Transactional
    @Override
    public void sendVerificationCode(String email){
        Admin admin = adminRepository.findByPersonEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", email));

        String otp = otpService.generateOtp(email, SecurityConstants.OTP_EXPIRY_TIME_MINUTES);

        String subject = "Vital Aid - Verification Code";
        String body = String.format(
                "Dear %s,\n\nYour verification code is: %s\n\nThis code is valid for %d minutes.\n\nThank you,\nVital Aid Team",
                admin.getPersonName(), otp, SecurityConstants.OTP_EXPIRY_TIME_MINUTES);
        mailService.sendOtpEmail(email, subject, body);
    }


                                                // VERIFYING OTP

    String validOTP;
    @Transactional
    @Override
    public void validateOtp(String otp){
        OtpDetails details = otpService.validateOtp(otp);
        if (details.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new OtpExpirationException("Expired OTP. Resend OTP.");
        }
        validOTP = otp;// Store the validated OTP
    }

    @Transactional
    @Override
    public void resetPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        // Get the email associated with the validated OTP
        String email = otpService.getEmailForOtp(validOTP);

        // Fetch user and update password
        Admin admin = adminRepository.findByPersonEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        admin.setLoginPassword(bCryptPasswordEncoder.encode(newPassword));
        adminRepository.save(admin);

        // Clear the OTP after successful reset
        otpService.clearOtp(email);
    }


                                                        // HELPER METHODS

                                                    // ADMIN ENTITY TO DTO
    public AdminDTO AdminToDto(Admin admin) {
        return modelMapper.map(admin, AdminDTO.class);
    }

                                                    // DTO TO ADMIN ENTITY
    public Admin DTOtoAdmin(AdminDTO adminDTO) {
        return modelMapper.map(adminDTO, Admin.class);
    }

                                                // ADMIN ENTITY TO REGISTER DTO
    public RegisterDTO AdminEntityToDto(Admin admin) {
        return modelMapper.map(admin, RegisterDTO.class);
    }

                                                // REGISTER DTO TO ADMIN ENTITY
    public Admin resgisterDTOtoAdmin(RegisterDTO adminDTO) {
        return modelMapper.map(adminDTO, Admin.class);
    }

}
