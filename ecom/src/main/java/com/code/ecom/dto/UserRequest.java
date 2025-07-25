package com.code.ecom.dto;

import com.code.ecom.entity.User;
import com.code.ecom.enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private AddressDto address;

//    public static User mapToUser(UserRequest userRequest) {
//        User user = new User();
//        user.setFirstName(userRequest.getFirstName());
//        user.setLastName(userRequest.getLastName());
//        user.setEmail(userRequest.getEmail());
//        user.setPhone(userRequest.getPhone());
//        user.setRole(UserRole.CUSTOMER);
//        return user;
//    }
//
//    public static User updateUserFromUserRequest(User user, UserRequest userRequest) {
//        user.setFirstName(userRequest.getFirstName());
//        user.setLastName(userRequest.getLastName());
//        user.setEmail(userRequest.getEmail());
//        user.setPhone(userRequest.getPhone());
//        if (userRequest.getAddress() != null) {
//            user.setAddress(AddressDto.mapToAddress(user.getAddress(), userRequest.getAddress()));
//        }
//        return user;
//    }
}
