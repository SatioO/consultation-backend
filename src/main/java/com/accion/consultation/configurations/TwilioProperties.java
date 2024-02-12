package com.accion.consultation.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "twilio")
@Configuration
@Data
public class TwilioProperties {
    private String accountSid;
    private String apiKey;
    private String apiSecret;
}
