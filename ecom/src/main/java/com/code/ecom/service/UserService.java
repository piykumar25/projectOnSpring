package com.code.ecom.service;

import com.code.ecom.dto.UserRequest;
import com.code.ecom.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse addUser(UserRequest userRequest);

    Optional<UserResponse> getUserById(Long id);

    Optional<UserResponse> updateUser(Long id, UserRequest userRequest);
}
