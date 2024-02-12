package com.accion.consultation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(long roleId) {
        super("Couldn't find the role with role id: " + roleId);
    }
}
