package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.UserDto;
import com.booking.flight.SystemOfFlightApplication.enums.Role;

import java.util.List;

public interface UserService {
    String createUser(UserDto userDto);

    String updateUser(Long id, UserDto userDto) throws Exception;

    String deleteUser(Long id) throws Exception;

    UserDto getUserById(Long id) throws Exception;

    List<UserDto> getAllUsers();

    List<UserDto> getUsersByRole(Role role);
}
