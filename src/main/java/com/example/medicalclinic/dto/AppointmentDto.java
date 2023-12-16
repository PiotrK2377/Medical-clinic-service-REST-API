package com.example.medicalclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long id;
    private UserDto userDto;
    private DoctorDto doctorDto;
    private Date appointmentDate;
    private String status;
}
