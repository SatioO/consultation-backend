package com.accion.consultation.service.impl;

import com.accion.consultation.entities.AppointmentEntity;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.ProviderEntity;
import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.exceptions.SpecialityNotFound;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AppointmentMapper;
import com.accion.consultation.mappers.PatientMapper;
import com.accion.consultation.models.AppointmentStatus;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.CreateAppointmentRequestDTO;
import com.accion.consultation.repositories.AppointmentRepository;
import com.accion.consultation.repositories.ProviderRepository;
import com.accion.consultation.repositories.SpecialityRepository;
import com.accion.consultation.service.AppointmentService;
import com.accion.consultation.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ProviderRepository providerRepository;
    private final SpecialityRepository specialityRepository;
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    @Override
    public AppointmentDTO createAppointment(CreateAppointmentRequestDTO body) {
        SpecialityEntity foundSpeciality = specialityRepository.findById(body.getSpecialityId()).orElseThrow(() -> new SpecialityNotFound(body.getSpecialityId()));
        ProviderEntity foundProvider = providerRepository.findById(body.getProviderId()).orElseThrow(() -> new UserNotFoundException(body.getProviderId()));

        PatientEntity createdPatient = patientMapper.toEntity(patientService.createPatient(body.getPatientDetails()));

        AppointmentEntity appointment = AppointmentEntity.builder()
            .dateTime(body.getDateTime())
            .provider(foundProvider)
            .speciality(foundSpeciality)
            .patient(createdPatient)
            .status(AppointmentStatus.PENDING)
            .build();

        return appointmentMapper.toModel(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(long appointmentId) {
        this.appointmentRepository.findById(appointmentId).ifPresent(this.appointmentRepository::delete);
    }

    public List<ZonedDateTime> generateSlots(ZonedDateTime startDate, int gapInMinutes) {
        List<ZonedDateTime> timeSlots = new ArrayList<>();

        // Adjust startDateTime to the nearest previous interval
        long minutes = startDate.getMinute();
        long adjustment = minutes % gapInMinutes;
        ZonedDateTime adjustedStartTime = startDate.minusMinutes(adjustment).truncatedTo(ChronoUnit.MINUTES);

        // End of the day
        ZonedDateTime endOfDay = adjustedStartTime.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        // Generate the slots based on the gap
        ZonedDateTime nextSlot = adjustedStartTime;
        while (nextSlot.isBefore(endOfDay)) {
            timeSlots.add(nextSlot);
            nextSlot = nextSlot.plusMinutes(gapInMinutes);
        }

        return timeSlots;
    }
}
