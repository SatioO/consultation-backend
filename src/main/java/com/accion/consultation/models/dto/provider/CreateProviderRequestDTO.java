package com.accion.consultation.models.dto.provider;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.dto.AddressDTO;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateProviderRequestDTO {
    private NameDTO name;
    private String email;
    private Instant dob;
    private AdministrativeSex administrativeSex;
    private MaritalStatus maritalStatus;
    private List<AddressDTO> addresses = new ArrayList<>();
    private Long npi;
    private String ssn;
    private int slotInMinutes = 15;
    private String timezone;

    private List<Long> specialities = new ArrayList<>();
    private String licenseNumber;
    private String stateLicenseIssued;
    private LocalDate licenseExpirationDate;
    private boolean acceptingNewPatients; // Flag to indicate availability

    // Additional Information
    private String photoUrl; // Optional: URL to practitioner's photo
    private String bio; // Optional: Brief biography
    private String websiteUrl; // Optional: Link to practitioner's website
}
