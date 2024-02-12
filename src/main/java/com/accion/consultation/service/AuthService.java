package com.accion.consultation.service;

import com.accion.consultation.models.dto.auth.AuthRequestDTO;
import com.accion.consultation.models.dto.auth.JwtResponseDTO;

public interface AuthService {
    JwtResponseDTO login(AuthRequestDTO authRequest);
}
