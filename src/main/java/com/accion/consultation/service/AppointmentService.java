package com.accion.consultation.service;

import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.CreateAppointmentRequestDTO;


public interface AppointmentService {
    AppointmentDTO createAppointment(CreateAppointmentRequestDTO body);

    void deleteAppointment(long appointmentId);
}
