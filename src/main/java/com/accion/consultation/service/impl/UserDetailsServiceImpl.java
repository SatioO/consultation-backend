package com.accion.consultation.service.impl;

import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.models.CustomUserDetails;
import com.accion.consultation.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Entering in loadUserByUsername");
        Optional<UserEntity> user = userRepository.findByUsername(username);
        return user
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found user"));
    }
}
