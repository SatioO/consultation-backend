package com.accion.consultation.models.dto;

import com.accion.consultation.models.AddressType;
import lombok.Data;

@Data
public class AddressDTO {
    private AddressType type;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String zip;
    private String country;
}
