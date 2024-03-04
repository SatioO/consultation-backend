package com.accion.consultation.models.dto.consultation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConsultationSessionResponseDTO {
    private String room;
    private String identity;
    private String accessToken;
}
