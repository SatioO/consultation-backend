package com.accion.consultation.entities;

import com.accion.consultation.models.SpecialityCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "speciality")
public class SpecialityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SpecialityCategory category;

    @ManyToMany(mappedBy = "specialities")
    @ToString.Exclude
    private List<ProviderEntity> provider;

    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private List<AppointmentEntity> appointments;
}
