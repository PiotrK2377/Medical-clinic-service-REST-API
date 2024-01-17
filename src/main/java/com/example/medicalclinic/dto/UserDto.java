package com.example.medicalclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String peselNumber;
    private String email;
    private String phoneNumber;
    private List<AppointmentDto> appointmentDtos;
    private List<ReviewDto> reviewDtos;

    public UserDto(Long id, String name, String lastname,String peselNumber, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.peselNumber = peselNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
