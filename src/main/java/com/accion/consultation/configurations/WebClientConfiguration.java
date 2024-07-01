package com.accion.consultation.configurations;

import io.netty.channel.ChannelOption;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .compress(true);

        return WebClient.builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .defaultHeaders(httpHeaders -> {
                httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            })
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }
}
