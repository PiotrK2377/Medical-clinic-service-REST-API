package com.example.medicalclinic.dto;

import com.example.medicalclinic.enumclass.DoctorSpecialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long id;
    private String name;
    private String lastname;
    private DoctorSpecialization specialization;
    private String numberPWZ;
    private List<AppointmentDto> appointmentDtos;
    private List<ReviewDto> reviewDtos;

    public DoctorDto(Long id, String name, String lastname, DoctorSpecialization specialization, String numberPWZ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.specialization = specialization;
        this.numberPWZ = numberPWZ;
    }
}
