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

        // Adjust startDateTime to the nearest previous interval
        long minutes = startDate.getMinute();
        long adjustment = minutes % gapInMinutes;
        ZonedDateTime adjustedStartDateTime = startDate.minusMinutes(adjustment).truncatedTo(ChronoUnit.MINUTES);

        // End of the day
        ZonedDateTime endOfDay = adjustedStartDateTime.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        // Generate the slots based on the gap
        ZonedDateTime nextSlot = adjustedStartDateTime;
        while (nextSlot.isBefore(endOfDay)) {
            timeSlots.add(nextSlot);
            nextSlot = nextSlot.plusMinutes(gapInMinutes);
        }

        return timeSlots;
    }
}
