package com.accion.consultation.models.dto.admin;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.role.RoleDTO;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
public class AdminDTO {
    private long userId;
    private String username;
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
    private Set<RoleDTO> roles;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
