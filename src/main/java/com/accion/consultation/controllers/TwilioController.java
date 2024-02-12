package com.accion.consultation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/twilio")
public class TwilioController {
    @GetMapping(path = "/token")
    public void getToken() {}

    @PostMapping(path = "/token")
    public void generateToken() {}
}
