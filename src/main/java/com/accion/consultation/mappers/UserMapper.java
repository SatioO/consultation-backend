package com.accion.consultation.mappers;

import com.accion.consultation.entities.PersonName;
import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.models.CustomUserDetails;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper implements EntityMapper<UserEntity, UserDTO> {
    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserEntity toEntity(UserDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        UserEntity entity = new UserEntity();
        entity.setUserId(model.getUserId());
        entity.setUsername(model.getEmail());
        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setStatus(model.getStatus());

        return entity;
    }

    @Override
    public UserDTO toModel(UserEntity entity) {
        NameDTO personName = new NameDTO();
        personName.setGivenName(entity.getName().getGivenName());
        personName.setMiddleName(entity.getName().getMiddleName());
        personName.setFamilyName(entity.getName().getFamilyName());

        UserDTO model = new UserDTO();
        model.setUserId(entity.getUserId());
        model.setUsername(entity.getEmail());
        model.setName(personName);
        model.setRoles(entity.getRoles().stream().map(RoleEntity::getName).toList());
        model.setEmail(entity.getEmail());
        model.setDob(entity.getDob());
        model.setAdministrativeSex(entity.getAdministrativeSex());
        model.setStatus(entity.getStatus());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }
}
