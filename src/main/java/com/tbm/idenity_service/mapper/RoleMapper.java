package com.tbm.idenity_service.mapper;

import com.tbm.idenity_service.dto.request.PermissionRequest;
import com.tbm.idenity_service.dto.request.RoleRequest;
import com.tbm.idenity_service.dto.response.PermissionResponse;
import com.tbm.idenity_service.dto.response.RoleResponse;
import com.tbm.idenity_service.entity.Permission;
import com.tbm.idenity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}

