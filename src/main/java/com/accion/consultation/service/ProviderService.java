package com.accion.consultation.service;

import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.admin.UpdateAdminRequestDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ProviderService {
    List<ProviderDTO> findProviders();

    Optional<ProviderDTO> findProviderById(long providerId);

    ProviderDTO createProvider(CreateProviderRequestDTO body);

    ProviderDTO updateProvider(long providerId, UpdateProviderRequestDTO body);

    void deleteProvider(long providerId);
}