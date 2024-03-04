package com.accion.consultation.controllers;

import com.accion.consultation.models.dto.consultation.ConsultationSessionResponseDTO;
import com.accion.consultation.models.dto.consultation.CreateSessionRequestDTO;
import com.accion.consultation.service.ConsultationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping(path = "/session")
    public ResponseEntity<ConsultationSessionResponseDTO> createSession(@RequestBody CreateSessionRequestDTO body) {
        return ResponseEntity.ok().body(consultationService.createSession(body));
    }
}
