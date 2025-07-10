package com.code.ecom.service;

import com.code.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
        return user;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return getUserById(id).map(existingUser -> {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            return existingUser;
        });
    }
}
