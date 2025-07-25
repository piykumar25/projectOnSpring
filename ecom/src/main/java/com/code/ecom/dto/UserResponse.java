package com.code.ecom.dto;

import com.code.ecom.entity.User;
import com.code.ecom.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;

    private AddressDto address;

//    public static UserResponse mapToUserResponse(User user) {
//        UserResponse userResponse = new UserResponse();
//        userResponse.setId(String.valueOf(user.getId()));
//        userResponse.setFirstName(user.getFirstName());
//        userResponse.setLastName(user.getLastName());
//        userResponse.setEmail(user.getEmail());
//        userResponse.setPhone(user.getPhone());
//        userResponse.setRole(user.getRole());
//
//        if (user.getAddress() != null) {
//            AddressDto addressDto = new AddressDto();
//            addressDto.setStreet(user.getAddress().getStreet());
//            addressDto.setCity(user.getAddress().getCity());
//            addressDto.setState(user.getAddress().getState());
//            addressDto.setCountry(user.getAddress().getCountry());
//            addressDto.setZipcode(user.getAddress().getZipcode());
//            userResponse.setAddress(addressDto);
//        }
//        return userResponse;
//    }
}
