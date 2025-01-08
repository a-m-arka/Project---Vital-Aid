package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.HospitalDTO;

public interface HospitalService {
    List<HospitalDTO> getAllHospitals();
    List<DoctorDTO> getAllDoctorsOfAHospital(Long hospitalId);
    HospitalDTO registerHospital(HospitalDTO hospitalDTO);
    HospitalDTO registerHospitalWithImage(HospitalDTO hospitalDTO, MultipartFile file);
    HospitalDTO updateHospitalWithDetailsAndImage(Long hospitalId, HospitalDTO hospitalDTO, MultipartFile file);
    HospitalDTO updateHospitalDetails(Long hospitalId,HospitalDTO hospitalDTO);
    HospitalDTO updateHospitalImage(Long hospitalId, MultipartFile file);
    HospitalDTO getHospitalDetailsById(Long hospitalId);
    void deleteHospitalById(Long hospitalId);
}
