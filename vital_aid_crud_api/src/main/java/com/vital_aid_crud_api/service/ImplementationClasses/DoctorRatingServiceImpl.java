package com.vital_aid_crud_api.service.ImplementationClasses;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.Entity.Doctor;
import com.vital_aid_crud_api.Entity.DoctorRating;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.DoctorRatingDTO;
import com.vital_aid_crud_api.repository.DoctorRatingRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.DoctorRatingService;

import jakarta.transaction.Transactional;

@Service
public class DoctorRatingServiceImpl implements DoctorRatingService {

    @Autowired
    private DoctorRatingRepository doctorRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper doctorRatingMapper;

// ==============================================================================================

                                        // LIST OF ALL DOCTOR RATINGS
    @Override
    @Transactional
    public List<DoctorRatingDTO> getAllDoctorRatings() {
        List<DoctorRating> doctorRatings = doctorRatingRepository.findAll();
        return doctorRatings.stream()
                .map(this::convertToDTO)
                .toList();
    }
    
// ===============================================================================================

// ===============================================================================================
                                    // LIST OF ALL RATINGS MADE BY USER(EMAIL)
    @Override
    @Transactional
    public List<DoctorRatingDTO> getAllRatingsMadeByUser(String userEmail){
        User user = userRepository.findByPersonEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        List<DoctorRating> doctorRatings = user.getRatedDoctors();
        return doctorRatings.stream()
                .map(this::convertToDTO)
                .toList();
    }

// ===============================================================================================

// ===============================================================================================
                                    // LIST OF ALL RATINGS MADE BY USER(ID)
    @Override
    @Transactional
    public List<DoctorRatingDTO> getAllRatingsMadeByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<DoctorRating> doctorRatings = user.getRatedDoctors();
        return doctorRatings.stream()
                .map(this::convertToDTO)
                .toList();
    }

// ===============================================================================================

// ===============================================================================================
                                    // DOCTOR RATINGS LIST BY DOCTOR ID
    @Override
    @Transactional
    public List<DoctorRatingDTO> getDoctorRatingListByDoctorId(Long doctorId){
        Doctor rateDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        List<DoctorRating> doctorRatings = rateDoctor.getDoctorRatings();
        return doctorRatings.stream()
                .map(this::convertToDTO)
                .toList();
    }

// ===============================================================================================

// ===============================================================================================
                                    // DOCTOR RATINGS LIST BY DOCTOR EMAIL
    @Override
    @Transactional
    public List<DoctorRatingDTO> getDoctorRatingsListByDoctorEmail(String doctorEmail) {
        Doctor ratedDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));
        List<DoctorRating> doctorRatings = ratedDoctor.getDoctorRatings();
        return doctorRatings.stream()
                .map(this::convertToDTO)
                .toList();
    }

// ===============================================================================================

// ===============================================================================================

                                    // RATE A DOCTOR
    @Override
    @Transactional
    public DoctorRatingDTO rateDoctor(DoctorRatingDTO doctorRatingDTO, Long doctorId) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User ratedByUser = userRepository.findByPersonEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));

        Doctor ratedDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));

        
        DoctorRating doctorRating = convertToEntity(doctorRatingDTO);

        doctorRating.setRatingMadeByUser(ratedByUser);
        doctorRating.setRatingMadeForDoctor(ratedDoctor);

        doctorRating = doctorRatingRepository.save(doctorRating);

        List<DoctorRating> doctorRatings = ratedDoctor.getDoctorRatings();
        if (doctorRatings == null || doctorRatings.isEmpty()) {
            ratedDoctor.setDoctorAverageRating(0.0f);
        } else {
            Float averageRating = doctorRatings.stream()
                .map(DoctorRating::getRating)
                .reduce(0f, Float::sum) / doctorRatings.size();
            ratedDoctor.setDoctorAverageRating(averageRating);
        }
        doctorRepository.save(ratedDoctor);


        return convertToDTO(doctorRating);
    }
// ===============================================================================================

