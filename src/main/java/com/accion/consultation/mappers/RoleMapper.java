package com.accion.consultation.mappers;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.models.dto.role.RoleDTO;
import com.accion.consultation.models.dto.role.UpdateRoleRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements EntityMapper<RoleEntity, RoleDTO> {
    @Override
    public RoleEntity toEntity(RoleDTO model) {
        RoleEntity entity = new RoleEntity();
        entity.setName(model.getName());

        return entity;
    }

    @Override
    public RoleDTO toModel(RoleEntity entity) {
        RoleDTO model = new RoleDTO();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }

    public RoleEntity toCreateRoleEntity(CreateRoleRequestDTO model) {
        RoleEntity entity = new RoleEntity();
        entity.setName(model.getName());
        return entity;
    }

    public RoleEntity toUpdateRoleEntity(UpdateRoleRequestDTO model) {
        RoleEntity entity = new RoleEntity();
        entity.setName(model.getName());
        return entity;
    }
}
