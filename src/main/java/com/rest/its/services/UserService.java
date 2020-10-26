package com.rest.its.services;

import com.rest.its.dto.UserDto;
import com.rest.its.entities.User;
import com.rest.its.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(int userId) throws NotFoundException;
    User addUser(User user);
    void deleteUser(int userId);
    User saveUser(User user) throws NotFoundException;
}
