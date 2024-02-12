package com.accion.consultation.models;

public enum AddressType {
    B("Firm/Business"),
    BI("Billing Address"),
    BR("Residence at birth (home address at time of birth"),
    C("Current Or Temporary"),
    F("Country Of Origin"),
    H("Home"),
    l("Legal Address"),
    M("Mailing"),
    O("Office/Business"),
    P("Permanent");

    private final String description;

    AddressType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}