package com.accion.consultation.models.dto.provider;

import com.accion.consultation.models.dto.NameDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class GetProviderDTO {
    private long userId;
    private String username;
    private NameDTO name;
    private String speciality;
}

