package com.accion.consultation.service.impl;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.models.CustomUserDetails;
import com.accion.consultation.models.dto.auth.AuthRequestDTO;
import com.accion.consultation.models.dto.auth.JwtResponseDTO;
import com.accion.consultation.service.AuthService;
import com.accion.consultation.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Override
    public JwtResponseDTO login(AuthRequestDTO authRequest) {
        log.info("authenticating user: " + authRequest.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            log.info("retrieving user details");
            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();

            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(userDetails))
                    .roles(userDetails.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()))
                    .build();
        } else {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }
}
