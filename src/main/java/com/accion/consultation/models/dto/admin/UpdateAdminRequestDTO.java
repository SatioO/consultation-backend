package com.accion.consultation.models.dto.admin;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.dto.NameDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class UpdateAdminRequestDTO {
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
}
