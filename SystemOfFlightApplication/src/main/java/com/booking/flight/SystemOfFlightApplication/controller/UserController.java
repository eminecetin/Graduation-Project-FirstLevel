package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.UserDto;
import com.booking.flight.SystemOfFlightApplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody UserDto userDto){
        return   ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.updateUser(id,userDto));
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) throws Exception {
        return userService.deleteUser(id);
    }
}
