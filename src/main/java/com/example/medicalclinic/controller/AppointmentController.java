package com.example.medicalclinic.controller;

import com.example.medicalclinic.dto.AppointmentDto;
import com.example.medicalclinic.exception.AppointmentAlreadyExistsException;
import com.example.medicalclinic.exception.AppointmentNotFoundException;
import com.example.medicalclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/med/appointments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAppointments() {
        List<AppointmentDto> appointments = service.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable Long appointmentId) throws AppointmentNotFoundException {
        return ResponseEntity.ok(service.getAppointmentById(appointmentId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentDto appointmentDto) {
        try {
            service.createAppointment(appointmentDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (AppointmentAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long appointmentId, @RequestBody AppointmentDto appointmentDto) {
        try {
            AppointmentDto updateAppointment = service.updateAppointment(appointmentId, appointmentDto);
            return ResponseEntity.ok(updateAppointment);
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long appointmentId) {
        try {
            service.deleteAppointment(appointmentId);
            return ResponseEntity.ok("Delete Appointment with ID number: " + appointmentId);
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
