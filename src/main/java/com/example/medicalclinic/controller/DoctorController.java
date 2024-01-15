package com.example.medicalclinic.controller;


import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.exception.DoctorAlreadyExistsException;
import com.example.medicalclinic.exception.DoctorNotFoundException;
import com.example.medicalclinic.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/med/doctors")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DoctorController {

    private final DoctorService service;

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getDoctors() {
        List<DoctorDto> doctors = service.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Long doctorId) throws DoctorNotFoundException {
        return ResponseEntity.ok(service.getDoctorById(doctorId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody DoctorDto doctorDto) {
        try {
            service.createDoctor(doctorDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DoctorAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("{doctorId}")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long doctorId, @RequestBody DoctorDto doctorDto) {
        try {
            DoctorDto updateDoctor = service.updateDoctor(doctorId, doctorDto);
            return ResponseEntity.ok(updateDoctor);
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long doctorId) {
        try {
            service.deleteDoctor(doctorId);
            return ResponseEntity.ok("Delete Doctor with ID number: " + doctorId);
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
