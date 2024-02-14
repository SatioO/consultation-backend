package com.accion.consultation.service;

import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.admin.UpdateAdminRequestDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    List<AdminDTO> findAdmins();

    Optional<AdminDTO> findAdminById(long adminId);

    AdminDTO createAdmin(CreateAdminRequestDTO body);

    AdminDTO updateAdmin(long adminId, UpdateAdminRequestDTO body);

    void deleteAdmin(long adminId);
}
