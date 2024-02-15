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
        SpecialityEntity entity = new SpecialityEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setCategory(model.getCategory());

        return entity;
    }

    @Override
    public SpecialityDTO toModel(SpecialityEntity entity) {
        SpecialityDTO model = new SpecialityDTO();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setCategory(entity.getCategory());

        return model;
    }

    public SpecialityEntity toCreateSpecialityEntity(CreateSpecialityRequestDTO model) {
        SpecialityEntity entity = new SpecialityEntity();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setCategory(model.getCategory());

        return entity;
    }

    public SpecialityEntity toUpdateSpecialityEntity(SpecialityEntity entity, UpdateSpecialityRequestDTO model) {
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setCategory(model.getCategory());

        return entity;
    }
}
