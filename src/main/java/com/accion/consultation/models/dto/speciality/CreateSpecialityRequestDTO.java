package com.accion.consultation.models.dto.speciality;

import com.accion.consultation.models.SpecialityCategory;
import lombok.Data;

@Data
public class CreateSpecialityRequestDTO {
    private String name;
    private String description;
    private SpecialityCategory category;
}
