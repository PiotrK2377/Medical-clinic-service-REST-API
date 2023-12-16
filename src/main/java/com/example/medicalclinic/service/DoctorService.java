package com.example.medicalclinic.service;

import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.exception.DoctorAlreadyExistsException;
import com.example.medicalclinic.exception.DoctorNotFoundException;
import com.example.medicalclinic.mapper.DoctorMapper;
import com.example.medicalclinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteDoctor(final Long doctorId) throws DoctorNotFoundException {
        try {
            doctorRepository.deleteById(doctorId);
        } catch (Exception exception) {
            throw new DoctorNotFoundException();
        }
    }
}
