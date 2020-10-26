package com.rest.its.controllers;

import com.rest.its.dto.UserDto;
import com.rest.its.entities.User;
import com.rest.its.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/its")
public class UserController {
    @Autowired
    UserService userService;
    
    // Allows to add a user
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Allows to delete an existing user
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable(value = "id") int userId) {
        userService.deleteUser(userId);
    }
    
    //Allows to display all the users
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////    
    //Allows to find a user using user id
    @GetMapping("/users/{id}")
    public UserDto getUser(@PathVariable(value = "id") int userId) {
        return userService.getUserById(userId);
    }
    
    //Allows to update a user
    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////    
}
