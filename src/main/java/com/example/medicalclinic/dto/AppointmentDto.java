package com.example.medicalclinic.dto;

import com.example.medicalclinic.enumclass.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long id;
    private UserDto userDto;
    private DoctorDto doctorDto;
    private Date appointmentDate;
    private AppointmentStatus status;
}
