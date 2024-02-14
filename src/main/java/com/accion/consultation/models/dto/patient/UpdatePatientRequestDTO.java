package com.accion.consultation.models.dto.patient;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.dto.AddressDTO;
import com.accion.consultation.models.dto.NameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientRequestDTO {
    private String mrn;
    private String ssn;
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex sex;
    private String licenseNumber;
    private MaritalStatus maritalStatus;
    private List<AddressDTO> addresses = new ArrayList<>();
}
