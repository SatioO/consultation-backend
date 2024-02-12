package com.accion.consultation.entities;

import com.accion.consultation.models.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient_address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private AddressType type;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String zip;
    private String country;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;
}

