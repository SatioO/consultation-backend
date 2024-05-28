package com.accion.consultation.service;

import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.CreateAppointmentOpenRequestDTO;

public interface AppointmentService {
    AppointmentDTO createAppointment(CreateAppointmentOpenRequestDTO body);

    void deleteAppointment(long appointmentId);
}
