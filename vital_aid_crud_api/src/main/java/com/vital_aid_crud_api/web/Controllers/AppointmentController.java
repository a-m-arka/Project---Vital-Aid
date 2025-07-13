package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.AppointmentDTO;
import com.vital_aid_crud_api.service.Interfaces.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;



                                                // GETTING ALL APPOINTMENTS

    @GetMapping("/allAppointments")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments(){
        List<AppointmentDTO> appointmentDTOs = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

                                                // GETTING INDIVIDUAL APPOINTMENT BY TOKEN

    @GetMapping("/viewAppointment/{appointmentToken}")
    public ResponseEntity<AppointmentDTO> viewAppointmentByToken(@PathVariable Long appointmentToken){
        AppointmentDTO appointmentDTO = appointmentService.getAppointmentByToken(appointmentToken);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

                                                // GETTING ALL APPOINTMENTs OF AN USER
    
    @GetMapping("/userAppointments")
    public ResponseEntity<List<AppointmentDTO>> viewUserAppointment(){
        List<AppointmentDTO> appointmentDTOs = appointmentService.getUserAppointments();
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

                                                // GETTING ALL APPOINTMENTS OF A DOCTOR
                            
    @GetMapping("/doctorAppointments/{Id}")
    public ResponseEntity<List<AppointmentDTO>> viewDoctorAppointments(@PathVariable Long Id){
        List<AppointmentDTO> appointmentDTOs = appointmentService.getDoctorAppointments(Id);
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

                                                // GETTING ALL APPOINTMENTS OF A DOCTOR(EMAIL)
                            
    @GetMapping("/doctorAppointments")
    public ResponseEntity<List<AppointmentDTO>> viewDoctorAppointments(){
        List<AppointmentDTO> appointmentDTOs = appointmentService.getDoctorAppointments();
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

                                                // GETTING ALL APPOINTMENTS OF A DOCTOR BY MONTH
    @GetMapping("/doctorAppointmentsByMonth/{month}")
    public ResponseEntity<List<AppointmentDTO>> viewDoctorAppointmentsByMonth(@PathVariable String month){
        List<AppointmentDTO> appointmentDTOs = appointmentService.getDoctorAppointmentsByMonth(month);
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }


                                                // MAKING AN APPOINTMENT

    @PostMapping("/makeAppointment/{Id}")
    public ResponseEntity<AppointmentDTO> makeAppointment(@PathVariable Long Id,@Valid @RequestBody AppointmentDTO appointmentDTO){
        AppointmentDTO appointment = appointmentService.makeAppointment(Id, appointmentDTO);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

                                        // LIST OF PAST APPOINTMENTS OF A DOCTOR
    @GetMapping("/doctorPastAppointments")
    public ResponseEntity<List<AppointmentDTO>> viewDoctorPastAppointments(){
        List<AppointmentDTO> appointmentDTOs = appointmentService.getDoctorPastAppointments();
        return new ResponseEntity<>(appointmentDTOs, HttpStatus.OK);
    }

                                                // DELETING AN APPOINTMENT         







                                                
                                                // UPDATING AN APPOINTMENT

}
