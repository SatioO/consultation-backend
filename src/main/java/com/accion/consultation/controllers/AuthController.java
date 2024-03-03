package com.accion.consultation.controllers;

import com.accion.consultation.models.dto.auth.AuthRequestDTO;
import com.accion.consultation.models.dto.auth.JwtResponseDTO;
import com.accion.consultation.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        JwtResponseDTO jwtDetails = authService.login(authRequest);
        return ResponseEntity.status(HttpStatus.OK).body(jwtDetails);
    }
}
