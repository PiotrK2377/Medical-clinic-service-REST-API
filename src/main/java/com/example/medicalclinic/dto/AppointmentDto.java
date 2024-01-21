package com.example.medicalclinic.dto;

import com.example.medicalclinic.enumclass.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long id;
    private UserDto userDto;
    private DoctorDto doctorDto;
    private LocalDate appointmentDate;
    private AppointmentStatus status;

    public AppointmentDto(LocalDate appointmentDate, AppointmentStatus status) {
        this.appointmentDate = appointmentDate;
        this.status = status;
    }
}
