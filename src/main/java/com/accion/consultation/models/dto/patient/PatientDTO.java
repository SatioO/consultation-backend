package com.accion.consultation.models.dto.patient;

import com.accion.consultation.models.YesOrNoIndicator;
import com.accion.consultation.models.dto.user.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class PatientDTO extends UserDTO {
    private String mrn;
    private String ssn;
    private String licenseNumber;
    private YesOrNoIndicator is_deceased;
    private Instant deceased_on;
}