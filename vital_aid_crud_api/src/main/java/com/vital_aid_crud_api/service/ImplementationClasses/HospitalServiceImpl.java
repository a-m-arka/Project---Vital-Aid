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
import com.vital_aid_crud_api.Entity.Doctor;
import com.vital_aid_crud_api.Entity.Hospital;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.HospitalDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.HospitalRepository;
import com.vital_aid_crud_api.service.Interfaces.CloudinaryImageUploadService;
import com.vital_aid_crud_api.service.Interfaces.HospitalService;


@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CloudinaryImageUploadService cloudinaryImageUploadService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AdminRepository adminRepository;

                                                // ALL HOSPITAL LIST  

    @Transactional
    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> hospitals = this.hospitalRepository.findAll();
        List<HospitalDTO> hospitalDTOs = hospitals.stream().map(hospital -> Hospital_to_HospitalDTO(hospital))
                .collect(Collectors.toList());

        return hospitalDTOs;
    }

                                            // ALL DOCTORS OF A HOSPITAL

    @Transactional
    @Override
    public List<DoctorDTO> getAllDoctorsOfAHospital(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", hospitalId));
        List<Doctor> doctors = hospital.getDoctors();
        List<DoctorDTO> doctorDTOs = doctors.stream().map(doctor -> {
            DoctorDTO doctorDTO = this.modelMapper.map(doctor, DoctorDTO.class);
            return doctorDTO;
        }).collect(Collectors.toList());

        return doctorDTOs;
    }

                                            // VIEW HOSPITAL DETAILS BY ID

    @Transactional
    @Override
    public HospitalDTO getHospitalDetailsById(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", hospitalId));
        return Hospital_to_HospitalDTO(hospital);
    }

                                            // REGISTER A NEW HOSPITAL                

    @Transactional
    @Override
    public HospitalDTO registerHospital(HospitalDTO hospitalDTO) {
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));
        Hospital hospital = HospitalDTO_to_Hospital(hospitalDTO);
        hospital.setHospitalManagedBy(admin);

        hospital = hospitalRepository.save(hospital);
        return Hospital_to_HospitalDTO(hospital);
    }

                                            // REGISTER A NEW HOSPITAL WITH IMAGE

    @Transactional
    @Override
    public HospitalDTO registerHospitalWithImage(HospitalDTO hospitalDTO, MultipartFile file) {

        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String hospitalPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/hospitals");
        hospitalDTO.setHospitalPhotoUrl(hospitalPhotoUrl);

        Hospital hospital = HospitalDTO_to_Hospital(hospitalDTO);

        hospital.setHospitalManagedBy(admin);


        hospital = hospitalRepository.save(hospital);
        return Hospital_to_HospitalDTO(hospital);
    }

                                            // UPDATE HOSPITAL DETAILS AND IMAGE

    @Transactional
    @Override
    public HospitalDTO updateHospitalWithDetailsAndImage(Long HospitalId, HospitalDTO hospitalDTO, MultipartFile file) {
        Hospital hospital = hospitalRepository.findById(HospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", HospitalId));

        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String hospitalCurrentPhotoUrl = hospital.getHospitalPhotoUrl();
        if(hospitalCurrentPhotoUrl != null && !hospitalCurrentPhotoUrl.equals(BaseImageUrls.HOSPITAL_PROFILE_PHOTO_BASE_URL))
        {
            String publicID = cloudinaryImageUploadService.extractPublicIdFromUrl(hospitalCurrentPhotoUrl);
            if(publicID != null)
            {
                try {
                    cloudinaryImageUploadService.deleteImageFromCloud(publicID);
                } catch (Exception e) {
                    ApiResponse response = new ApiResponse("Failed to delete image", false);
                    throw new IllegalStateException(response.getMessage());
                }
            }
        }

        hospital.setHospitalName(hospitalDTO.getHospitalName());
        hospital.setHospitalLocation(hospitalDTO.getHospitalLocation());
        hospital.setHospitalContact(hospitalDTO.getHospitalContact());
        hospital.setHospitalEmail(hospitalDTO.getHospitalEmail());
        hospital.setTotalGeneralBeds(hospitalDTO.getTotalGeneralBeds());
        hospital.setTotalIcuBeds(hospitalDTO.getTotalIcuBeds());
        hospital.setTotalVentilators(hospitalDTO.getTotalVentilators());
        hospital.setHospitalFacilities(hospitalDTO.getHospitalFacilities());

        String hospitalPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/hospitals");
        hospital.setHospitalPhotoUrl(hospitalPhotoUrl);

        hospital.setHospitalManagedBy(admin);

        Hospital updatedHospital = hospitalRepository.save(hospital);

        return Hospital_to_HospitalDTO(updatedHospital);

    }

                                            // UPDATE HOSPITAL DETAILS ONLY

    @Transactional
    @Override
    public HospitalDTO updateHospitalDetails(Long hospitalId, HospitalDTO hospitalDTO) {
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", hospitalId));
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        hospital.setHospitalName(hospitalDTO.getHospitalName());
        hospital.setHospitalLocation(hospitalDTO.getHospitalLocation());
        hospital.setHospitalContact(hospitalDTO.getHospitalContact());
        hospital.setHospitalEmail(hospitalDTO.getHospitalEmail());
        hospital.setTotalGeneralBeds(hospitalDTO.getTotalGeneralBeds());
        hospital.setTotalIcuBeds(hospitalDTO.getTotalIcuBeds());
        hospital.setTotalVentilators(hospitalDTO.getTotalVentilators());
        hospital.setHospitalFacilities(hospitalDTO.getHospitalFacilities());
        hospital.setHospitalManagedBy(admin);

        Hospital updatedHospital = hospitalRepository.save(hospital);
        return Hospital_to_HospitalDTO(updatedHospital);
    }

                                            // UPDATE HOSPITAL IMAGE ONLY

    @Transactional
    @Override
    public HospitalDTO updateHospitalImage(Long hospitalId, MultipartFile file) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", hospitalId));

        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String hospitalCurrentPhotoUrl = hospital.getHospitalPhotoUrl();
        if(hospitalCurrentPhotoUrl != null && !hospitalCurrentPhotoUrl.equals(BaseImageUrls.HOSPITAL_PROFILE_PHOTO_BASE_URL))
            {
                String publicID = cloudinaryImageUploadService.extractPublicIdFromUrl(hospitalCurrentPhotoUrl);
                if(publicID != null)
                    {
                        try {
                            cloudinaryImageUploadService.deleteImageFromCloud(publicID);
                        } catch (Exception e) {
                            ApiResponse response = new ApiResponse("Failed to delete image", false);
                            throw new IllegalStateException(response.getMessage());
                        }
                    }
            }
            
        String hospitalPhotoUrl = cloudinaryImageUploadService.uploadImageToCloud(file, "vital_aid/hospitals");
        hospital.setHospitalPhotoUrl(hospitalPhotoUrl);

        hospital.setHospitalManagedBy(admin);

        Hospital updatedHospital = hospitalRepository.save(hospital);

        return Hospital_to_HospitalDTO(updatedHospital);
    }

                                                // DELETE HOSPITAL

    @Transactional
    @Override
    public void deleteHospitalById(Long hospitaId) {
        Hospital hospital = hospitalRepository.findById(hospitaId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital", "id", hospitaId));

        String hospitalPhotoUrl = hospital.getHospitalPhotoUrl();
        if (hospitalPhotoUrl != null && !hospitalPhotoUrl.equals(BaseImageUrls.HOSPITAL_PROFILE_PHOTO_BASE_URL)) {
            String publicID = cloudinaryImageUploadService.extractPublicIdFromUrl(hospitalPhotoUrl);
            if (publicID != null) {
                try {
                    cloudinaryImageUploadService.deleteImageFromCloud(publicID);
                } catch (Exception e) {
                    ApiResponse response = new ApiResponse("Failed to delete image", false);
                    throw new IllegalStateException(response.getMessage());
                }
            }
        }

        for (Doctor doctor : hospital.getDoctors()) {
            doctor.setWorksAt(null);
            doctorRepository.save(doctor); // Persist the change
        }

        Admin admin = hospital.getHospitalManagedBy();
        if(admin != null) {
            admin.getHospitals().remove(hospital);
            adminRepository.save(admin);
        }

        hospitalRepository.delete(hospital);
    }


                                                // HELPER METHODS

                                        // CONVERT HOSPITALDTO TO HOSPITAL

    private Hospital HospitalDTO_to_Hospital(HospitalDTO hospitalDTO) {
        Hospital hospital = this.modelMapper.map(hospitalDTO, Hospital.class);
        return hospital;
    }

                                        // CONVERT HOSPITAL TO HOSPITALDTO

    private HospitalDTO Hospital_to_HospitalDTO(Hospital hospital) {
        HospitalDTO hospitalDTO = this.modelMapper.map(hospital, HospitalDTO.class);
        hospitalDTO.setHospitalTotalDoctor(hospital.getDoctors() != null ? hospital.getDoctors().size() : 0);
        return hospitalDTO;
    }
}
