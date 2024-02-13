package com.accion.consultation.service;

import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.models.dto.role.RoleDTO;
import com.accion.consultation.models.dto.role.UpdateRoleRequestDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> getRoles();
    Optional<RoleDTO> getRoleById(long roleId);
    RoleDTO createRole(CreateRoleRequestDTO role);
    RoleDTO updateRole(long roleId, UpdateRoleRequestDTO role);
    void deleteRole(long roleId);
}
