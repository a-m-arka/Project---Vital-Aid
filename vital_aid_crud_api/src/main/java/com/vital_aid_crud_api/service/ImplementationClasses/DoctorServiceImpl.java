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
import com.vital_aid_crud_api.Entity.ConsultingTimes;
import com.vital_aid_crud_api.Entity.Doctor;
import com.vital_aid_crud_api.Entity.Hospital;
import com.vital_aid_crud_api.Exception.IllegalPasswordException;
import com.vital_aid_crud_api.Exception.OtpExpirationException;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.OtpDetails;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.repository.AppointmentRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.HospitalRepository;
import com.vital_aid_crud_api.service.Interfaces.CloudinaryImageUploadService;
import com.vital_aid_crud_api.service.Interfaces.DoctorService;
import com.vital_aid_crud_api.service.MailService.MailService;
import com.vital_aid_crud_api.service.OTPService.OTPService;
import com.vital_aid_crud_api.service.UniqueIdentityService.PersonValidationService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PersonValidationService personValidationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private OTPService otpService;

    @Autowired
    private MailService mailService;

    @Autowired
    private CloudinaryImageUploadService cloudinaryImageUploadService;

    @Autowired
    private HospitalRepository hospitalRepository;

    // @Autowired
    // private AdminRepository adminRepository;

//  ===================================================================================================================

                                            // REGISTERING A NEW DOCTOR
    @Transactional
    @Override
    public DoctorDTO registerDoctor(DoctorDTO doctorDTO) {
        Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));

        
        personValidationService.validateUniqueFields(
                doctorDTO.getPersonEmail(),
                doctorDTO.getPersonPhone());
        if (!doctorDTO.getLoginPassword().equals(doctorDTO.getConfirmPassword())) {
            throw new IllegalPasswordException("Passwords do not match");
        }

        doctorDTO.setLoginPassword(bCryptPasswordEncoder.encode(doctorDTO.getLoginPassword()));
        doctorDTO.setDoctorProfileImageUrl(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL);
        doctorDTO.setAvailabilityStatus("Available");
        doctorDTO.setDoctorAverageRating(0.0f);

        // String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        // Admin admin = adminRepository.findByPersonEmail(adminEmail)
        //         .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        Doctor doctor = DTOtoDoctor(doctorDTO);

        doctor.setWorksAt(hospital);
        // doctor.setDoctorManagedBy(admin);

        doctor = doctorRepository.save(doctor);
        return DoctortoDTO(doctor);
    }
//  ===================================================================================================================
    

                                                // DELETING DOCTOR ACCOUNT
    @Transactional
    @Override
    public void deleteDoctor(String doctorEmail) {
        Doctor doctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));

        String doctorCurrentPhotoUrl = doctor.getDoctorProfileImageUrl();
        if (doctorCurrentPhotoUrl != null
                && !doctorCurrentPhotoUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)) {
            String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(doctorCurrentPhotoUrl);
            System.out.println("Extracted Public ID: " + publicId);
            if (publicId != null) {
                try {
                    cloudinaryImageUploadService.deleteImageFromCloud(publicId); // Attempt to delete the image
                } catch (Exception e) {
                    ApiResponse response = new ApiResponse("Failed to delete image", false);
                    throw new IllegalStateException(response.getMessage());
                }
            }
        }

        // Removing the doctor from the list of doctors in the associated hospital
        Hospital hospital = doctor.getWorksAt();
        if (hospital != null) {
            hospital.getDoctors().remove(doctor);
        }

        for (Appointment appointment : doctor.getDoctorAppointments()) {
            appointment.setWithDoctor(null);
            appointmentRepository.save(appointment);
        }

        // Admin admin = doctor.getDoctorManagedBy();
        // if (admin != null) {
        //     admin.getDoctors().remove(doctor);
        //     adminRepository.save(admin);
        // }

        doctorRepository.delete(doctor);

    }
// ===================================================================================================================


                                            // LIST OF ALL DOCTORS
    @Transactional
    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> DoctortoDTO(doctor)).collect(Collectors.toList());
    }
//  ===================================================================================================================

                                    // VIEWING A DOCTOR'S PROFILE BY ID
    @Override
    public DoctorDTO getDoctorbyId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        return DoctortoDTO(doctor);
    }
