package com.accion.consultation.service;

import com.accion.consultation.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(CustomUserDetails username);

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
