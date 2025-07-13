package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.DoctorRatingDTO;

public interface DoctorRatingService {

    DoctorRatingDTO rateDoctor(DoctorRatingDTO doctorRatingDTO, Long doctorId);
    void deleteDoctorRating(Long doctorRatingId);
    DoctorRatingDTO updateDoctorRating(Long doctorRatingId, DoctorRatingDTO doctorRatingDTO);
    List<DoctorRatingDTO> getDoctorRatingListByDoctorId(Long doctorId);
    List<DoctorRatingDTO> getDoctorRatingsListByDoctorEmail(String doctorEmail);
    DoctorDTO getDoctorRatingById(Long doctorId);
    DoctorDTO getDoctorRatingByEmail(String doctorEmail);
    List<DoctorRatingDTO> getAllDoctorRatings();
    List<DoctorRatingDTO> getAllRatingsMadeByUser(String userEmail);
    List<DoctorRatingDTO> getAllRatingsMadeByUserId(Long userId);

}
