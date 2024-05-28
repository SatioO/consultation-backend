package com.accion.consultation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SlotUnavailableException extends RuntimeException {
    public SlotUnavailableException() {
        super("The slot is already taken");
    }
}
