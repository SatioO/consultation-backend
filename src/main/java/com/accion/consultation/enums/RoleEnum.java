package com.accion.consultation.constants;

public enum RoleEnum {
    PATIENT("patient"),
    PROVIDER("provider"),
    ADMIN("admin");

    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
