package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.Appointment;
import com.example.medicalclinic.domain.Doctor;
import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.AppointmentDto;
import com.example.medicalclinic.dto.DoctorDto;
import com.example.medicalclinic.dto.UserDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentMapper {

    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;

    public AppointmentMapper(UserMapper userMapper, DoctorMapper doctorMapper) {
        this.userMapper = userMapper;
        this.doctorMapper = doctorMapper;
    }

    public Appointment mapToAppointment(AppointmentDto appointmentDto) {
        UserDto userDto = appointmentDto.getUserDto();
        DoctorDto doctorDto = appointmentDto.getDoctorDto();
        return new Appointment(
                appointmentDto.getId(),
                userMapper.mapToUser(userDto),
                doctorMapper.mapToDoctor(doctorDto),
                appointmentDto.getAppointmentDate(),
                appointmentDto.getStatus()
        );
    }

    public AppointmentDto mapToAppointmentDto(Appointment appointment) {
        User user = appointment.getUser();
        Doctor doctor = appointment.getDoctor();
        return new AppointmentDto(
                appointment.getId(),
                userMapper.mapToUserDto(user),
                doctorMapper.mapToDoctorDto(doctor),
                appointment.getAppointmentDate(),
                appointment.getStatus()
        );
    }

    public List<AppointmentDto> mapToAppointmentDtoList(List<Appointment> appointmentList) {
        return appointmentList.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

}
