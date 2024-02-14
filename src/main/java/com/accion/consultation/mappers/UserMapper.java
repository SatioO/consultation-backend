package com.accion.consultation.mappers;

import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.models.CustomUserDetails;
import com.accion.consultation.models.dto.NameDTO;
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
        return null;
    }

    @Override
    public UserDTO toModel(UserEntity entity) {
        return null;
    }
}
