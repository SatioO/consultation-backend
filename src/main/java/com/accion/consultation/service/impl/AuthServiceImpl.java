package com.accion.consultation.service.impl;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.models.CustomUserDetails;
import com.accion.consultation.models.dto.auth.AuthRequestDTO;
import com.accion.consultation.models.dto.auth.JwtResponseDTO;
import com.accion.consultation.service.AuthService;
import com.accion.consultation.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Override
    public JwtResponseDTO login(AuthRequestDTO authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();

            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(userDetails))
                    .roles(userDetails.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()))
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid Auth Request");
        }
    }
}
