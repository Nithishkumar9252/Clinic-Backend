package com.homeo.clinic.service;

import com.homeo.clinic.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getByUsername(String username);

    void deleteUser(Long id);
}