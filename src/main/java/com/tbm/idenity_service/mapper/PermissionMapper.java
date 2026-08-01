package com.tbm.idenity_service.mapper;

import com.tbm.idenity_service.dto.request.PermissionRequest;
import com.tbm.idenity_service.dto.request.UserCreationRequest;
import com.tbm.idenity_service.dto.request.UserUpdateRequest;
import com.tbm.idenity_service.dto.response.PermissionResponse;
import com.tbm.idenity_service.dto.response.UserResponse;
import com.tbm.idenity_service.entity.Permission;
import com.tbm.idenity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}

