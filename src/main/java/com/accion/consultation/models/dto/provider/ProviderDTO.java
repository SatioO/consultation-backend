package com.accion.consultation.models.dto.provider;

import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.user.UserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProviderDTO extends UserDTO {
    private Long npi;
    private String ssn;
    private int slotInMinutes = 15;
    private String timezone;

    private List<SpecialityDTO> specialities;
    private String licenseNumber;
    private String stateLicenseIssued;
    private LocalDate licenseExpirationDate;
    private boolean acceptingNewPatients; // Flag to indicate availability

    // Additional Information
    private String photoUrl; // Optional: URL to practitioner's photo
    private String bio; // Optional: Brief biography
    private String websiteUrl; // Optional: Link to practitioner's website
}
