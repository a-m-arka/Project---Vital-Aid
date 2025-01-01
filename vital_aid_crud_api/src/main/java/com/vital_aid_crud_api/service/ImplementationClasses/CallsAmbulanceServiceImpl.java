package com.vital_aid_crud_api.service.ImplementationClasses;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.EmbeddedClass.CallsAmbulanceId;
import com.vital_aid_crud_api.Entity.Ambulance;
import com.vital_aid_crud_api.Entity.CallsAmbulance;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.CallsAmbulanceDTO;
import com.vital_aid_crud_api.repository.AmbulanceRepository;
import com.vital_aid_crud_api.repository.CallsAmbulanceRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.CallsAmbulanceService;

import jakarta.transaction.Transactional;

@Service
public class CallsAmbulanceServiceImpl implements CallsAmbulanceService{

    @Autowired
    private CallsAmbulanceRepository callsAmbulanceRepository;

    @Autowired
    private ModelMapper callsAmbulanceMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmbulanceRepository ambulanceRepository;

                                        // LIST OF ALL AMBULANCE CALLS

    @Transactional
    @Override
    public List<CallsAmbulanceDTO> listOfAllCallsForAmbulance(){
        List<CallsAmbulance> allCalls = callsAmbulanceRepository.findAll();
        return allCalls.stream().map(this::convertToDTO).toList();
    }

                                    // AMBULANCE CALL DETAILS BY CALL ID

    @Transactional
    @Override
    public CallsAmbulanceDTO detailsOfACallForAmbulance(CallsAmbulanceId callId){
        Long userId = callId.getPersonId();
        String ambulanceNumPlate = callId.getAmbulanceNumPlate();

        // Check if user exists
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
        throw new ResourceNotFoundException("User", "ID", userId.toString());
        }

        // Check if ambulance exists
        boolean ambulanceExists = ambulanceRepository.existsByAmbulanceNumPlate(ambulanceNumPlate);
        if (!ambulanceExists) {
        throw new ResourceNotFoundException("Ambulance", "number plate", ambulanceNumPlate);
        }
        CallsAmbulance call = callsAmbulanceRepository.findById(callId).orElseThrow(
                ()-> new ResourceNotFoundException("Call for Ambulance", "id", callId));
        return convertToDTO(call);
    }

                                    // AMBULANCE CALL BY USER
    
    @Transactional
    @Override
    public CallsAmbulanceDTO makeACallForAmbulance(CallsAmbulanceDTO call){

        User calledByUser = userRepository.findById(call.getCalledByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", call.getCalledByUserId()));

        Ambulance callForAmbulance = ambulanceRepository.findById(call.getAmbulanceNumberplate()).orElseThrow(()-> new ResourceNotFoundException("Ambulance", "numberplate", call.getAmbulanceNumberplate()));

        CallsAmbulanceId callId = new CallsAmbulanceId(call.getCalledByUserId(), call.getAmbulanceNumberplate());

        CallsAmbulance newCall = convertToEntity(call);
        newCall.setId(callId);
        newCall.setCalledByUser(calledByUser);
        newCall.setCallForAmbulance(callForAmbulance);

        newCall = callsAmbulanceRepository.save(newCall);

        return convertToDTO(newCall);
    }




                                        // HELPER METHODS

                                    // CONVERT ENTITY TO DTO 

    private CallsAmbulanceDTO convertToDTO(CallsAmbulance callsAmbulance){
        CallsAmbulanceId callId = callsAmbulance.getId();
        CallsAmbulanceDTO callsAmbulanceDTO = callsAmbulanceMapper.map(callsAmbulance, CallsAmbulanceDTO.class);
        callsAmbulanceDTO.setCalledByUserId(callId.getPersonId());
        callsAmbulanceDTO.setAmbulanceNumberplate(callId.getAmbulanceNumPlate());
        return callsAmbulanceDTO;
    }

                                    // CONVERT DTO TO ENTITY

    private CallsAmbulance convertToEntity(CallsAmbulanceDTO callsAmbulanceDTO){
        return callsAmbulanceMapper.map(callsAmbulanceDTO, CallsAmbulance.class);
    }
    
    

}
