package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.DoctorDTO;
import com.vital_aid_crud_api.Payloads.DoctorRatingDTO;
import com.vital_aid_crud_api.service.Interfaces.DoctorRatingService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vital_aid/doctorRating")
public class DoctorRatingController {

    @Autowired
    private DoctorRatingService doctorRatingService;

// =========================================================================================================
//                                        LIST OF ALL RATINGS   
    @GetMapping("/listOfAllRatings")
    public ResponseEntity<List<DoctorRatingDTO>> getListOfAllRatings() {
        List<DoctorRatingDTO> ratings = doctorRatingService.getAllDoctorRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                     LIST OF ALL RATING MADE BY USER (EMAIL)
    @GetMapping("/ratingsMadeByUser")
    public ResponseEntity<List<DoctorRatingDTO>> getRatingsMadeByAnUser(){
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DoctorRatingDTO> userRatings = doctorRatingService.getAllRatingsMadeByUser(userEmail);
        return new ResponseEntity<>(userRatings, HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                     LIST OF ALL RATING MADE BY USER (ID)
    @GetMapping("/ratingsMadeByUserId/{Id}")
    public ResponseEntity<List<DoctorRatingDTO>> getRatingsMadeByAnUserId(@PathVariable Long Id){
        List<DoctorRatingDTO> userRatings = doctorRatingService.getAllRatingsMadeByUserId(Id);
        return new ResponseEntity<>(userRatings, HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                    LIST OF ALL RATINGS MADE FOR DOCTOR (ID)
    @GetMapping("/ratingsMadeForDoctor/{Id}")
    public ResponseEntity<List<DoctorRatingDTO>> getRatingsMadeForDoctor(@PathVariable Long Id){
        List<DoctorRatingDTO> doctorRatings = doctorRatingService.getDoctorRatingListByDoctorId(Id);
        return new ResponseEntity<>(doctorRatings, HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                    LIST OF ALL RATINGS MADE FOR DOCTOR (EMAIL)
    @GetMapping("/ratingsMadeForDoctorEmail")
    public ResponseEntity<List<DoctorRatingDTO>> getRatingsMadeForDoctorEmail(){
        String doctorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DoctorRatingDTO> doctorRatings = doctorRatingService.getDoctorRatingsListByDoctorEmail(doctorEmail);
        return new ResponseEntity<>(doctorRatings, HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                            RATE A DOCTOR
    @PostMapping("/rateDoctor/{Id}")
    public ResponseEntity<String> rateADoctor(@PathVariable Long Id, @Valid @RequestBody DoctorRatingDTO doctorRatingDTO){
        doctorRatingDTO = doctorRatingService.rateDoctor(doctorRatingDTO, Id);
        return new ResponseEntity<>("Doctor rated successfully", HttpStatus.CREATED);
    }
// =========================================================================================================

// =========================================================================================================
//                                            DELETE A RATING
    @DeleteMapping("/deleteRating/{Id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long Id){
        doctorRatingService.deleteDoctorRating(Id);
        return new ResponseEntity<>("Doctor rating deleted successfully", HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                           UPDATE A RATING
    @PutMapping("/updateRating/{Id}")
    public ResponseEntity<String> updateARating(@PathVariable Long Id, @Valid @RequestBody DoctorRatingDTO doctorRatingDTO){
    doctorRatingDTO = doctorRatingService.updateDoctorRating(Id, doctorRatingDTO);
    return new ResponseEntity<>("Doctor rating updated successfully", HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                        GET DOCTOR RATING BY ID
    @GetMapping("/getDoctorRatingById/{Id}")
    public ResponseEntity<DoctorDTO> getDoctorRatingById(@PathVariable Long Id){
        DoctorDTO doctorDTO = doctorRatingService.getDoctorRatingById(Id);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }
// =========================================================================================================

// =========================================================================================================
//                                        GET DOCTOR RATING BY EMAIL
    @GetMapping("/getDoctorRatingByEmail")
    public ResponseEntity<DoctorDTO> getDoctorRatingByEmail() {
        String doctorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        DoctorDTO doctorDTO = doctorRatingService.getDoctorRatingByEmail(doctorEmail);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }
// =========================================================================================================

}
