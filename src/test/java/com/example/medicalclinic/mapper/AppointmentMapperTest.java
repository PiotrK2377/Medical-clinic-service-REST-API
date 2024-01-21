package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.Appointment;
import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.AppointmentDto;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.dto.UserDto;
import com.example.medicalclinic.enumclass.AppointmentStatus;
import com.example.medicalclinic.enumclass.DoctorSpecialization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AppointmentMapperTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private DoctorMapper doctorMapper;

    @InjectMocks
    private AppointmentMapper appointmentMapper;

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
    void testMapToAppointment() {
        //Given
        LocalDate localDate = LocalDate.now();
        AppointmentDto appointmentDto = AppointmentDto.builder()
                .id(1L)
                .userDto(userDto)
                .doctorDto(doctorDto)
                .appointmentDate(localDate)
                .status(AppointmentStatus.CONFIRMED)
                .build();

        //When
        Appointment appointment = appointmentMapper.mapToAppointment(appointmentDto);

        //Then
        assertEquals(appointmentDto.getId(), appointment.getId());
        assertEquals(appointmentDto.getAppointmentDate(), appointment.getAppointmentDate());
        assertEquals(appointmentDto.getStatus(), appointment.getStatus());

    }

    @Test
    void testMapToAppointmentDto() {
        //Given
        LocalDate localDate = LocalDate.now();
        Appointment appointment = Appointment.builder()
                .id(1L)
                .user(user)
                .doctor(doctor)
                .appointmentDate(localDate)
                .status(AppointmentStatus.COMPLETED)
                .build();

        //When
        AppointmentDto appointmentDto = appointmentMapper.mapToAppointmentDto(appointment);

        //Then
        assertEquals(appointment.getId(), appointmentDto.getId());
        assertEquals(appointment.getAppointmentDate(), appointmentDto.getAppointmentDate());
        assertEquals(appointment.getStatus(), appointmentDto.getStatus());
    }

    @Test
    void testMapToAppointmentDtoList() {
        //Given
        LocalDate localDate = LocalDate.now();
        Appointment appointment1 = Appointment.builder()
                .id(1L)
                .user(user)
                .doctor(doctor)
                .appointmentDate(localDate)
                .status(AppointmentStatus.CONFIRMED)
                .build();

        Appointment appointment2 = Appointment.builder()
                .id(2L)
                .user(user)
                .doctor(doctor)
                .appointmentDate(localDate)
                .status(AppointmentStatus.CANCELLED)
                .build();

        List<Appointment> appointmentList = Arrays.asList(appointment1, appointment2);

        //When
        List<AppointmentDto> appointmentDtoList = appointmentMapper.mapToAppointmentDtoList(appointmentList);

        //Then
        assertEquals(appointmentList.size(), appointmentDtoList.size());
        assertEquals(appointmentList.get(0).getId(), appointmentDtoList.get(0).getId());
        assertEquals(appointmentList.get(1).getStatus(), appointmentDtoList.get(1).getStatus());
    }
}