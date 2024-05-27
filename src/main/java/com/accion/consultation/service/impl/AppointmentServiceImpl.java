package com.accion.consultation.service.impl;

import com.accion.consultation.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public List<Void> findAppointmentsByProviderId(long providerId) {
        return null;
    }

    @Override
    public Void getAvailableSlots() {
        return null;
    }

    @Override
    public Void createAppointment(Void body) {
        return null;
    }

    @Override
    public void deleteAppointment(long appointmentId) {

    }

    public List<ZonedDateTime> generateSlots(ZonedDateTime startDate, int gapInMinutes) {
        List<ZonedDateTime> timeSlots = new ArrayList<>();

        // Normalize the start time to the start of the day in the same time zone
        ZonedDateTime startOfDay = startDate.truncatedTo(ChronoUnit.DAYS);
        // End of the day
        ZonedDateTime endOfDay = startOfDay.plusDays(1).truncatedTo(ChronoUnit.DAYS);

        // Add the first slot if it is after the current time
        if (startDate.isBefore(endOfDay)) {
            timeSlots.add(startDate);
        }

        // Generate the slots based on the gap
        ZonedDateTime nextSlot = startDate.plusMinutes(gapInMinutes);
        while (nextSlot.isBefore(endOfDay)) {
            timeSlots.add(nextSlot);
            nextSlot = nextSlot.plusMinutes(gapInMinutes);
        }

        return timeSlots;
    }
}
