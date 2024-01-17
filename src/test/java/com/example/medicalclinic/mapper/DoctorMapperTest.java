package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.enumclass.DoctorSpecialization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DoctorMapperTest {

    @InjectMocks
    private DoctorMapper mapper;

    @Test
    void testMapToDoctor() {
        //Given
        DoctorDto doctorDto = DoctorDto.builder()
                .id(1L)
                .name("Jack")
                .lastname("Kramer")
                .specialization(DoctorSpecialization.NEUROLOGY)
                .numberPWZ("123490")
                .build();

        //When
        Doctor doctor = mapper.mapToDoctor(doctorDto);

        //Then
        assertEquals(doctorDto.getId(), doctor.getId());
        assertEquals(doctorDto.getName(), doctor.getName());
        assertEquals(doctorDto.getSpecialization(), doctor.getSpecialization());
        assertEquals(doctorDto.getNumberPWZ(), doctor.getNumberPWZ());
    }

    @Test
    void testMapToDoctorDto() {
        //Given
        Doctor doctor = Doctor.builder()
                .id(1L)
                .name("Jack")
                .lastname("Kramer")
                .specialization(DoctorSpecialization.DERMATOLOGY)
                .numberPWZ("890123")
                .build();

        //When
        DoctorDto doctorDto = mapper.mapToDoctorDto(doctor);

        //Then
        assertEquals(doctor.getId(), doctorDto.getId());
        assertEquals(doctor.getName(), doctorDto.getName());
        assertEquals(doctor.getLastname(), doctorDto.getLastname());
        assertEquals(doctor.getSpecialization(), doctorDto.getSpecialization());
        assertEquals(doctor.getNumberPWZ(), doctorDto.getNumberPWZ());
    }

    @Test
    void testMapToDoctorDtoList() {
        //Given
        Doctor doctor1 = Doctor.builder()
                .id(1L)
                .name("Sam")
                .lastname("Rock")
                .specialization(DoctorSpecialization.CARDIOLOGY)
                .numberPWZ("123789")
                .build();

        Doctor doctor2 = Doctor.builder()
                .id(2L)
                .name("Steve")
                .lastname("McDowel")
                .specialization(DoctorSpecialization.ONCOLOGY)
                .numberPWZ("456231")
                .build();

        List<Doctor> doctorList = Arrays.asList(doctor1, doctor2);

        //When
        List<DoctorDto> doctorDtoList = mapper.mapToDoctorDtoList(doctorList);

        //Then
        assertEquals(doctorList.size(), doctorDtoList.size());
        assertEquals(doctorList.get(0).getName(), doctorDtoList.get(0).getName());
        assertEquals(doctorList.get(1).getSpecialization(), doctorDtoList.get(1).getSpecialization());
    }

    @Test
    void testMapToDoctorList() {
        //Given
        DoctorDto doctorDto1 = DoctorDto.builder()
                .id(1L)
                .name("Joe")
                .lastname("Slow")
                .specialization(DoctorSpecialization.DERMATOLOGY)
                .numberPWZ("452389")
                .build();

        DoctorDto doctorDto2 = DoctorDto.builder()
                .id(2L)
                .name("Will")
                .lastname("Busch")
                .specialization(DoctorSpecialization.ENDOCRINOLOGY)
                .numberPWZ("901245")
                .build();

        List<DoctorDto> doctorDtoList = Arrays.asList(doctorDto1, doctorDto2);

        //When
        List<Doctor> doctorList = mapper.mapToDoctorList(doctorDtoList);

        //Then
        assertEquals(doctorDtoList.size(), doctorList.size());
        assertEquals(doctorDtoList.get(0).getLastname(), doctorList.get(0).getLastname());
        assertEquals(doctorDtoList.get(1).getNumberPWZ(), doctorList.get(1).getNumberPWZ());
    }
}