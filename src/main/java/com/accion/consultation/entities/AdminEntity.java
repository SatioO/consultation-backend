package com.accion.consultation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "admin")
@Builder
@PrimaryKeyJoinColumn(name = "admin_id")
public class AdminEntity extends UserEntity {

}
