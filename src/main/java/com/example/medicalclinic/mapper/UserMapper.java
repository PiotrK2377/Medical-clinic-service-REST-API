package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastname(),
                userDto.getPeselNumber(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );
    }
    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getPeselNumber(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToUserList(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }
}