// ===================================================================================================================

                                    // VIEWING A DOCTOR'S PROFILE BY EMAIL
    @Override
    public DoctorDTO getDoctorbyEmail(String personEmail) {
        Doctor doctor = doctorRepository.findByPersonEmail(personEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", personEmail));
        return DoctortoDTO(doctor);
    }
// ===================================================================================================================

                                        // UPDATING DOCTOR PROFILE DETAILS
    @Transactional
    @Override
    public DoctorDTO updateDoctorProfileDetails(String doctorEmail, DoctorDTO doctorDTO) {
        Doctor existingdoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));

        Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));

        personValidationService.validateUniqueFieldsForUpdate(
                doctorDTO.getPersonEmail(),
                doctorDTO.getPersonPhone(),
                existingdoctor.getPersonId());

        // String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Admin admin = adminRepository.findByPersonEmail(adminEmail)
        //         .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        existingdoctor.setPersonName(doctorDTO.getPersonName());
        existingdoctor.setPersonEmail(doctorDTO.getPersonEmail());
        existingdoctor.setPersonPhone(doctorDTO.getPersonPhone());
        existingdoctor.setDoctorFee(doctorDTO.getDoctorFee());
        existingdoctor.setDoctorGender(doctorDTO.getDoctorGender());
        existingdoctor.setDoctorCity(doctorDTO.getDoctorCity());
        existingdoctor.setSpecializationField(doctorDTO.getSpecializationField());
        existingdoctor.setDoctorSpecialization(doctorDTO.getDoctorSpecialization());
        existingdoctor.setConsultationDays(doctorDTO.getConsultationDays());
        existingdoctor.setConsultingTime(modelMapper.map(doctorDTO.getConsultingTime(), ConsultingTimes.class));
        existingdoctor.setWorksAt(hospital);
        existingdoctor.setAvailabilityStatus(doctorDTO.getAvailabilityStatus());
        // existingdoctor.setDoctorManagedBy(admin);

        Doctor updatedDoctor = doctorRepository.save(existingdoctor);
        return DoctortoDTO(updatedDoctor);
    }
// ===================================================================================================================


                                        // UPDATING DOCTOR PROFILE IMAGE 
    @Transactional
    @Override
    public void updateLoggedInDoctorImage(MultipartFile file) {
        String doctorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor existingdoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));
        String profileImageCurrentUrl = existingdoctor.getDoctorProfileImageUrl();
        if (!profileImageCurrentUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)) {
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
        String doctorProfileImageUrl = cloudinaryImageUploadService.uploadImageToCloud(file,"vital_aid/doctors");
        existingdoctor.setDoctorProfileImageUrl(doctorProfileImageUrl);
        doctorRepository.save(existingdoctor);
    }
// ===================================================================================================================

                                            // CHANGE DOCTOR PASSWORD
    @Transactional
    @Override
    public void changeDoctorPassword(String doctorEmail, ChangePasswordDTO changePasswordDTO) {

        Doctor existingDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));

        if (!bCryptPasswordEncoder.matches(changePasswordDTO.getOldPassword(), existingDoctor.getLoginPassword())) {
            throw new IllegalPasswordException("Old password is incorrect");
        }
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new IllegalPasswordException("Passwords do not match");
        }
        existingDoctor.setLoginPassword(bCryptPasswordEncoder.encode(changePasswordDTO.getNewPassword()));

        doctorRepository.save(existingDoctor);
    }
// ===================================================================================================================

                                        // FORGOT PASSWORD: SEND VERIFICATION CODE
    @Transactional
    @Override
    public void sendVerificationCode(String doctorEmail) {
        Doctor existingDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));

        String otp = otpService.generateOtp(doctorEmail, (SecurityConstants.OTP_EXPIRY_TIME_MINUTES) + 1);

        String subject = "Vital Aid - Verification Code";
        String body = String.format(
                "Dear %s,\n\nYour verification code is: %s\n\nThis code is valid for %d minutes.\n\nThank you,\nVital Aid Team",
                existingDoctor.getPersonName(), otp, SecurityConstants.OTP_EXPIRY_TIME_MINUTES);
        mailService.sendOtpEmail(doctorEmail, subject, body);
    }
    String validOTP; // Store the validated OTP
// ===================================================================================================================

                                        // FORGOT PASSWORD: VALIDATE OTP
    @Transactional
    @Override
    public void validateOtp(String otp) {
        OtpDetails details = otpService.validateOtp(otp);

        if (details.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new OtpExpirationException("Expired OTP. Resend OTP.");
        }
        validOTP = otp;
    }
//  ==================================================================================================================

                                    //  FORGOT PASSWORD: RESET PASSWORD
    @Transactional
    @Override
    public void resetPassword(String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        // Get the email associated with the validated OTP
        String doctorEmail = otpService.getEmailForOtp(validOTP);

        // Fetch doctor and update password
        Doctor existingDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));
        existingDoctor.setLoginPassword(bCryptPasswordEncoder.encode(newPassword));
        doctorRepository.save(existingDoctor);

        // Clear the OTP after successful reset
        otpService.clearOtp(doctorEmail);
    }
//  ===================================================================================================================


// ====================================================================================================================

                                        // UPDATE AVAILABILITY STATUS
    @Transactional
    @Override
    public void updateAvailabilityStatus(String availabilityStatus)
    {
        String doctorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor existingDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));
        existingDoctor.setAvailabilityStatus(availabilityStatus);
        doctorRepository.save(existingDoctor);
    }
// ===================================================================================================================

// ===================================================================================================================
                                        // GET DOCTOR AVAILABILITY STATUS
    @Override
    public DoctorDTO getDoctorAvailabilityStatus(String doctorEmail) {
        Doctor existingDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));
        DoctorDTO existingDoctorDTO = DoctortoDTO(existingDoctor);
        return existingDoctorDTO;
    }
