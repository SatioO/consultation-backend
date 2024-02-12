package com.accion.consultation.models.dto.patient;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.PatientStatus;
import com.accion.consultation.models.YesOrNoIndicator;
import com.accion.consultation.models.dto.NameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
    private long id;
    private String mrn;
    private String ssn;
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex sex;
    private String licenseNumber;
    private MaritalStatus maritalStatus;
    private YesOrNoIndicator is_deceased;
    private Instant deceased_on;
    private PatientStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
