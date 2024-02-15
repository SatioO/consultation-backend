package com.accion.consultation.service;

import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.models.dto.speciality.UpdateSpecialityRequestDTO;

import java.util.List;
import java.util.Optional;

public interface SpecialityService {
    List<SpecialityDTO> findSpecialities();

    Optional<SpecialityDTO> findSpecialityById(long specialityId);

    SpecialityDTO createSpeciality(CreateSpecialityRequestDTO body);

    SpecialityDTO updateSpeciality(long specialityId, UpdateSpecialityRequestDTO body);

    void deleteSpeciality(long specialityId);
}
