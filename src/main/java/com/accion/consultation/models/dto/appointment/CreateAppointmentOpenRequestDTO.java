package com.accion.consultation.models.dto.appointment;

import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentOpenRequestDTO {
    private LocalDateTime dateTime;
    private long specialityId;
    private long providerId;
    private long patientId;
    private CreatePatientRequestDTO patientDetails;
}
