package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.UserDto;
import com.booking.flight.SystemOfFlightApplication.entity.User;
import com.booking.flight.SystemOfFlightApplication.enums.Role;
import com.booking.flight.SystemOfFlightApplication.repository.UserRepository;
import com.booking.flight.SystemOfFlightApplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        return "User created successfully";    }

    @Override
    public String updateUser(Long id, UserDto userDto) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        modelMapper.map(userDto, user);
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        userRepository.save(user);
        return "User updated successfully";    }

    @Override
    public String deleteUser(Long id) throws Exception {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            throw new Exception("id'li kullanıcı bulunamadı!!");
        }
        userRepository.deleteById(id);
        return "Deleted!";
    }

    @Override
    public UserDto getUserById(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found."));
        return modelMapper.map(user, UserDto.class);    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());    }

    @Override
    public List<UserDto> getUsersByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
