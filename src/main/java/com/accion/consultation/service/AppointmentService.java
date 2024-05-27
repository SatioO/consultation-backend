package com.accion.consultation.service;

import java.time.ZonedDateTime;
import java.util.List;

public interface AppointmentService {
    List<Void> findAppointmentsByProviderId(long providerId);

    Void getAvailableSlots();

    List<ZonedDateTime> generateSlots(ZonedDateTime date, int gapInMinutes);

    Void createAppointment(Void body);

    void deleteAppointment(long appointmentId);
}
