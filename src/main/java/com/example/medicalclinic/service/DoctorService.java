package com.example.medicalclinic.service;

import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.exception.DoctorAlreadyExistsException;
import com.example.medicalclinic.exception.DoctorNotFoundException;
import com.example.medicalclinic.mapper.DoctorMapper;
import com.example.medicalclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.mapToDoctorDtoList(doctors);
    }

    public DoctorDto getDoctorById(final Long doctorId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(DoctorNotFoundException::new);
        return doctorMapper.mapToDoctorDto(doctor);
    }

    public void createDoctor(final DoctorDto doctorDto) throws DoctorAlreadyExistsException {
        Doctor doctor = doctorMapper.mapToDoctor(doctorDto);
        boolean isExisting = doctorRepository.existsDoctorByNumberPWZ(doctor.getNumberPWZ());
        if (!isExisting) {
            doctorRepository.save(doctor);
        } else {
            throw new DoctorAlreadyExistsException();
        }
    }

    public DoctorDto updateDoctor(final Long doctorId, final DoctorDto doctorDto) throws DoctorNotFoundException {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isPresent()) {
            Doctor existDoctor = doctorOptional.get();
            existDoctor.setName(doctorDto.getName());
            existDoctor.setLastname(doctorDto.getLastname());
            existDoctor.setSpecialization(doctorDto.getSpecialization());
            existDoctor.setNumberPWZ(doctorDto.getNumberPWZ());
            Doctor updateDoctor = doctorRepository.save(existDoctor);
            return doctorMapper.mapToDoctorDto(updateDoctor);
        } else {
            throw new DoctorNotFoundException();
        }

    }

    public void deleteDoctor(final Long doctorId) throws DoctorNotFoundException {
        try {
            doctorRepository.deleteById(doctorId);
        } catch (Exception exception) {
            throw new DoctorNotFoundException();
        }
    }
}