// ====================================================================================================================


// ===================================================================================================================
                                                // HELPER METHODS

    // CONVERTING DOCTOR DTO TO DOCTOR ENTITY
    private Doctor DTOtoDoctor(DoctorDTO doctorDTO) {
        return modelMapper.map(doctorDTO, Doctor.class);
    }

    // CONVERTING DOCTOR ENTITY TO DOCTOR DTO
    private DoctorDTO DoctortoDTO(Doctor doctor) {
        DoctorDTO doctorDTO = modelMapper.map(doctor, DoctorDTO.class);
        doctorDTO.setHospitalName(doctor.getWorksAt().getHospitalName());
        return doctorDTO;
    }

}






// REGISTERING A NEW DOCTOR WITH IMAGE
    // @Transactional
    // @Override
    // public DoctorDTO registerDoctorWithImage(DoctorDTO doctorDTO, MultipartFile file) {
    //     Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
    //             .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));
    //     String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    //     Admin admin = adminRepository.findByPersonEmail(adminEmail)
    //             .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

    //     String doctorPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/doctors");
    //     doctorDTO.setDoctorPhotoUrl(doctorPhotoUrl);

    //     Doctor doctor = DTOtoDoctor(doctorDTO);

    //     doctor.setWorksAt(hospital);
    //     doctor.setDoctorManagedBy(admin);

    //     doctor = doctorRepository.save(doctor);
    //     return DoctortoDTO(doctor);
    // }




    // UPDATING DOCTOR PROFILE DETAILS AND IMAGE
    // @Transactional
    // @Override
    // public DoctorDTO updateDoctorWithDetailsAndImage(Long doctorId, DoctorDTO doctorDTO, MultipartFile file) {
    //     Doctor doctor = doctorRepository.findById(doctorId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

    //     Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
    //             .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));

    //     String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    //     Admin admin = adminRepository.findByPersonEmail(adminEmail)
    //             .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

    //     String doctorPhotoCurrentUrl = doctor.getDoctorProfileImageUrl();
    //     if (doctorPhotoCurrentUrl != null
    //             && !doctorPhotoCurrentUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)) {
    //         String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(doctorPhotoCurrentUrl);
    //         if (publicId != null) {
    //             try {
    //                 cloudinaryImageUploadService.deleteImageFromCloud(publicId);
    //             } catch (Exception e) {
    //                 ApiResponse response = new ApiResponse("Failed to delete image", false);
    //                 throw new IllegalStateException(response.getMessage());
    //             }
    //         }
    //     }
    //     doctor.setPersonName(doctorDTO.getPersonName());
    //     doctor.setPersonEmail(doctorDTO.getPersonEmail());
    //     doctor.setPersonPhone(doctorDTO.getPersonPhone());
    //     doctor.setDoctorFee(doctorDTO.getDoctorFee());
    //     doctor.setDoctorGender(doctorDTO.getDoctorGender());
    //     doctor.setDoctorCity(doctorDTO.getDoctorCity());
    //     doctor.setSpecializationField(doctorDTO.getSpecializationField());
    //     doctor.setDoctorSpecialization(doctorDTO.getDoctorSpecialization());
    //     doctor.setConsultationDays(doctorDTO.getConsultationDays());
    //     doctor.setConsultingTime(modelMapper.map(doctorDTO.getConsultingTime(), ConsultingTimes.class));
    //     String doctorPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/doctors");
    //     doctor.setDoctorProfileImageUrl(doctorPhotoUrl);
    //     doctor.setWorksAt(hospital);
    //     doctor.setDoctorManagedBy(admin);

    //     Doctor updatedDoctor = doctorRepository.save(doctor);
    //     return DoctortoDTO(updatedDoctor);
    // }



    // UPDATING DOCTOR IMAGE ONLY(By Admin)
    // @Transactional
    // @Override
    // public DoctorDTO updateDoctorImage(Long doctorId, MultipartFile file) {
    //     Doctor doctor = doctorRepository.findById(doctorId)
    //             .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

    //     String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    //     Admin admin = adminRepository.findByPersonEmail(adminEmail)
    //             .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

    //     String doctorPhotoCurrentUrl = doctor.getDoctorProfileImageUrl();
    //     if (doctorPhotoCurrentUrl != null
    //             && !doctorPhotoCurrentUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)) {
    //         String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(doctorPhotoCurrentUrl);
    //         if (publicId != null) {
    //             try {
    //                 cloudinaryImageUploadService.deleteImageFromCloud(publicId);
    //             } catch (Exception e) {
    //                 ApiResponse response = new ApiResponse("Failed to delete image", false);
    //                 throw new IllegalStateException(response.getMessage());
    //             }
    //         }
    //     }

    //     String doctorPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/doctors");
    //     doctor.setDoctorProfileImageUrl(doctorPhotoUrl);
    //     doctor.setDoctorManagedBy(admin);

    //     Doctor updatedDoctor = doctorRepository.save(doctor);
    //     return DoctortoDTO(updatedDoctor);
    // }