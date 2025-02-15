package com.accion.consultation.service;

import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<AppointmentDTO> findAppointments(long providerId, Pageable page);

    List<PatientDTO> findPatients();

    Optional<PatientDTO> findPatientById(long patientId);

    PatientDTO createPatient(CreatePatientRequestDTO body);

    PatientDTO updatePatient(long patientId, UpdatePatientRequestDTO body);

    void deletePatient(long patientId);
}
