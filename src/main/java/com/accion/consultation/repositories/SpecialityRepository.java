package com.accion.consultation.repositories;

import com.accion.consultation.entities.SpecialityEntity;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {
    List<SpecialityEntity> findByIdIn(List<Long> ids);
}
