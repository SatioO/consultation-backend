package com.accion.consultation.models;

public enum AdministrativeSex {
    A("Ambiguous"),
    F("Female"),
    M("Male"),
    N("Not Applicable"),
    O("Other"),
    U("Unknown");

    private final String description;

    AdministrativeSex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
