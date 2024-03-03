package com.accion.consultation.service.impl;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.mappers.UserMapper;
import com.accion.consultation.models.CustomUserDetails;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.auth.AuthRequestDTO;
import com.accion.consultation.models.dto.auth.JwtResponseDTO;
import com.accion.consultation.models.dto.auth.JwtUserResponseDTO;
import com.accion.consultation.service.AuthService;
import com.accion.consultation.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public JwtResponseDTO login(AuthRequestDTO authRequest) {
        log.info("authenticating user: " + authRequest.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authenticate.isAuthenticated()) {
            log.info("retrieving user details");
            CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();

            NameDTO personName = new NameDTO();
            personName.setGivenName(userDetails.getName().getGivenName());
            personName.setMiddleName(userDetails.getName().getMiddleName());
            personName.setFamilyName(userDetails.getName().getFamilyName());

            JwtUserResponseDTO jwtUserResponseDTO = new JwtUserResponseDTO();
            jwtUserResponseDTO.setUserId(userDetails.getUserId());
            jwtUserResponseDTO.setName(personName);
            jwtUserResponseDTO.setRoles(userDetails.getRoles().stream().map(RoleEntity::getName).toList());
            jwtUserResponseDTO.setUsername(userDetails.getUsername());
            jwtUserResponseDTO.setEmail(userDetails.getEmail());
            jwtUserResponseDTO.setStatus(userDetails.getStatus());

            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(userDetails))
                    .user(jwtUserResponseDTO)
                    .build();
        } else {
            throw new BadCredentialsException("Invalid Credentials");
        }
    }
}
