package com.accion.consultation.entities;

import com.accion.consultation.models.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserAddressEntity {
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
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

