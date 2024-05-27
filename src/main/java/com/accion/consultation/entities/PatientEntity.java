package com.accion.consultation.entities;

import com.accion.consultation.models.YesOrNoIndicator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "patient")
@Data
public class PatientEntity extends UserEntity  {
    @Column(nullable = false)
    private String mrn;

    @Column(nullable = false)
    private String ssn;

    @Column
    private String licenseNumber;

    @Column(nullable = false)
    private YesOrNoIndicator is_deceased;

    @Column
    private Instant deceased_on;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;
}

