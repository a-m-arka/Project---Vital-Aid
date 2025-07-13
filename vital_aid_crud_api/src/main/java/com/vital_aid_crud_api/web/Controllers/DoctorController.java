package com.vital_aid_crud_api.web.Controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.ChangePasswordDTO;
import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.service.Interfaces.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

// ===================================================================================================================
                                        // FETCHING ALL DOCTORS

    @GetMapping("/allDoctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsList() {
        List<DoctorDTO> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
// ===================================================================================================================

// ===================================================================================================================
                                        //FETCHING A DOCTOR BY ID

    @GetMapping("/viewDoctor/{Id}")
    public ResponseEntity<DoctorDTO> viewDoctorDetailsById(@PathVariable Long Id) {
        DoctorDTO doctor = doctorService.getDoctorbyId(Id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
// ===================================================================================================================

// ===================================================================================================================
                                        // FETCHING DOCTOR PROFILE
    @GetMapping("/doctorProfile")
    public ResponseEntity<DoctorDTO> viewDoctorProfile(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        DoctorDTO doctorDTO = doctorService.getDoctorbyEmail(email);

        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }
// ===================================================================================================================

//  ===================================================================================================================
                                        // UPDATING DOCTOR PROFILE

    @PutMapping("/updateDoctorProfile")
    public ResponseEntity<DoctorDTO> updateDoctorProfileDetailsby(@Valid @RequestBody DoctorDTO doctorDTO) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Update the doctor profile
        DoctorDTO updatedDoctorDTO = doctorService.updateDoctorProfileDetails(email, doctorDTO);

        return new ResponseEntity<>(updatedDoctorDTO, HttpStatus.OK);
    }
//  ===================================================================================================================

// ====================================================================================================================
                                                // CHANGING PASSWORD
                                    
    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        doctorService.changeDoctorPassword(email, changePasswordDTO);
        ApiResponse response = new ApiResponse("Password changed successfully", true);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
    }
// ====================================================================================================================

// ====================================================================================================================
                                    // UPDATING AVAILABILITY STATUS
    @PutMapping("/updateAvailabilityStatus")
    public ResponseEntity<String> updateAvailabilityStatus(@Valid @RequestBody DoctorDTO doctorDTO) {
        String availabilityStatus = doctorDTO.getAvailabilityStatus();
        doctorService.updateAvailabilityStatus(availabilityStatus);
        return new ResponseEntity<>("Availability status updated successfully", HttpStatus.OK);
}
// ====================================================================================================================

// ====================================================================================================================
                                        // GETTING DOCTOR AVAILABILITY STATUS
                                        
    @GetMapping("/getAvailabilityStatus")
    public ResponseEntity<DoctorDTO> getDoctorAvailabilityStatus() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        DoctorDTO doctorDTO = doctorService.getDoctorAvailabilityStatus(email);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }
// =====================================================================================================================

// =====================================================================================================================
                                        // DELETING A DOCTOR
                                        
    @DeleteMapping("/deleteDoctorAccount")
    public ResponseEntity<String> deleteDoctor() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        doctorService.deleteDoctor(email);
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }
// =====================================================================================================================
}
