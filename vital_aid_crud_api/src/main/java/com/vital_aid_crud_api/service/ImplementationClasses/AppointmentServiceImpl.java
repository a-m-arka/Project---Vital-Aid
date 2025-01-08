package com.vital_aid_crud_api.service.ImplementationClasses;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vital_aid_crud_api.Entity.Appointment;
import com.vital_aid_crud_api.Entity.Doctor;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.AppointmentDTO;
import com.vital_aid_crud_api.repository.AppointmentRepository;
import com.vital_aid_crud_api.repository.DoctorRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

                                                    // LIST OF ALL APPOINTMENTS

    @Transactional
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::AppointmentToDTO)
                .collect(Collectors.toList());
    }

                                                // VIEW APPOINTMENT DETAILS BY TOKEN

    @Transactional
    @Override
    public AppointmentDTO getAppointmentByToken(Long appointmentToken) {
        Appointment appointment = appointmentRepository.findById(appointmentToken).orElseThrow(
                () -> new ResourceNotFoundException("Appointment", "appointment token", appointmentToken));

        return AppointmentToDTO(appointment);
    }

                                                // MAKE APPOINTMENT WITH DOCTOR

    @Transactional
    @Override
    public AppointmentDTO makeAppointment(Long doctorID,AppointmentDTO appointmentDTO) {
        if (appointmentDTO.getPatientName() == null || appointmentDTO.getPatientDob() == null) {
            throw new IllegalArgumentException("Invalid appointment details.");
        }
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByPersonEmail(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", userEmail));

        Doctor doctor = doctorRepository.findById(doctorID).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctorID));


        Appointment appointment = DTOtoAppointment(appointmentDTO);
        appointment.setMadeByUser(user);
        appointment.setWithDoctor(doctor);

        appointment = appointmentRepository.save(appointment);
        return AppointmentToDTO(appointment);
    }

                                            // LIST OF ALL APPOINTMENTS OF A PARTICULAR DOCTOR

    @Transactional
    @Override
    public List<AppointmentDTO> getDoctorAppointments(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("Doctor", "id", doctorId));
        List<Appointment> appointments = doctor.getDoctorAppointments();
        return appointments.stream()
                .map(this::AppointmentToDTO)
                .collect(Collectors.toList());
    }

    // @Transactional
    // @Override
    // public void deleteAppointmentById(Long appointment_token) {
    //     Appointment appointment = appointmentRepository.findById(appointment_token)
    //             .orElseThrow(
    //                     () -> new ResourceNotFoundException("Appointment", "appointment_token", appointment_token));
    //     appointmentRepository.delete(appointment);
    // }

                                            // LIST OF ALL APPOINTMENTS OF A PARTICULAR USER

    @Transactional
    @Override
    public List<AppointmentDTO> getUserAppointments(){
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByPersonEmail(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", userEmail));
        List<Appointment> appointments = user.getMadeAppointments();
        return appointments.stream()
                .map(this::AppointmentToDTO)
                .collect(Collectors.toList());
    }

                                            // HELPER METHODS

                                    // DTO object to Entity converter

    private Appointment DTOtoAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = this.modelMapper.map(appointmentDTO, Appointment.class);
        return appointment;
    }

                                    // Entity to DTO object converter

    private AppointmentDTO AppointmentToDTO(Appointment appointment) {
        AppointmentDTO appointmentDTO = this.modelMapper.map(appointment, AppointmentDTO.class);
        String doctorName = appointment.getWithDoctor().getDoctorName();
        String userName = appointment.getMadeByUser().getPersonName();

        appointmentDTO.setAppointmentWith(doctorName);
        appointmentDTO.setAppointmentBy(userName);
        if (appointment.getPatientDob() != null) {
                int age = Period.between(appointment.getPatientDob(), LocalDate.now()).getYears();
                appointmentDTO.setPatientAge(age);
                }
        return appointmentDTO;
    }

}
