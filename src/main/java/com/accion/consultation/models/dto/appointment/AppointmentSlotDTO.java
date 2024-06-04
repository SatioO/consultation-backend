package com.accion.consultation.models.dto.appointment;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppointmentSlotDTO {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean isAvailable;
}
