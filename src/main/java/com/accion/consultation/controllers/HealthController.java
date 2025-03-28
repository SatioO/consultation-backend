package com.accion.consultation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/")
    public String ping() {
        return "Hello & Welcome to Appointment Service !!!";
    }
}
