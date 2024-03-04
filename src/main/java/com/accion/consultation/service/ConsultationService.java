package com.accion.consultation.service;

import com.accion.consultation.models.dto.consultation.ConsultationSessionResponseDTO;
import com.accion.consultation.models.dto.consultation.CreateSessionRequestDTO;

public interface ConsultationService {
    ConsultationSessionResponseDTO createSession(CreateSessionRequestDTO body);
}
