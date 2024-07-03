package com.accion.consultation.models.dto.provider;

import com.accion.consultation.models.dto.NameDTO;

import java.util.Objects;

public record GetProviderDTO(Long userId, String username, NameDTO name, String speciality) {
    public String getFullName() {
        return String.join(" ",
            name.getGivenName(),
            Objects.requireNonNullElse(name.getMiddleName(), ""),
            name.getFamilyName()).trim();
    }
}
