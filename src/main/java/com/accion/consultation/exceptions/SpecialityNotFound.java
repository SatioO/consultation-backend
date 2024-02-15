package com.accion.consultation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpecialityNotFound extends RuntimeException {
    public SpecialityNotFound(long specialityId) {
        super("Speciality Not Found With ID: " + specialityId);
    }

    public SpecialityNotFound(String name) {
        super("Couldn't find the speciality: " + name);
    }
}
