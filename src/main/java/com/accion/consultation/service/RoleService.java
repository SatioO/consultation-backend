package com.accion.consultation.service;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.models.dto.role.UpdateRoleRequestDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleEntity> getRoles();
    Optional<RoleEntity> getRoleById(long roleId);
    RoleEntity createRole(CreateRoleRequestDTO role);
    RoleEntity updateRole(long roleId, UpdateRoleRequestDTO role);
    void deleteRole(long roleId);
}
