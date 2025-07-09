package com.code.ecom.service;

import com.code.ecom.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    Optional<User> getUserById(Long id);
}
