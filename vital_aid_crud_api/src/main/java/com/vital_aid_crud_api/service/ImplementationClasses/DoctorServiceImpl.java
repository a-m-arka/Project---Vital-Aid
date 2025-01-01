package com.vital_aid_crud_api.service.ImplementationClasses;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Entity.Admin;
import com.vital_aid_crud_api.Entity.Appointment;
import com.vital_aid_crud_api.Entity.ConsultingTimes;
import com.vital_aid_crud_api.Entity.Doctor;
import com.vital_aid_crud_api.Entity.Hospital;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.AppointmentRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.HospitalRepository;
import com.vital_aid_crud_api.service.Interfaces.CloudinaryImageUploadService;
import com.vital_aid_crud_api.service.Interfaces.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CloudinaryImageUploadService cloudinaryImageUploadService;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private AdminRepository adminRepository;

                                                 // REGISTERING A NEW DOCTOR WIHTOUT IMAGE

    @Transactional
    @Override
    public DoctorDTO registerDoctor(DoctorDTO doctorDTO) {
        Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));
        

        Doctor doctor = DTOtoDoctor(doctorDTO);

        doctor.setWorksAt(hospital);
        doctor.setDoctorManagedBy(admin);

        doctor = doctorRepository.save(doctor);
        return DoctortoDTO(doctor);
    }

                                              // REGISTERING A NEW DOCTOR WITH IMAGE

    @Transactional
    @Override
    public DoctorDTO registerDoctorWithImage(DoctorDTO doctorDTO, MultipartFile file) {
        Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String doctorPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/doctors");
        doctorDTO.setDoctorPhotoUrl(doctorPhotoUrl);

        Doctor doctor = DTOtoDoctor(doctorDTO);

        doctor.setWorksAt(hospital);
        doctor.setDoctorManagedBy(admin);
        
        doctor = doctorRepository.save(doctor);
        return DoctortoDTO(doctor);
    }

                                                // DELETING A DOCTOR

    @Transactional
    @Override
    public void deleteDoctorbyId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));

        String doctorCurrentPhotoUrl = doctor.getDoctorPhotoUrl();
        if (doctorCurrentPhotoUrl != null && !doctorCurrentPhotoUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)) {
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

        for(Appointment appointment: doctor.getDoctorAppointments()){
            appointment.setWithDoctor(null);
            appointmentRepository.save(appointment);
        }

        Admin admin = doctor.getDoctorManagedBy();
        if(admin != null){
            admin.getDoctors().remove(doctor);
            adminRepository.save(admin);
        }

        doctorRepository.delete(doctor);

    }

                                                // GETTING ALL DOCTORS LIST

    @Transactional
    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(doctor -> DoctortoDTO(doctor)).collect(Collectors.toList());
    }

                                            // VIEWING A DOCTOR'S PROFILE BY ID
    
    @Override
    public DoctorDTO getDoctorbyId(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        return DoctortoDTO(doctor);
    }

                                        // UPDATING DOCTOR DETAILS AND IMAGE    
    
    @Transactional
    @Override
    public DoctorDTO updateDoctorWithDetailsAndImage(Long doctorId, DoctorDTO doctorDTO, MultipartFile file) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));

        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        
        String doctorPhotoCurrentUrl = doctor.getDoctorPhotoUrl();
        if(doctorPhotoCurrentUrl != null && !doctorPhotoCurrentUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)){
            String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(doctorPhotoCurrentUrl);
            if(publicId != null){
                try {
                    cloudinaryImageUploadService.deleteImageFromCloud(publicId);
                } catch (Exception e) {
                    ApiResponse response = new ApiResponse("Failed to delete image", false);
                    throw new IllegalStateException(response.getMessage());
                }
            }
        }
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctor.setDoctorEmail(doctorDTO.getDoctorEmail());
        doctor.setDoctorPhone(doctorDTO.getDoctorPhone());
        doctor.setDoctorFee(doctorDTO.getDoctorFee());
        doctor.setDoctorGender(doctorDTO.getDoctorGender());
        doctor.setDoctorCity(doctorDTO.getDoctorCity());
        doctor.setSpecializationField(doctorDTO.getSpecializationField());
        doctor.setDoctorSpecialization(doctorDTO.getDoctorSpecialization());
        doctor.setConsultationDays(doctorDTO.getConsultationDays());
        doctor.setConsultingTime(modelMapper.map(doctorDTO.getConsultingTime(), ConsultingTimes.class));
        String doctorPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/doctors");
        doctor.setDoctorPhotoUrl(doctorPhotoUrl);
        doctor.setWorksAt(hospital);
        doctor.setDoctorManagedBy(admin);

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return DoctortoDTO(updatedDoctor);
    }

                                        // UPDATING DOCTOR DETAILS ONLY
    
    @Transactional
    @Override
    public DoctorDTO updateDoctorDetails(Long doctorId, DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        Hospital hospital = hospitalRepository.findByHospitalName(doctorDTO.getHospitalName())
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "name", doctorDTO.getHospitalName()));
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctor.setDoctorEmail(doctorDTO.getDoctorEmail());
        doctor.setDoctorPhone(doctorDTO.getDoctorPhone());
        doctor.setDoctorFee(doctorDTO.getDoctorFee());
        doctor.setDoctorGender(doctorDTO.getDoctorGender());
        doctor.setDoctorCity(doctorDTO.getDoctorCity());
        doctor.setSpecializationField(doctorDTO.getSpecializationField());
        doctor.setDoctorSpecialization(doctorDTO.getDoctorSpecialization());
        doctor.setConsultationDays(doctorDTO.getConsultationDays());
        doctor.setConsultingTime(modelMapper.map(doctorDTO.getConsultingTime(), ConsultingTimes.class));
        doctor.setWorksAt(hospital);
        doctor.setDoctorManagedBy(admin);

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return DoctortoDTO(updatedDoctor);
    }

                                        // UPDATING DOCTOR IMAGE ONLY
    
    @Transactional
    @Override
    public DoctorDTO updateDoctorImage(Long doctorId, MultipartFile file) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String doctorPhotoCurrentUrl = doctor.getDoctorPhotoUrl();
        if(doctorPhotoCurrentUrl != null && !doctorPhotoCurrentUrl.equals(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL)){
            String publicId = cloudinaryImageUploadService.extractPublicIdFromUrl(doctorPhotoCurrentUrl);
            if(publicId != null){
                 try {
                        cloudinaryImageUploadService.deleteImageFromCloud(publicId);
                     } catch (Exception e) {
                        ApiResponse response = new ApiResponse("Failed to delete image", false);
                        throw new IllegalStateException(response.getMessage());
                     }
                }
            }

        String doctorPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/doctors");
        doctor.setDoctorPhotoUrl(doctorPhotoUrl);
        doctor.setDoctorManagedBy(admin);

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return DoctortoDTO(updatedDoctor);
    }


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
