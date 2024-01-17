package com.example.medicalclinic.mapper;

import com.example.medicalclinic.domain.User;
import com.example.medicalclinic.dto.UserDto;
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
class UserMapperTest {

    @InjectMocks
    private UserMapper mapper;
    @Test
    void testMapToUser() {
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("John")
                .lastname("Doe")
                .peselNumber("123456789")
                .email("john@example.com")
                .phoneNumber("234890156")
                .build();

        //When
        User user = mapper.mapToUser(userDto);

        //Then
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getLastname(), user.getLastname());
        assertEquals(userDto.getPeselNumber(), user.getPeselNumber());
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getPhoneNumber(), user.getPhoneNumber());
    }

    @Test
    void testMapToUserDto() {
        //Given
        User user = User.builder()
                .id(1L)
                .name("Johnny")
                .lastname("Wood")
                .peselNumber("123456789")
                .email("johnny@example.com")
                .phoneNumber("098765432")
                .build();

        // When
        UserDto userDto = mapper.mapToUserDto(user);

        //Then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getLastname(), userDto.getLastname());
        assertEquals(user.getPeselNumber(), userDto.getPeselNumber());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPhoneNumber(), userDto.getPhoneNumber());
    }

    @Test
    void testMapToUserDtoList() {
        // Given
        User user1 = User.builder()
                .id(1L)
                .name("John")
                .lastname("Doe")
                .peselNumber("123456789")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        User user2 = User.builder()
                .id(2L)
                .name("Jane")
                .lastname("Doe")
                .peselNumber("987654321")
                .email("jane@example.com")
                .phoneNumber("987654321")
                .build();

        List<User> userList = Arrays.asList(user1, user2);

        // When
        List<UserDto> userDtoList = mapper.mapToUserDtoList(userList);

        // Then
        assertEquals(userList.size(), userDtoList.size());
        assertEquals(userList.get(0).getName(), userDtoList.get(0).getName());
        assertEquals(userList.get(1).getEmail(), userDtoList.get(1).getEmail());
    }

    @Test
    void testMapToUserList() {
        // Given
        UserDto userDto1 = UserDto.builder()
                .id(1L)
                .name("John")
                .lastname("Doe")
                .peselNumber("123456789")
                .email("john@example.com")
                .phoneNumber("123456789")
                .build();

        UserDto userDto2 = UserDto.builder()
                .id(2L)
                .name("Jane")
                .lastname("Doe")
                .peselNumber("987654321")
                .email("jane@example.com")
                .phoneNumber("987654321")
                .build();

        List<UserDto> userDtoList = Arrays.asList(userDto1, userDto2);

        // When
        List<User> userList = mapper.mapToUserList(userDtoList);

        // Then
        assertEquals(userDtoList.size(), userList.size());
        assertEquals(userDtoList.get(0).getName(), userList.get(0).getName());
        assertEquals(userDtoList.get(1).getEmail(), userList.get(1).getEmail());
    }
}