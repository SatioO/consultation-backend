package com.accion.consultation.models;

public enum MaritalStatus {
    S("Single"),
    M("Married"),
    D("Divorced"),
    W("Widow/Seperated"),
    U("Unknown");

    private final String description;

    MaritalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

