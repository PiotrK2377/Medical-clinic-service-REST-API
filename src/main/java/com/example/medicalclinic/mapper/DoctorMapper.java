package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.dto.DoctorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorMapper {

    public Doctor mapToDoctor(DoctorDto doctorDto) {
        return new Doctor(
                doctorDto.getId(),
                doctorDto.getName(),
                doctorDto.getLastname(),
                doctorDto.getSpecialization(),
                doctorDto.getNumberPWZ()
        );
    }

    public DoctorDto mapToDoctorDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getLastname(),
                doctor.getSpecialization(),
                doctor.getNumberPWZ()
        );
    }

    public List<DoctorDto> mapToDoctorDtoList(List<Doctor> doctorList) {
        return doctorList.stream()
                .map(this::mapToDoctorDto)
                .collect(Collectors.toList());
    }

    public List<Doctor> mapToDoctorList(List<DoctorDto> doctorDtoList) {
        return doctorDtoList.stream()
                .map(this::mapToDoctor)
                .collect(Collectors.toList());
    }
}
