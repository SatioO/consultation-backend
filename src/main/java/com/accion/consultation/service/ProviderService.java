package com.accion.consultation.service;

import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.admin.UpdateAdminRequestDTO;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.AppointmentSlotDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface ProviderService {
    List<ProviderDTO> findProviders();

    List<AppointmentSlotDTO> getAvailableSlots(Long providerId, Instant date);

    Optional<ProviderDTO> findProviderById(long providerId);

    List<PatientDTO> findPatients(long providerId, Pageable page);

    ProviderDTO createProvider(CreateProviderRequestDTO body);

    List<AppointmentDTO> findAppointments(long providerId, Pageable page);

    ProviderDTO updateProvider(long providerId, UpdateProviderRequestDTO body);

    void deleteProvider(long providerId);
}
