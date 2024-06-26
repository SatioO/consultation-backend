package com.accion.consultation.service.impl;

import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.exceptions.RoleNotFoundException;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AdminMapper;
import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.admin.UpdateAdminRequestDTO;
import com.accion.consultation.repositories.RoleRepository;
import com.accion.consultation.repositories.UserRepository;
import com.accion.consultation.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AdminMapper adminMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public List<AdminDTO> findAdmins() {
        return userRepository.findByRoles_Name(AdministrativeSex.Role.ADMIN.getDescription())
                .stream()
                .map(adminMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdminDTO> findAdminById(long adminId) {
        return userRepository.findById(adminId).map(adminMapper::toModel);
    }

    @Override
    public AdminDTO createAdmin(CreateAdminRequestDTO body) {
        return roleRepository.findByName(AdministrativeSex.Role.ADMIN.getDescription()).map(role -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode("helloworld");

            UserEntity user = adminMapper.toCreateAdminEntity(body);
            user.setPassword(encryptedPassword);

            user.setRoles(List.of(role));

            UserEntity savedUser = userRepository.save(user);
            return adminMapper.toModel(savedUser);
        }).orElseThrow(() -> new RoleNotFoundException(AdministrativeSex.Role.ADMIN.getDescription()));
    }

    @Override
    public AdminDTO updateAdmin(long adminId, UpdateAdminRequestDTO body) {
        return userRepository.findById(adminId)
                .map(user -> this.userRepository.save(this.adminMapper.toUpdateAdminEntity(user, body)))
                .map(adminMapper::toModel)
                .orElseThrow(() -> new UserNotFoundException(adminId));
    }

    @Override
    public void deleteAdmin(long adminId) {
        this.userRepository.findById(adminId).ifPresent(admin -> {
            admin.setStatus(UserStatus.INACTIVE);
            this.userRepository.save(admin);
        });
    }
}
