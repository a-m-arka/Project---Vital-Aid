package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.Payloads.AppointmentDTO;

public interface AppointmentService {
    public List<AppointmentDTO> getAllAppointments(); // get all appointments
    public AppointmentDTO getAppointmentByToken(Long appointmentToken); // get appointment by token
    public AppointmentDTO makeAppointment(Long doctorId, AppointmentDTO appointment); // make an appointment
    public List<AppointmentDTO> getUserAppointments(); // get all appointments of a user
    public List<AppointmentDTO> getDoctorAppointments(Long doctorId); // get all appointments of a doctor
    
    // public void deleteAppointmentById(Long appointmentToken);
    // public Appointment updateAppointmentById(Long appointment_token, Appointment appointment);
}
