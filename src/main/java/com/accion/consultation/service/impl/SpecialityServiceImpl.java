package com.accion.consultation.service.impl;

import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.exceptions.SpecialityNotFound;
import com.accion.consultation.mappers.SpecialityMapper;
import com.accion.consultation.models.SpecialityCategory;
import com.accion.consultation.models.dto.provider.GetProviderDTO;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.speciality.UpdateSpecialityRequestDTO;
import com.accion.consultation.repositories.ProviderRepository;
import com.accion.consultation.repositories.SpecialityRepository;
import com.accion.consultation.service.SpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final ProviderRepository providerRepository;
    private final SpecialityMapper specialityMapper;

    @Override
    public List<SpecialityDTO> findSpecialities(SpecialityCategory category) {
        List<SpecialityEntity> specialities;

        if(category != null) {
            specialities = specialityRepository.findByCategory(category);
        } else {
            specialities = specialityRepository.findAll();
        }

        return specialities
                .stream()
                .map(specialityMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SpecialityDTO> findSpecialityById(long specialityId) {
        return specialityRepository.findById(specialityId).map(specialityMapper::toModel);
    }

    @Override
    public SpecialityDTO createSpeciality(CreateSpecialityRequestDTO body) {
        SpecialityEntity speciality = specialityMapper.toCreateSpecialityEntity(body);
        SpecialityEntity savedSpeciality = specialityRepository.save(speciality);
        return specialityMapper.toModel(savedSpeciality);
    }

    @Override
    public SpecialityDTO updateSpeciality(long specialityId, UpdateSpecialityRequestDTO body) {
        return specialityRepository.findById(specialityId).map(speciality -> {
            SpecialityEntity specialityEntity = specialityMapper.toUpdateSpecialityEntity(speciality, body);
            SpecialityEntity savedSpeciality = specialityRepository.save(specialityEntity);
            return specialityMapper.toModel(savedSpeciality);
        }).orElseThrow(() -> new SpecialityNotFound(specialityId));
    }

    @Override
    public void deleteSpeciality(long specialityId) {
        this.specialityRepository.findById(specialityId).ifPresent(this.specialityRepository::delete);
    }

    @Override
    public List<GetProviderDTO> findProviders(long specialityId) {
        return this.providerRepository.findProvidersBySpeciality(specialityId);
    }
}
