package com.vital_aid_crud_api.service.ImplementationClasses;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.Entity.Admin;
import com.vital_aid_crud_api.Entity.Ambulance;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.AmbulanceDTO;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.AmbulanceRepository;
import com.vital_aid_crud_api.service.Interfaces.AmbulanceService;

import jakarta.transaction.Transactional;

@Service
public class AmbulanceServiceImpl implements AmbulanceService{

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    @Autowired
    private ModelMapper ambulanceMapper;

    @Autowired
    private AdminRepository adminRepository;
            
                                                    // VIEWING ALL AMBULANCES LIST

    @Transactional
    @Override
    public List<AmbulanceDTO> viewAllAmbulances(){
        List<Ambulance> ambulanceList = ambulanceRepository.findAll();
        return ambulanceList.stream().map(this::convertToDTO).toList();
    }

                                                    // REGISTERING A NEW AMBULANCE

    @Transactional
    @Override
    public AmbulanceDTO registerAmbulance(AmbulanceDTO ambulanceDTO){
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        Ambulance ambulance = convertToEntity(ambulanceDTO);
        ambulance.setAmbulanceManagedBy(admin);
        ambulance.setAmbulanceNumPlate(ambulanceDTO.getId());

        ambulanceRepository.save(ambulance);
        return convertToDTO(ambulance);
    }

                                                // VIEWING AMBULANCE DETAILS BY NUMBER PLATE
                                        
    @Transactional
    @Override
    public AmbulanceDTO viewAmbulanceDetailsByNumPlate(String ambulanceNumPlate){
        Ambulance ambulance = ambulanceRepository.findById(ambulanceNumPlate)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance", "number plate", ambulanceNumPlate));
        AmbulanceDTO ambulanceDTO = convertToDTO(ambulance);
        return ambulanceDTO;
    }

                                                // UPDATING AMBULANCE DETAILS

    @Transactional
    @Override
    public AmbulanceDTO updateAmbulanceDetails(String numPlate, AmbulanceDTO ambulanceDTO){
        Ambulance ambulance = ambulanceRepository.findById(numPlate)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance", "number plate", numPlate));
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        ambulance.setAmbulanceNumPlate(ambulanceDTO.getId());
        ambulance.setAmbulanceDriverContact(ambulanceDTO.getAmbulanceDriverContact());
        ambulance.setAmbulanceManagedBy(admin);

        Ambulance updatedAmbulance = ambulanceRepository.save(ambulance);
        AmbulanceDTO updatedAmbulanceDTO = convertToDTO(updatedAmbulance);
        return updatedAmbulanceDTO;
    }

                                                // DELETING AMBULANCE BY NUMBER PLATE

    @Transactional
    @Override
    public void deleteAmbulanceByNumPlate(String ambulanceNumPlate){
        Ambulance ambulance = ambulanceRepository.findById(ambulanceNumPlate)
                .orElseThrow(() -> new ResourceNotFoundException("Ambulance", "number plate", ambulanceNumPlate));
        Admin admin = ambulance.getAmbulanceManagedBy();
        if(admin != null){
            admin.getAmbulances().remove(ambulance);
            adminRepository.save(admin);
        }
        
        ambulanceRepository.delete(ambulance);
    }




                                    // Helper method to convert AmbulanceDTO to Ambulance
    private Ambulance convertToEntity(AmbulanceDTO ambulanceDTO){
        Ambulance ambulance = ambulanceMapper.map(ambulanceDTO, Ambulance.class);
        ambulance.setAmbulanceNumPlate(ambulanceDTO.getId());
        return ambulance;
    }

                                    // Helper method to convert Ambulance to AmbulanceDTO
    private AmbulanceDTO convertToDTO(Ambulance ambulance){
        AmbulanceDTO ambulanceDTO = ambulanceMapper.map(ambulance, AmbulanceDTO.class);
        ambulanceDTO.setId(ambulance.getAmbulanceNumPlate());
        return ambulanceDTO;
    }

}
