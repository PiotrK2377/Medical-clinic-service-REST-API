package com.example.medicalclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class DoctorDto {

    private Long id;
    private String name;
    private String lastname;
    private String specialization;
    private String numberPWZ;
    private List<AppointmentDto> appointmentDtos;
    private List<ReviewDto> reviewDtos;

    public DoctorDto(Long id, String name, String lastname, String specialization, String numberPWZ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.specialization = specialization;
        this.numberPWZ = numberPWZ;
    }
}
