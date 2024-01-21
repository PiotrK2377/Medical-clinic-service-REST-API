package com.example.medicalclinic.service;

import com.example.medicalclinic.config.AdminConfig;
import com.example.medicalclinic.domain.Appointment;
import com.example.medicalclinic.domain.Mail;
import com.example.medicalclinic.dto.AppointmentDto;
import com.example.medicalclinic.exception.AppointmentAlreadyExistsException;
import com.example.medicalclinic.exception.AppointmentNotFoundException;
import com.example.medicalclinic.mapper.AppointmentMapper;
import com.example.medicalclinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private SimpleEmailService emailService;
    private AdminConfig adminConfig;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.mapToAppointmentDtoList(appointments);
    }

    public AppointmentDto getAppointmentById(final Long appointmentId) throws AppointmentNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentNotFoundException::new);
        return appointmentMapper.mapToAppointmentDto(appointment);
    }

    public void createAppointment(final AppointmentDto appointmentDto) throws AppointmentAlreadyExistsException {
        Appointment appointment = appointmentMapper.mapToAppointment(appointmentDto);
        boolean isExisting = appointmentRepository.existsByAppointmentDate(appointment.getAppointmentDate());
        if (!isExisting) {
            appointmentRepository.save(appointment);
            sendAppointmentConfirmationEmail(appointmentDto);
        } else {
            throw new AppointmentAlreadyExistsException();
        }
    }

    public AppointmentDto updateAppointment(final Long appointmentId, final AppointmentDto appointmentDto) throws AppointmentNotFoundException {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (appointmentOptional.isPresent()) {
            Appointment existAppointment = appointmentOptional.get();
            existAppointment.setAppointmentDate(appointmentDto.getAppointmentDate());
            existAppointment.setStatus(appointmentDto.getStatus());
            Appointment updateAppointment = appointmentRepository.save(existAppointment);
            return appointmentMapper.mapToAppointmentDto(updateAppointment);
        } else {
            throw new AppointmentNotFoundException();
        }
    }

    public void deleteAppointment(final Long appointmentId) throws AppointmentNotFoundException {
        try {
            appointmentRepository.deleteById(appointmentId);
        } catch (Exception exception) {
            throw new AppointmentNotFoundException();
        }
    }

    private void sendAppointmentConfirmationEmail(AppointmentDto appointmentDto) {
        Mail mail = new Mail(
                adminConfig.getAdminMail(),
                "New Appointment Confirmation",
                "Your appointment has been successfully created. Date: " + appointmentDto.getAppointmentDate()
        );
        emailService.send(mail);
    }
}
