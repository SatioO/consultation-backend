package com.accion.consultation.models;

public enum UserStatus {
    INACTIVE("Inactive"),
    ACTIVE("Active");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
