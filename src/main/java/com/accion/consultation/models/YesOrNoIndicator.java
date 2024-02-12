package com.accion.consultation.models;

public enum YesOrNoIndicator {
    N("No"),
    Y("Yes");

    private final String description;

    YesOrNoIndicator(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
