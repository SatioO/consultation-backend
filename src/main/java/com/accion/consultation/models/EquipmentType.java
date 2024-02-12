package com.accion.consultation.models;

public enum EquipmentType {
    CP("Cellular or Mobile Phone"),
    FX("Fax"),
    Internet("Internet Address"),
    PH("Telephone");

    private final String description;

    EquipmentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
