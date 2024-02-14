package com.accion.consultation.repositories;

import com.accion.consultation.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    List<PatientEntity> findByRoles_Name(String role);
}
