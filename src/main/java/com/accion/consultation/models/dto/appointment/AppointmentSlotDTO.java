package com.accion.consultation.models.dto.appointment;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AppointmentSlotDTO {
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private boolean isAvailable;
}
