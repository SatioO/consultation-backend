package com.accion.consultation.service.impl;

import com.accion.consultation.entities.AppointmentEntity;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.ProviderEntity;
import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.exceptions.SlotUnavailableException;
import com.accion.consultation.exceptions.SpecialityNotFound;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AppointmentMapper;
import com.accion.consultation.mappers.PatientMapper;
import com.accion.consultation.models.AppointmentStatus;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.CreateAppointmentOpenRequestDTO;
import com.accion.consultation.repositories.AppointmentRepository;
import com.accion.consultation.repositories.PatientRepository;
import com.accion.consultation.repositories.ProviderRepository;
import com.accion.consultation.repositories.SpecialityRepository;
import com.accion.consultation.service.AppointmentService;
import com.accion.consultation.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ProviderRepository providerRepository;
    private final SpecialityRepository specialityRepository;
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;

    @Transactional
    @Override
    public AppointmentDTO createAppointment(CreateAppointmentOpenRequestDTO body) {
        SpecialityEntity foundSpeciality = specialityRepository.findById(body.getSpecialityId()).orElseThrow(() -> new SpecialityNotFound(body.getSpecialityId()));
        ProviderEntity foundProvider = providerRepository.findById(body.getProviderId()).orElseThrow(() -> new UserNotFoundException(body.getProviderId()));

        ZonedDateTime startDate = body.getDateTime();
        long minutes = startDate.getMinute();
        long adjustment = minutes % 15;
        ZonedDateTime adjustedStartDate = startDate.minusMinutes(adjustment).truncatedTo(ChronoUnit.MINUTES);

        Optional<AppointmentEntity> appointmentSlot = appointmentRepository.findAppointmentSlot(foundProvider.getUserId(), adjustedStartDate);
        if(appointmentSlot.isPresent()) {
            throw new SlotUnavailableException();
        }

        PatientEntity patient = null;
        if(body.getPatientId() == 0) {
            patient = patientMapper.toEntity(patientService.createPatient(body.getPatientDetails()));
        } else {
            patient = patientRepository.findById(body.getPatientId()).orElseThrow(() -> new UserNotFoundException(body.getPatientId()));
        }

        AppointmentEntity appointment = AppointmentEntity.builder()
            .dateTime(body.getDateTime())
            .provider(foundProvider)
            .speciality(foundSpeciality)
            .patient(patient)
            .status(AppointmentStatus.PENDING)
            .build();

        return appointmentMapper.toModel(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(long appointmentId) {
        this.appointmentRepository.findById(appointmentId).ifPresent(this.appointmentRepository::delete);
    }
}
