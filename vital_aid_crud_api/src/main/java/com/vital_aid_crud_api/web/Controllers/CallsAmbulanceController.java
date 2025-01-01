package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.EmbeddedClass.CallsAmbulanceId;
import com.vital_aid_crud_api.Payloads.CallsAmbulanceDTO;
import com.vital_aid_crud_api.Payloads.UserDTO;
import com.vital_aid_crud_api.service.Interfaces.CallsAmbulanceService;
import com.vital_aid_crud_api.service.Interfaces.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/call_ambulance")
public class CallsAmbulanceController {

    @Autowired
    private CallsAmbulanceService callsAmbulanceService;

    @Autowired
    private UserService userService;

                                        //  LIST OF ALL CALLS FOR AMBULANCE
    @GetMapping("/allCalls")
    public ResponseEntity<List<CallsAmbulanceDTO>> listOfAllCallsForAmbulance() {
        List<CallsAmbulanceDTO> calls = callsAmbulanceService.listOfAllCallsForAmbulance();
        return new ResponseEntity<>(calls, HttpStatus.OK);
    }

                                        // DETAILS OF A CALL FOR AMBULANCE
    @GetMapping("/callDetails/{Id}/{id}")
    public ResponseEntity<CallsAmbulanceDTO> detailsOfACallForAmbulance(@PathVariable Long Id, @PathVariable String id) {
        CallsAmbulanceId callId = new CallsAmbulanceId(Id, id);
        CallsAmbulanceDTO call = callsAmbulanceService.detailsOfACallForAmbulance(callId);
        return new ResponseEntity<>(call, HttpStatus.OK);
    }

                                        // MAKE A CALL FOR AMBULANCE
    @PostMapping("/makeCall/{Id}")
    public ResponseEntity<String> makeCallForAmbulance(@PathVariable String Id, @Valid @RequestBody CallsAmbulanceDTO callsAmbulanceDTO)
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        UserDTO calledByUser = userService.getUserByEmail(userEmail);
        callsAmbulanceDTO.setCalledByUserId(calledByUser.getId());
        callsAmbulanceDTO.setAmbulanceNumberplate(Id);

        callsAmbulanceDTO = callsAmbulanceService.makeACallForAmbulance(callsAmbulanceDTO);
        return new ResponseEntity<>("Call for ambulance made successfully", HttpStatus.CREATED);
    }
    
}
