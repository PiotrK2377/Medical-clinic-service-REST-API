package com.example.medicalclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private UserDto userDto;
    private DoctorDto doctorDto;
    private int rating;
    private String comment;
}
