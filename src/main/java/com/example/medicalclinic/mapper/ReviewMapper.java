package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.domain.Review;
import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.dto.ReviewDto;
import com.example.medicalclinic.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewMapper {

    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;

    public ReviewMapper(UserMapper userMapper, DoctorMapper doctorMapper) {
        this.userMapper = userMapper;
        this.doctorMapper = doctorMapper;
    }

    public Review mapToReview(ReviewDto reviewDto) {
        UserDto userDto = reviewDto.getUserDto();
        DoctorDto doctorDto = reviewDto.getDoctorDto();
        return new Review(
                reviewDto.getId(),
                userMapper.mapToUser(userDto),
                doctorMapper.mapToDoctor(doctorDto),
                reviewDto.getRating(),
                reviewDto.getComment()
        );
    }

    public ReviewDto mapToReview(Review review) {
        User user = review.getUser();
        Doctor doctor = review.getDoctor();
        return new ReviewDto(
                review.getId(),
                userMapper.mapToUserDto(user),
                doctorMapper.mapToDoctorDto(doctor),
                review.getRating(),
                review.getComment()
        );
    }

    public List<ReviewDto> mapToReviewDtoList(List<Review> reviewList) {
        return reviewList.stream()
                .map(this::mapToReview)
                .collect(Collectors.toList());
    }
}
