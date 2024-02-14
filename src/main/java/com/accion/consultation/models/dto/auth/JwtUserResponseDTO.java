package com.accion.consultation.models.dto.auth;

import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class JwtUserResponseDTO {
    private long userId;
    private String username;
    private NameDTO name;
    private String email;
    private UserStatus status;
}
