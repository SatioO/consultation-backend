package com.accion.consultation.models.dto.admin;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class AdminDTO {
    private long userId;
    private String username;
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
    private List<String> roles;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
