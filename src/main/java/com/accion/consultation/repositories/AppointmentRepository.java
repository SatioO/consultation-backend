package com.accion.consultation.repositories;

import com.accion.consultation.entities.AppointmentEntity;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.ProviderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    @Query(value = "SELECT * FROM appointment ap WHERE ap.provider_id = :providerId AND ap.date_time >= :startDate AND ap.date_time <= :endDate", nativeQuery = true)
    List<AppointmentEntity> findAppointmentsByProviderId(
        @Param("providerId") long providerId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT DISTINCT ap.patient from AppointmentEntity ap WHERE ap.provider = :providerId")
    List<PatientEntity> findAllPatients(@Param("providerId") ProviderEntity providerId, Pageable pageable);

    Page<AppointmentEntity> findAppointmentByProviderUserId(@Param("providerId") long providerId, Pageable pageable);

    @Query(value= "SELECT * FROM appointment ap WHERE ap.provider_id = :providerId AND ap.date_time = :date", nativeQuery = true)
    Optional<AppointmentEntity> findAppointmentSlot(@Param("providerId") long providerId, @Param("date") LocalDateTime date);
}
