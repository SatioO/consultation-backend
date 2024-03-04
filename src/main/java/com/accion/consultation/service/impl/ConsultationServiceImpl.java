package com.accion.consultation.service.impl;

import com.accion.consultation.configurations.TwilioProperties;
import com.accion.consultation.models.dto.consultation.ConsultationSessionResponseDTO;
import com.accion.consultation.models.dto.consultation.CreateSessionRequestDTO;
import com.accion.consultation.service.ConsultationService;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    private final TwilioProperties twilioProperties;

    @Override
    public ConsultationSessionResponseDTO createSession(CreateSessionRequestDTO body) {
        // Required for all types of tokens
        String twilioAccountSid = twilioProperties.getAccountSid();
        String twilioApiKey = twilioProperties.getApiKey();
        String twilioApiSecret = twilioProperties.getApiSecret();

        // Create Video grant
        VideoGrant grant = new VideoGrant().setRoom(body.getMeetingId());

        // Create access token
        AccessToken token = new AccessToken.Builder(
            twilioAccountSid,
            twilioApiKey,
            twilioApiSecret
        ).identity(body.getUsername()).grant(grant).build();

        return ConsultationSessionResponseDTO.builder()
            .room(body.getMeetingId())
            .identity(token.getSubject())
            .accessToken(token.toJwt())
            .build();
    }
}
