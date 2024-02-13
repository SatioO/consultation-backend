package com.accion.consultation.models.dto.user;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.YesOrNoIndicator;
import com.accion.consultation.models.dto.AddressDTO;
import com.accion.consultation.models.dto.NameDTO;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class UserDTO {
    private long userId;
    private String username;
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
    private MaritalStatus maritalStatus;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<AddressDTO> addresses;
}
