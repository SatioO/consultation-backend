package com.accion.consultation.models;

public enum PatientStatus {
    INACTIVE("Inactive"),
    ACTIVE("Active");

    private final String description;

    PatientStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
