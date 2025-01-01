package com.vital_aid_crud_api.service.ImplementationClasses;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Config.SecurityConstants;
import com.vital_aid_crud_api.Entity.Appointment;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.IllegalPasswordException;
import com.vital_aid_crud_api.Exception.OtpExpirationException;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.OtpDetails;
import com.vital_aid_crud_api.Payloads.RegisterDTO;
import com.vital_aid_crud_api.Payloads.UserDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.repository.AppointmentRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.CloudinaryImageUploadService;
import com.vital_aid_crud_api.service.Interfaces.UserService;
import com.vital_aid_crud_api.service.MailService.MailService;
import com.vital_aid_crud_api.service.OTPService.OTPService;
import com.vital_aid_crud_api.service.UniqueIdentityService.PersonValidationService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
    private CloudinaryImageUploadService cloudinaryImageUploadService;

                                                    // LIST OF ALL USERS

    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> UsertoDTO(user)).collect(Collectors.toList());

        return userDTOs;
    }

                                                  // VIEW A SINGLE USER DETAILS BY ID

    @Transactional
    @Override
    public UserDTO viewUserDetailsbyId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return UsertoDTO(user);
    }

                                                // VIEW A SINGLE USER DETAILS BY EMAIL  

    @Transactional
    @Override
    public UserDTO getUserByEmail(String personEmail) {
        User user = userRepository.findByPersonEmail(personEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", personEmail));
        return UsertoDTO(user);
    }

                                                // REGISTERING A NEW USER   

    @Transactional
    @Override
    public RegisterDTO registerUser(RegisterDTO userDTO) {
        personValidationService.validateUniqueFields(
                userDTO.getPersonEmail(),
                userDTO.getPersonPhone());
        if (!userDTO.getLoginPassword().equals(userDTO.getConfirmPassword())) {
            throw new IllegalPasswordException("Passwords do not match");
        }
        
        
        userDTO.setLoginPassword(bCryptPasswordEncoder.encode(userDTO.getLoginPassword()));
        User user = userRepository.save(registerDTOtoUser(userDTO));
        return UserEntitytoDTO(user);
    }

                                                // UPDATING USER PROFILE    

    @Transactional
    @Override
    public UserDTO updateUserProfile(String userEmail, UserDTO userDTO) {
        User existingUser = userRepository.findByPersonEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        // Validate uniqueness of fields
        personValidationService.validateUniqueFieldsForUpdate(
                userDTO.getPersonEmail(),
                userDTO.getPersonPhone(),
                existingUser.getPersonId());

        // Update user details
        existingUser.setPersonName(userDTO.getPersonName());
        existingUser.setPersonEmail(userDTO.getPersonEmail());
        existingUser.setPersonPhone(userDTO.getPersonPhone());
        existingUser.setUserGender(userDTO.getUserGender());
        existingUser.setUserAddress(userDTO.getUserAddress());
        existingUser.setUserBloodGroup(userDTO.getUserBloodGroup());
        existingUser.setUserDob(userDTO.getUserDob());

        User updatedUser = userRepository.save(existingUser);
        return UsertoDTO(updatedUser);
    }


                                                // DELETING USER ACCOUNT

    @Transactional
    @Override
    public void deleteUser(String personEmail) {
        User user = userRepository.findByPersonEmail(personEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", personEmail));
        String profileImageUrl = user.getProfileImageUrl();
        if(!profileImageUrl.equals(BaseImageUrls.USER_PROFILE_PHOTO_BASE_URL)){
            String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(profileImageUrl);
            if (publicId != null) {
                    try {
                        cloudinaryImageUploadService.deleteImageFromCloud(publicId); // Attempt to delete the image
                    } catch (Exception e) {
                        ApiResponse response = new ApiResponse("Failed to delete image", false);
                        throw new IllegalStateException(response.getMessage());
                    }
                }
            }
            
        for(Appointment appointment : user.getMadeAppointments()){
            appointment.setMadeByUser(null);
            appointmentRepository.save(appointment);
        }
        
        userRepository.delete(user);
    }

                                                // CHANGING USER PASSWORD  

    @Transactional
    @Override
    public void changePassword(String userEmail, ChangePasswordDTO changePasswordDTO) {

        User existingUser = userRepository.findByPersonEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        if (!bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(), existingUser.getLoginPassword())) {
            throw new IllegalPasswordException("Old password is incorrect");
        }
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new IllegalPasswordException("Passwords do not match");
        }
        existingUser.setLoginPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));

        userRepository.save(existingUser);
    }

                                    // SENDING VERIFICATION CODE FOR PASSWORD RESET

    @Transactional
    @Override
    public void sendVerificationCode(String email) {
        User user = userRepository.findByPersonEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        String otp = otpService.generateOtp(email, (SecurityConstants.OTP_EXPIRY_TIME_MINUTES) + 1);

        String subject = "Vital Aid - Verification Code";
        String body = String.format(
                "Dear %s,\n\nYour verification code is: %s\n\nThis code is valid for %d minutes.\n\nThank you,\nVital Aid Team",
                user.getPersonName(), otp, SecurityConstants.OTP_EXPIRY_TIME_MINUTES);
        mailService.sendOtpEmail(email, subject, body);
    }

    
    String validOTP; // Store the validated OTP

                                        // VERIFYING THE OTP FOR PASSWORD RESET

    @Transactional
    @Override
    public void validateOtp(String otp) {
        OtpDetails details = otpService.validateOtp(otp);

        if (details.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new OtpExpirationException("Expired OTP. Resend OTP.");
        }
        validOTP = otp;
    }

                                        // RESET PASSWORD AFTER VERIFYING THE OTP

    @Transactional
    @Override
    public void resetPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        // Get the email associated with the validated OTP
        String email = otpService.getEmailForOtp(validOTP);

        // Fetch user and update password
        User user = userRepository.findByPersonEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        user.setLoginPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);

        // Clear the OTP after successful reset
        otpService.clearOtp(email);
    }

                                            // UPDATING USER PROFILE IMAGE          

    @Transactional
    @Override
    public void updateLoggedInUserProfileImage(MultipartFile file){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = userRepository.findByPersonEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        String profileImageCurrentUrl = existingUser.getProfileImageUrl();
        if (!profileImageCurrentUrl.equals(BaseImageUrls.USER_PROFILE_PHOTO_BASE_URL)) {
                String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(profileImageCurrentUrl);
                
                    if (publicId != null) {
                            try {
                                cloudinaryImageUploadService.deleteImageFromCloud(publicId); // Attempt to delete the image
                            } catch (Exception e) {
                                ApiResponse response = new ApiResponse("Failed to delete image", false);
                                throw new IllegalStateException(response.getMessage());
                            }
                        }
                    }
        String userProfileImageUrl = cloudinaryImageUploadService.uploadImageToCloud(file,"vital_aid/users");
        existingUser.setProfileImageUrl(userProfileImageUrl);
    }

                                                // HELPER METHODS

                                            // CONVERTING ENTITY TO DTO 

    private UserDTO UsertoDTO(User user) {
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

                                            // CONVERTING DTO TO ENTITY 
    
    private RegisterDTO UserEntitytoDTO(User user) {
        RegisterDTO userDTO = this.modelMapper.map(user, RegisterDTO.class);
        return userDTO;
    }

                                        // CONVERTING REGISTER DTO TO ENTITY    
    
    private User registerDTOtoUser(RegisterDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setProfileImageUrl(BaseImageUrls.USER_PROFILE_PHOTO_BASE_URL);
        return user;
    }
}