package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.EmbeddedClass.CallsAmbulanceId;
import com.vital_aid_crud_api.Payloads.CallsAmbulanceDTO;

public interface CallsAmbulanceService {

    public List<CallsAmbulanceDTO> listOfAllCallsForAmbulance(); // get all calls for ambulance
    public CallsAmbulanceDTO detailsOfACallForAmbulance(CallsAmbulanceId callId); // get details of a call for ambulance
    public CallsAmbulanceDTO makeACallForAmbulance(CallsAmbulanceDTO call); // make a call for ambulance
    // update
    // delete
}
