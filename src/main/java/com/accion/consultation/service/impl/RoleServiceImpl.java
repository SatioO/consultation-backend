package com.accion.consultation.service.impl;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.mappers.RoleMapper;
import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.models.dto.role.UpdateRoleRequestDTO;
import com.accion.consultation.repositories.RoleRepository;
import com.accion.consultation.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<RoleEntity> getRoleById(long roleId) {
        return roleRepository.findById(roleId);
    }

    @Override
    public RoleEntity createRole(CreateRoleRequestDTO role) {
        RoleEntity entity = roleMapper.toCreateRoleEntity(role);
        return roleRepository.save(entity);
    }

    @Override
    public RoleEntity updateRole(long roleId, UpdateRoleRequestDTO role) {
        RoleEntity entity = roleMapper.toUpdateRoleEntity(role);
        return roleRepository.save(entity);
    }

    @Override
    public void deleteRole(long roleId) {
        roleRepository.deleteById(roleId);
    }
}