// ===============================================================================================

                                    // UPDATE RATING
    @Override
    @Transactional
    public DoctorRatingDTO updateDoctorRating(Long doctorRatingId, DoctorRatingDTO doctorRatingDTO){
        DoctorRating existingDoctorRating = doctorRatingRepository.findById(doctorRatingId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Rating", "id", doctorRatingId));
        // User ratedByUser = existingDoctorRating.getRatedByUser();
        Doctor ratedDoctor = existingDoctorRating.getRatingMadeForDoctor();

        existingDoctorRating.setRating(doctorRatingDTO.getRating());
        existingDoctorRating.setReview(doctorRatingDTO.getReview());
        existingDoctorRating = doctorRatingRepository.save(existingDoctorRating);

        List<DoctorRating> doctorRatings = ratedDoctor.getDoctorRatings();
        if(doctorRatings.isEmpty()){
            ratedDoctor.setDoctorAverageRating(doctorRatingDTO.getRating());
        }else{
            Float averageRating = doctorRatings.stream()
                    .map(DoctorRating::getRating)
                    .reduce(0f, Float::sum) / doctorRatings.size();
            ratedDoctor.setDoctorAverageRating(averageRating);
        }
        doctorRepository.save(ratedDoctor);
        return convertToDTO(existingDoctorRating);
    }
// ===============================================================================================



// ===============================================================================================
                                    // DELETE RATING
    @Override
    @Transactional
    public void deleteDoctorRating(Long doctorRatingId) {
        DoctorRating doctorRating = doctorRatingRepository.findById(doctorRatingId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Rating", "id", doctorRatingId));
        User ratedByUser = doctorRating.getRatingMadeByUser();
        ratedByUser.getRatedDoctors().remove(doctorRating);
        userRepository.save(ratedByUser);
        Doctor ratedDoctor = doctorRating.getRatingMadeForDoctor();
        ratedDoctor.getDoctorRatings().remove(doctorRating);

        List<DoctorRating> doctorRatings = ratedDoctor.getDoctorRatings();
        if(!doctorRatings.isEmpty() && doctorRatings != null){
            if(!doctorRatings.isEmpty()){
                Float averageRating = doctorRatings.stream()
                    .map(DoctorRating::getRating)
                    .reduce(0f, Float::sum) / doctorRatings.size();
                ratedDoctor.setDoctorAverageRating(averageRating);

            }else {
            ratedDoctor.setDoctorAverageRating(0.0f);
        }
    }else{
            ratedDoctor.setDoctorAverageRating(0.0f);
        }

        doctorRepository.save(ratedDoctor);
        doctorRatingRepository.delete(doctorRating);
    }
// =============================================================================================== 

// ===============================================================================================

                                    // GET DOCTOR RATING BY ID
    @Override
    @Transactional
    public DoctorDTO getDoctorRatingById(Long doctorId){
        Doctor ratedDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", doctorId));
        
        DoctorDTO doctorDTO = doctorRatingMapper.map(ratedDoctor, DoctorDTO.class);

        doctorDTO.setDoctorAverageRating(ratedDoctor.getDoctorAverageRating());
        return doctorDTO;
    }

// ===============================================================================================

// ===============================================================================================

                                    // GET DOCTOR RATING BY EMAIL
    @Override
    @Transactional
    public DoctorDTO getDoctorRatingByEmail(String doctorEmail){
        Doctor ratedDoctor = doctorRepository.findByPersonEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "email", doctorEmail));
        DoctorDTO doctorDTO = doctorRatingMapper.map(ratedDoctor, DoctorDTO.class);

        doctorDTO.setDoctorAverageRating(ratedDoctor.getDoctorAverageRating());
        return doctorDTO;
    }

// ===============================================================================================


// ===============================================================================================
                                    // HELPER METHODS

                        // Convert DoctorRatingDTO to DoctorRating Entity
    private DoctorRating convertToEntity(DoctorRatingDTO doctorRatingDTO) {
        return doctorRatingMapper.map(doctorRatingDTO, DoctorRating.class);
    }

                        // Convert DoctorRating Entity to DoctorRatingDTO
    private DoctorRatingDTO convertToDTO(DoctorRating doctorRating) {
        DoctorRatingDTO doctorRatingDTO = doctorRatingMapper.map(doctorRating, DoctorRatingDTO.class);
        doctorRatingDTO.setRatedByUserName(doctorRating.getRatingMadeByUser().getPersonName());
        doctorRatingDTO.setRatingForDoctorName(doctorRating.getRatingMadeForDoctor().getPersonName());
        return doctorRatingDTO;
    }
// ===============================================================================================
}
