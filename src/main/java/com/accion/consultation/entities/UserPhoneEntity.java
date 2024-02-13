package com.accion.consultation.entities;

import com.accion.consultation.models.EquipmentType;
import com.accion.consultation.models.PhoneUseCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_contact")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private EquipmentType equipmentType;
    private PhoneUseCode type;
    private String extension;
    private String phoneNumber;
    private String unformattedPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
