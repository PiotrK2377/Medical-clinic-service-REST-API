package com.example.medicalclinic.service;

import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.UserDto;
import com.example.medicalclinic.exception.UserAlreadyExistsException;
import com.example.medicalclinic.exception.UserNotFoundException;
import com.example.medicalclinic.mapper.UserMapper;
import com.example.medicalclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToUserDtoList(users);
    }

    public UserDto getUserById(final Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    public void createUser(final UserDto userDto) throws UserAlreadyExistsException {
        User user = userMapper.mapToUser(userDto);
        boolean isExisting = userRepository.existsUserByPeselNumber(user.getPeselNumber());
        if (!isExisting) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    public UserDto updateUser(final UserDto userDto) throws UserNotFoundException {
        userRepository.findById(userDto.getId()).orElseThrow(UserNotFoundException::new);
        User user = userMapper.mapToUser(userDto);
        User updateUser = userRepository.save(user);
        return userMapper.mapToUserDto(updateUser);
    }

    public void deleteUser(final Long userId) throws UserNotFoundException {
        try {
            userRepository.deleteById(userId);
        } catch (Exception exception) {
            throw new UserNotFoundException();
        }
    }
}
