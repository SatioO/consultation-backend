package com.accion.consultation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "provider")
@Builder
public class ProviderEntity extends UserEntity {

}
