package com.tbm.idenity_service.mapper;

import com.tbm.idenity_service.dto.request.UserCreationRequest;
import com.tbm.idenity_service.dto.request.UserUpdateRequest;
import com.tbm.idenity_service.dto.response.UserResponse;
import com.tbm.idenity_service.entity.User;
import com.tbm.idenity_service.repository.UserRepository;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

