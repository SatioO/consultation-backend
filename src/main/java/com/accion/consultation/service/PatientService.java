package com.accion.consultation.service;

import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<PatientDTO> findPatients();

    Optional<PatientDTO> findPatientById(long patientId);

    PatientDTO createPatient(CreatePatientRequestDTO body);

    PatientDTO updatePatient(long patientId, UpdatePatientRequestDTO body);

    void deletePatient(long patientId);
}
