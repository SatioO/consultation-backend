package com.accion.consultation.mappers;

import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.speciality.UpdateSpecialityRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class SpecialityMapper implements EntityMapper<SpecialityEntity, SpecialityDTO> {
    @Override
    public SpecialityEntity toEntity(SpecialityDTO model) {
        return null;
    }

    @Override
    public SpecialityDTO toModel(SpecialityEntity entity) {
        return null;
    }

    public SpecialityEntity toCreateSpecialityEntity(CreateSpecialityRequestDTO model) {
        return null;
    }

    public SpecialityEntity toUpdateSpecialityEntity(UpdateSpecialityRequestDTO model) {
        return null;
    }
}
