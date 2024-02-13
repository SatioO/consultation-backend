package com.accion.consultation.models.dto.patient;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.dto.NameDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class CreatePatientRequestDTO {
    private String mrn;
    private String ssn;
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
    private String licenseNumber;
    private MaritalStatus maritalStatus;
}
