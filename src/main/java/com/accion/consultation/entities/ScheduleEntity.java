package com.accion.consultation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Instant start;

    @Column(nullable = false)
    private Instant end;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private ProviderEntity provider;
}
