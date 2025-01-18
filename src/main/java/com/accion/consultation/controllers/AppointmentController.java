package com.accion.consultation.controllers;

import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.CreateAppointmentOpenRequestDTO;
import com.accion.consultation.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/public/appointments")
    public ResponseEntity<AppointmentDTO> createOpenAppointment(@RequestBody CreateAppointmentOpenRequestDTO body) {
        AppointmentDTO appointment = appointmentService.createAppointment(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
    }

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody CreateAppointmentOpenRequestDTO body) {
        AppointmentDTO appointment = appointmentService.createAppointment(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointmentId") long appointmentId) {
        this.appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
