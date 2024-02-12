package com.accion.consultation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "provider")
@Builder
@PrimaryKeyJoinColumn(name = "provider_id")
public class ProviderEntity extends UserEntity {

}
