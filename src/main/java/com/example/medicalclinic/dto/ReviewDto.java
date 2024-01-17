package com.example.medicalclinic.dto;

import com.example.medicalclinic.enumclass.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private UserDto userDto;
    private DoctorDto doctorDto;
    private Rating rating;
    private String comment;
}
