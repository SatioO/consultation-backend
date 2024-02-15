package com.accion.consultation.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "provider")
@Builder
public class ProviderEntity extends UserEntity {
    @Column(nullable = false)
    private Long npi;

    @Column(nullable = false)
    private String ssn;

    // Professional Information
    @ManyToMany
    @JoinTable(name = "provider_speciality", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private List<SpecialityEntity> speciality;

    @Column
    private String licenseNumber;

    @Column
    private String stateLicenseIssued;

    @Column
    private LocalDate licenseExpirationDate;

    @Column
    private boolean acceptingNewPatients; // Flag to indicate availability

    // Additional Information
    @Column
    private String photoUrl; // Optional: URL to practitioner's photo

    @Column
    private String bio; // Optional: Brief biography

    @Column
    private String websiteUrl; // Optional: Link to practitioner's website
}
