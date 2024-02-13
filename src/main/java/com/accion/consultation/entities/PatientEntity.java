package com.accion.consultation.entities;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.PatientStatus;
import com.accion.consultation.models.YesOrNoIndicator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "patient")
@Data
@PrimaryKeyJoinColumn(name = "patient_id")
public class PatientEntity extends UserEntity  {
    @Column(nullable = false)
    private String mrn;
    @Column(nullable = false)
    private String ssn;

    @Embedded
    private PersonName name;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Instant dob;
    @Column(nullable = false)
    private AdministrativeSex administrativeSex;

    private String licenseNumber;
    private MaritalStatus maritalStatus;
    @Column(nullable = false)
    private YesOrNoIndicator is_deceased;
    private Instant deceased_on;
    @Column(nullable = false)
    private PatientStatus status;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientAddressEntity> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PatientPhoneEntity> contactInfo = new ArrayList<>();
}

