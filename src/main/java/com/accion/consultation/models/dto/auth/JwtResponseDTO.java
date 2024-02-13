package com.accion.consultation.models.dto.auth;

import com.accion.consultation.models.dto.NameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;
    private NameDTO name;
    private List<String> roles;
}
