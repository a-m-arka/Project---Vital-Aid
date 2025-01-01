package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Payloads.DoctorDTO;

public interface DoctorService {
    List<DoctorDTO> getAllDoctors();
    DoctorDTO registerDoctor(DoctorDTO doctorDTO);
    DoctorDTO registerDoctorWithImage(DoctorDTO doctorDTO, MultipartFile file);
    DoctorDTO updateDoctorWithDetailsAndImage(Long doctorId, DoctorDTO doctorDTO, MultipartFile file);
    DoctorDTO updateDoctorDetails(Long doctorId, DoctorDTO doctorDTO);
    DoctorDTO updateDoctorImage(Long doctorId, MultipartFile file);
    DoctorDTO getDoctorbyId(Long doctorId);
    void deleteDoctorbyId(Long doctorId);
}
