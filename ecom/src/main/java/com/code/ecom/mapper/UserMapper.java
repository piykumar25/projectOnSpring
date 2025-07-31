package com.code.ecom.mapper;

import com.code.ecom.dto.UserRequest;
import com.code.ecom.dto.UserResponse;
import com.code.ecom.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", constant = "CUSTOMER")
    User toUser(UserRequest userRequest);


    UserResponse mapToUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateUserFromUserRequest(UserRequest userRequest, @MappingTarget User user);
}
