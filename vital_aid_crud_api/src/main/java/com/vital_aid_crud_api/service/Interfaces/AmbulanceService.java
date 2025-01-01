package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.Payloads.AmbulanceDTO;

public interface AmbulanceService {

    List<AmbulanceDTO> viewAllAmbulances();
    AmbulanceDTO registerAmbulance(AmbulanceDTO ambulanceDTO);
    AmbulanceDTO viewAmbulanceDetailsByNumPlate(String ambulanceNumPlate);
    AmbulanceDTO updateAmbulanceDetails(String numPlate,AmbulanceDTO ambulanceDTO);
    void deleteAmbulanceByNumPlate(String ambulanceNumPlate);
}
