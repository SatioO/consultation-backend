package com.accion.consultation.models.dto.appointment;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class CreateAppointmentRequestDTO {
    private ZonedDateTime dateTime;
    private long specialityId;
    private long providerId;
    private long patientId;
}
