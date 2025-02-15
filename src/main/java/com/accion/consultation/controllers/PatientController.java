package com.accion.consultation.controllers;

import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import com.accion.consultation.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}/appointments")
    public ResponseEntity<List<AppointmentDTO>> getProviderAppointments(
            @PathVariable("patientId") long patientId,
            Pageable pageable
    ) {
        List<AppointmentDTO> appointments = patientService.findAppointments(patientId, pageable);
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> foundPatients = patientService.findPatients();
        return ResponseEntity.status(HttpStatus.OK).body(foundPatients);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable long patientId) {
        return patientService.findPatientById(patientId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(patientId));
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody @Valid CreatePatientRequestDTO body) {
        PatientDTO createdPatient = patientService.createPatient(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable long patientId, @RequestBody @Valid UpdatePatientRequestDTO body) {
        PatientDTO updatedPatient = patientService.updatePatient(patientId, body);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
