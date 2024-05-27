package com.accion.consultation.repositories;

import com.accion.consultation.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
    List<ProviderEntity> findByRoles_Name(String role);

    @Query(value = "SELECT * FROM provider p INNER JOIN user u ON p.user_id = u.user_id JOIN provider_speciality ps ON p.user_id = ps.user_id JOIN speciality s ON ps.speciality_id = s.id WHERE s.id = :specialityId", nativeQuery = true)
    List<ProviderEntity> findProvidersBySpeciality(@Param("specialityId") Long specialityId);
}
