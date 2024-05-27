package com.accion.consultation.controllers;

import com.accion.consultation.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/slots")
    public ResponseEntity<List<ZonedDateTime>> generateSlots(
        @RequestParam("date") ZonedDateTime date,
        @RequestParam(value = "gapInMinutes", defaultValue = "15") int gapInMinutes) {
        return ResponseEntity.ok().body(this.appointmentService.generateSlots(date, gapInMinutes));
    }
}
