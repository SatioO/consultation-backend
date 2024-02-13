package com.accion.consultation.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonName {
    @Column(nullable = false)
    private String familyName;
    private String middleName;
    @Column(nullable = false)
    private String givenName;
}

