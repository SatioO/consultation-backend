package com.accion.consultation.models.dto.patient;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.dto.NameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
public class CreatePatientRequestDTO {
    private String mrn;
    private String ssn;
    private NameDTO name;
    private String email;
    private String password;
    private Instant dob;
    private AdministrativeSex sex;
    private String licenseNumber;
    private MaritalStatus maritalStatus;
}
