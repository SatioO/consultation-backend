package com.accion.consultation.repositories;

import com.accion.consultation.entities.ProviderEntity;
import com.accion.consultation.models.dto.provider.GetProviderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {
    List<ProviderEntity> findByRoles_Name(String role);

    @Query("SELECT NEW com.accion.consultation.models.dto.provider.GetProviderDTO(" +
            "p.userId, " +
            "p.username, " +
            "NEW com.accion.consultation.models.dto.NameDTO(p.name.givenName, p.name.middleName, p.name.familyName)," +
            "s.name, " +
            "p.bio, " +
            "p.photoUrl" +
        ") FROM ProviderEntity p JOIN p.specialities s WHERE s.id = :specialityId AND p.acceptingNewPatients = true")
    List<GetProviderDTO> findProvidersBySpeciality(@Param("specialityId") Long specialityId);
}

