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
import com.vital_aid_crud_api.Payloads.HospitalDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.service.Interfaces.HospitalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;




                                    // FETCHING ALL HOSPITALS

    @GetMapping("/allHospitals")
    public ResponseEntity<List<HospitalDTO>> getAllHospitalList(){
        List<HospitalDTO> hospitalDTOs = hospitalService.getAllHospitals();
        // return hospitalService.getAllHospitals();
        return new ResponseEntity<>(hospitalDTOs, HttpStatus.OK);
    }

                                    // FETCHING PROFILE OF A HOSPITAL

    @GetMapping("/viewHospital/{Id}")
    public ResponseEntity<HospitalDTO> viewHospitalDetailsById(@PathVariable Long Id){
        HospitalDTO hospitalDTO = hospitalService.getHospitalDetailsById(Id);
        return new ResponseEntity<>(hospitalDTO, HttpStatus.OK);
    }

                                    // REGISTERING A NEW HOSPITAL

    @PostMapping("/registerHospital")
    public ResponseEntity<HospitalDTO> registerHospital(@Valid @RequestPart("hospitalDTO") HospitalDTO hospitalDTO, @RequestPart(value = "file", required = false) MultipartFile file){

        if(file != null && !file.isEmpty()){
            hospitalDTO = hospitalService.registerHospitalWithImage(hospitalDTO, file);
        }else if(file == null && hospitalDTO != null){
            hospitalDTO.setHospitalPhotoUrl(BaseImageUrls.HOSPITAL_PROFILE_PHOTO_BASE_URL);
            hospitalDTO = hospitalService.registerHospital(hospitalDTO);
        }else{
            throw new IllegalArgumentException("Please provide the details you want to register");
        }
        return new ResponseEntity<>(hospitalDTO, HttpStatus.CREATED);
    }

                                    // UPDATING A HOSPITAL

    @PutMapping("/updateHospital/{Id}")
    public ResponseEntity<HospitalDTO> updateHospital(@PathVariable Long Id,
            @Valid @RequestPart(value = "hospitalDTO",required = false) HospitalDTO hospitalDTO,
            @RequestPart(value = "file", required = false) MultipartFile file){

        HospitalDTO updatedHospitalDTO;
        if(hospitalDTO == null && file == null){
            throw new IllegalArgumentException("Please provide details to update");
        }

        if(hospitalDTO != null && file != null){
            updatedHospitalDTO = hospitalService.updateHospitalWithDetailsAndImage(Id, hospitalDTO, file);
        }else if(hospitalDTO != null && file == null){
            updatedHospitalDTO = hospitalService.updateHospitalDetails(Id, hospitalDTO);
        }else{
            updatedHospitalDTO = hospitalService.updateHospitalImage(Id, file);
        }

        return new ResponseEntity<>(updatedHospitalDTO, HttpStatus.OK);
    }

                                    // DELETING A HOSPITAL
                                    
    @DeleteMapping("/deleteHospital/{Id}")
    public ResponseEntity<ApiResponse> deleteHospital(@PathVariable Long Id){
        hospitalService.deleteHospitalById(Id);
        return new ResponseEntity<>(new ApiResponse("Hospital Deleted Successfully", true), HttpStatus.OK);
    }

    
}
