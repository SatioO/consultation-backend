package com.accion.consultation.models;

public enum PhoneUseCode {
    PRS("Personal"),
    WPN("Work Number"),
    PRN("Primary Residence Number"),
    ORN("Other Residence Number"),
    EMR("Emergency Number"),
    VHN("Vacation Home Number");

    private final String description;

    PhoneUseCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
