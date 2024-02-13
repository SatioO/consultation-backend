package com.accion.consultation.models.dto.patient;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.dto.AddressDTO;
import com.accion.consultation.models.dto.NameDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CreatePatientRequestDTO {
    private String mrn;
    private String ssn;
    private NameDTO name;
    @NotNull
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
    private String licenseNumber;
    private MaritalStatus maritalStatus;
    private List<AddressDTO> addresses;
}
