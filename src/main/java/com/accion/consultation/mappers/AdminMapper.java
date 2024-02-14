package com.accion.consultation.mappers;

import com.accion.consultation.entities.PersonName;
import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.admin.UpdateAdminRequestDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AdminMapper implements EntityMapper<UserEntity, AdminDTO> {
    private final RoleMapper roleMapper;

    public AdminMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserEntity toEntity(AdminDTO model) {
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
        entity.setStatus(UserStatus.ACTIVE);

        return entity;
    }

    @Override
    public AdminDTO toModel(UserEntity entity) {
        NameDTO personName = new NameDTO();
        personName.setGivenName(entity.getName().getGivenName());
        personName.setMiddleName(entity.getName().getMiddleName());
        personName.setFamilyName(entity.getName().getFamilyName());

        AdminDTO model = new AdminDTO();
        model.setUserId(entity.getUserId());
        model.setUsername(entity.getEmail());
        model.setName(personName);
        model.setEmail(entity.getEmail());
        model.setDob(entity.getDob());
        model.setAdministrativeSex(entity.getAdministrativeSex());
        model.setStatus(entity.getStatus());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());
        return model;
    }

    public UserEntity toCreateAdminEntity(CreateAdminRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        UserEntity entity = new UserEntity();
        entity.setUsername(model.getEmail());
        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setStatus(UserStatus.ACTIVE);

        return entity;
    }

    public UserEntity toUpdateAdminEntity(UserEntity entity, UpdateAdminRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setAdministrativeSex(model.getAdministrativeSex());

        return entity;
    }
}
