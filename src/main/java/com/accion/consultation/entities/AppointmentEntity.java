package com.accion.consultation.entities;

import com.accion.consultation.models.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "appointment")
@EqualsAndHashCode(callSuper = false)
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ZonedDateTime dateTime;

    @Column(nullable = false)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderEntity provider;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private SpecialityEntity speciality;
}
