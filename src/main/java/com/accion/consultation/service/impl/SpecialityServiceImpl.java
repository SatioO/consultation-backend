package com.accion.consultation.service.impl;

import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.mappers.SpecialityMapper;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.speciality.UpdateSpecialityRequestDTO;
import com.accion.consultation.repositories.SpecialityRepository;
import com.accion.consultation.service.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final SpecialityMapper specialityMapper;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository, SpecialityMapper specialityMapper) {
        this.specialityRepository = specialityRepository;
        this.specialityMapper = specialityMapper;
    }

    @Override
    public List<SpecialityDTO> findSpecialities() {
        return specialityRepository.findAll()
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
        SpecialityEntity speciality = specialityMapper.toUpdateSpecialityEntity(body);
        SpecialityEntity savedSpeciality = specialityRepository.save(speciality);
        return specialityMapper.toModel(savedSpeciality);
    }

    @Override
    public void deleteSpeciality(long specialityId) {
        this.specialityRepository.findById(specialityId).ifPresent(this.specialityRepository::delete);
    }
}
