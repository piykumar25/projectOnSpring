package com.code.ecom.service;

import com.code.ecom.dto.UserRequest;
import com.code.ecom.dto.UserResponse;
import com.code.ecom.mapper.AddressMapper;
import com.code.ecom.mapper.UserMapper;
import com.code.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::mapToUserResponse).toList();
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        return userMapper.mapToUserResponse(userRepository.save(userMapper.toUser(userRequest)));
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::mapToUserResponse);
    }

    @Override
    public Optional<UserResponse> updateUser(Long id, UserRequest userRequest) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    userMapper.updateUserFromUserRequest(userRequest, existingUser);
                    if (userRequest.getAddress() != null) {
                        existingUser.setAddress(addressMapper.toAddress(userRequest.getAddress()));
                    }
                    return userMapper.mapToUserResponse(userRepository.save(existingUser));
                });
    }
}
