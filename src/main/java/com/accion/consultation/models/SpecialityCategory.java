package com.accion.consultation.models;

public enum SpecialityCategory {
    BEHAVIORAL("behavioral"),
    NON_BEHAVIORAL("non-behavioral"),
    THERAPY("therapy");

    private final String description;

    SpecialityCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
