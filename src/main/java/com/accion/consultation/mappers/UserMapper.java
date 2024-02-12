package com.accion.consultation.mappers;

import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.models.dto.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityMapper<UserEntity, UserDTO> {
    @Override
    public UserEntity toEntity(UserDTO model) {
//        UserEntity entity = new UserEntity();
//        entity.setUsername(model.getUsername());

        return null;
    }

    @Override
    public UserDTO toModel(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole().getName());
        return dto;
    }
}
