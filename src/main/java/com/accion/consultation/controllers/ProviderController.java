package com.accion.consultation.controllers;

import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.AppointmentSlotDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;
import com.accion.consultation.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/providers")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> getProviders() {
        List<ProviderDTO> foundProviders = providerService.findProviders();
        return ResponseEntity.status(HttpStatus.OK).body(foundProviders);
    }

    @GetMapping("{providerId}/slots")
    public ResponseEntity<List<AppointmentSlotDTO>> getAvailableSlots(
        @PathVariable("providerId") Long providerId,
        @RequestParam(value = "date") Instant date) {
        List<AppointmentSlotDTO> availableSlots = providerService.getAvailableSlots(providerId, date);
        return ResponseEntity.ok().body(availableSlots);
    }

    @GetMapping("/{providerId}/appointments")
    public ResponseEntity<List<AppointmentDTO>> getProviderAppointments(
        @PathVariable("providerId") long providerId,
        Pageable pageable
    ) {
        List<AppointmentDTO> appointments = providerService.findAppointments(providerId, pageable);
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping("/{providerId}/patients")
    public ResponseEntity<List<PatientDTO>> getPatients(
        @PathVariable("providerId") long providerId,
        Pageable pageable
    ) {
        List<PatientDTO> patients = providerService.findPatients(providerId, pageable);
        return ResponseEntity.ok().body(patients);
    }

    @GetMapping("/{providerId}")
    public ResponseEntity<ProviderDTO> getProviderById(@PathVariable long providerId) {
        return providerService.findProviderById(providerId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(providerId));
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> createProvider(@RequestBody @Valid CreateProviderRequestDTO body) {
        ProviderDTO createdProvider = providerService.createProvider(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProvider);
    }

    @PutMapping("/{providerId}")
    public ResponseEntity<ProviderDTO> updateProvider(@PathVariable long providerId, @RequestBody @Valid UpdateProviderRequestDTO body) {
        ProviderDTO updatedProvider = providerService.updateProvider(providerId, body);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProvider);
    }

    @DeleteMapping("/{providerId}")
    public ResponseEntity<Void> deleteProvider(@PathVariable long providerId) {
        providerService.deleteProvider(providerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
