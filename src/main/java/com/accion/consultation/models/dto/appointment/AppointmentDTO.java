package com.accion.consultation.models.dto.appointment;

import com.accion.consultation.models.AppointmentStatus;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AppointmentDTO {
    private long id;
    private ZonedDateTime dateTime;
    private SpecialityDTO speciality;
    private ProviderDTO provider;
    private PatientDTO patient;
    private AppointmentStatus status;
}
