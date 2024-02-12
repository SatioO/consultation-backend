package com.accion.consultation.repositories;

import com.accion.consultation.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
