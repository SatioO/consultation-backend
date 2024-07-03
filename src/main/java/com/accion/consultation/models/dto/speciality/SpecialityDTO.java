package com.accion.consultation.models.dto.speciality;

import com.accion.consultation.models.SpecialityCategory;
import lombok.Data;

@Data
public class SpecialityDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
}
