package com.accion.consultation.repositories;

import com.accion.consultation.entities.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {
}
