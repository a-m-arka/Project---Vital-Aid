package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.AmbulanceDTO;
import com.vital_aid_crud_api.service.Interfaces.AmbulanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/ambulance")
public class AmbulanceController {
   
    @Autowired
    private AmbulanceService ambulanceService;

                                // VIEW ALL AMBULANCES LIST

    @GetMapping("/allAmbulances")
    public ResponseEntity<List<AmbulanceDTO>> viewAllAmbulances(){
        List<AmbulanceDTO> ambulances =  ambulanceService.viewAllAmbulances();
        return new ResponseEntity<>(ambulances, HttpStatus.OK);
    }

                                // REGISTER A NEW AMBULANCE

    @PostMapping("/registerAmbulance")
    public ResponseEntity<String> registerAmbulance(@Valid @RequestBody AmbulanceDTO ambulanceDTO){
        ambulanceDTO = ambulanceService.registerAmbulance(ambulanceDTO);
        return new ResponseEntity<>("Ambulance Registered Successfully", HttpStatus.CREATED);
    }

                        // VIEW AMBULANCE DETAILS BY NUMBER PLATE

    @GetMapping("/viewAmbulanceDetailsByNumPlate/{Id}")
    public ResponseEntity<AmbulanceDTO> viewAmbulanceDetailsByNumPlate(@PathVariable String Id){
        AmbulanceDTO ambulanceDTO = ambulanceService.viewAmbulanceDetailsByNumPlate(Id);
        return new ResponseEntity<>(ambulanceDTO, HttpStatus.OK);
    }

                            // UPDATE AMBULANCE DETAILS

    @PutMapping("/updateAmbulanceDetails/{Id}")
    public ResponseEntity<String> updateAmbulanceDetails(@PathVariable String Id,@Valid @RequestBody AmbulanceDTO ambulanceDTO){
        ambulanceDTO = ambulanceService.updateAmbulanceDetails(Id,ambulanceDTO);
        return new ResponseEntity<>("Ambulance Details Updated Successfully", HttpStatus.OK);
    }

                            // DELETE AMBULANCE BY NUMBER PLATE

    @DeleteMapping("/deleteAmbulanceByNumPlate/{Id}")
    public ResponseEntity<String> deleteAmbulanceByNumPlate(@PathVariable String Id){
        ambulanceService.deleteAmbulanceByNumPlate(Id);
        return new ResponseEntity<>("Ambulance Deleted Successfully", HttpStatus.OK);
    }

}
