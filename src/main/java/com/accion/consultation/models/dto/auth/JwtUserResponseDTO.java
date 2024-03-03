package com.accion.consultation.models.dto.auth;

import com.accion.consultation.enums.RoleEnum;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import lombok.Data;

import java.util.List;


@Data
public class JwtUserResponseDTO {
    private long userId;
    private String username;
    private List<String> roles;
    private NameDTO name;
    private String email;
    private UserStatus status;
}
