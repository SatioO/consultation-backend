package com.accion.consultation.service.impl;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.mappers.RoleMapper;
import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.models.dto.role.RoleDTO;
import com.accion.consultation.models.dto.role.UpdateRoleRequestDTO;
import com.accion.consultation.repositories.RoleRepository;
import com.accion.consultation.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> getRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> getRoleById(long roleId) {
        return roleRepository.findById(roleId).map(roleMapper::toModel);
    }

    @Override
    public RoleDTO createRole(CreateRoleRequestDTO role) {
        RoleEntity entity = roleMapper.toCreateRoleEntity(role);
        return roleMapper.toModel(roleRepository.save(entity));
    }

    @Override
    public RoleDTO updateRole(long roleId, UpdateRoleRequestDTO role) {
        RoleEntity entity = roleMapper.toUpdateRoleEntity(role);
        return roleMapper.toModel(roleRepository.save(entity));
    }

    @Override
    public void deleteRole(long roleId) {
        roleRepository.deleteById(roleId);
    }
}
