package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.domain.Review;
import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.dto.ReviewDto;
import com.example.medicalclinic.dto.UserDto;
import com.example.medicalclinic.enumclass.DoctorSpecialization;
import com.example.medicalclinic.enumclass.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReviewMapperTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private ReviewMapper reviewMapper;

    private User user;
    private Doctor doctor;
    private UserDto userDto;
    private DoctorDto doctorDto;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1L)
                .name("John")
                .lastname("Doe")
                .peselNumber("123456779")
                .email("john@example.com")
                .phoneNumber("1234560897")
                .build();

        userDto = UserDto.builder()
                .id(1L)
                .name("Johnny")
                .lastname("Drake")
                .peselNumber("9876543321")
                .email("johnny@example.com")
                .phoneNumber("345789120")
                .build();

        doctor = Doctor.builder()
                .id(1L)
                .name("Frank")
                .lastname("Dren")
                .specialization(DoctorSpecialization.ENDOCRINOLOGY)
                .numberPWZ("123789")
                .build();

        doctorDto = DoctorDto.builder()
                .id(1L)
                .name("Peter")
                .lastname("Green")
                .specialization(DoctorSpecialization.DERMATOLOGY)
                .numberPWZ("890456")
                .build();
    }

    @Test
    void testMapToReview() {
        //Given
        ReviewDto reviewDto = ReviewDto.builder()
                .id(1L)
                .userDto(userDto)
                .doctorDto(doctorDto)
                .rating(Rating.FOUR_STAR)
                .comment("Nice doctor")
                .build();

        //When
        Review review = reviewMapper.mapToReview(reviewDto);

        //Then
        assertEquals(reviewDto.getId(), review.getId());
        assertEquals(reviewDto.getRating(), review.getRating());
        assertEquals(reviewDto.getComment(), review.getComment());
    }

    @Test
    void testMapToReviewDto() {
        //Given
        Review review = Review.builder()
                .id(1L)
                .user(user)
                .doctor(doctor)
                .rating(Rating.FIVE_STAR)
                .comment("The best doctor ever !!")
                .build();

        //When
        ReviewDto reviewDto = reviewMapper.mapToReviewDto(review);

        //Then
        assertEquals(review.getId(), reviewDto.getId());
        assertEquals(review.getRating(), reviewDto.getRating());
        assertEquals(review.getComment(), reviewDto.getComment());
    }

    @Test
    void testMapToReviewDtoList() {
        //Given
        Review review1 = Review.builder()
                .id(1L)
                .user(user)
                .doctor(doctor)
                .rating(Rating.THREE_STAR)
                .comment("Not to bad")
                .build();

        Review review2 = Review.builder()
                .id(2L)
                .user(user)
                .doctor(doctor)
                .rating(Rating.ONE_STAR)
                .comment("Never again")
                .build();

        List<Review> reviewList = Arrays.asList(review1, review2);

        //When
        List<ReviewDto> reviewDtoList = reviewMapper.mapToReviewDtoList(reviewList);

        //Then
        assertEquals(reviewList.size(), reviewDtoList.size());
        assertEquals(reviewList.get(0).getId(), reviewDtoList.get(0).getId());
        assertEquals(reviewList.get(1).getRating(), reviewDtoList.get(1).getRating());
    }
}