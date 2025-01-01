package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.service.Interfaces.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

                                        // FETCHING ALL DOCTORS

    @GetMapping("/allDoctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsList() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

                                        // REGISTERING A NEW DOCTOR

    @PostMapping("/registerDoctor")
    public ResponseEntity<String> saveDoctor(
            @Valid @RequestPart("doctorDTO") DoctorDTO doctorDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {
                if (file != null && !file.isEmpty()){
                    doctorDTO = doctorService.registerDoctorWithImage(doctorDTO, file);
                }else if(file == null && doctorDTO != null){
            
                doctorDTO.setDoctorPhotoUrl(BaseImageUrls.DOCTOR_PROFILE_PHOTO_BASE_URL);
                doctorDTO = doctorService.registerDoctor(doctorDTO);
            }else{
                throw new IllegalArgumentException("Please provide the details you want to register");
            }
            return new ResponseEntity<>("Doctor Registered Successfully", HttpStatus.CREATED);
        }

                                        //FETCHING PROFILE OF A DOCTOR

    @GetMapping("/viewDoctor/{Id}")
    public ResponseEntity<DoctorDTO> viewDoctorDetailsById(@PathVariable Long Id) {
        DoctorDTO doctor = doctorService.getDoctorbyId(Id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

                                        // UPDATING A DOCTOR

    @PutMapping("/updateDoctor/{Id}")
    public ResponseEntity<DoctorDTO> updateDoctorDetailsbyID(@PathVariable Long Id,
                    @Valid @RequestPart(value = "doctorDTO", required = false) DoctorDTO doctorDTO,
                    @RequestPart(value = "file", required = false) MultipartFile file) {

        DoctorDTO updatedDoctorDTO = doctorService.getDoctorbyId(Id);
        if (doctorDTO == null && file == null) {
            throw new IllegalArgumentException("Please provide the details you want to update");
        }

        if (doctorDTO != null && file != null) {
            updatedDoctorDTO = doctorService.updateDoctorWithDetailsAndImage(Id, doctorDTO, file);
        }else if (doctorDTO != null && file == null) {
            updatedDoctorDTO = doctorService.updateDoctorDetails(Id, doctorDTO);
        }else{
            updatedDoctorDTO = doctorService.updateDoctorImage(Id, file);
        }

        return new ResponseEntity<>(updatedDoctorDTO, HttpStatus.OK);
    }

    
                                        // DELETING A DOCTOR
                                        
    @DeleteMapping("/deleteDoctor/{Id}")
    public ResponseEntity<String> deleteDoctorbyId(@PathVariable Long Id) {
        doctorService.deleteDoctorbyId(Id);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }
}
