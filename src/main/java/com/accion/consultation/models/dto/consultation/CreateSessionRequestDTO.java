package com.accion.consultation.models.dto.consultation;

import lombok.Data;

@Data
public class CreateSessionRequestDTO {
    private String username;
    private String meetingId;
}
