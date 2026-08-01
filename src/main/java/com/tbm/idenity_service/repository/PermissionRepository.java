package com.tbm.idenity_service.repository;

import com.tbm.idenity_service.entity.Permission;
import com.tbm.idenity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
