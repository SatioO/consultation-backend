package com.accion.consultation.repositories;

import com.accion.consultation.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    @Query(value = "SELECT * FROM appointment ap WHERE ap.provider_id = :providerId AND ap.date_time >= :startDate AND ap.date_time <= :endDate", nativeQuery = true)
    List<AppointmentEntity> findAppointmentsByProviderId(
        @Param("providerId") long providerId,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate);
}
