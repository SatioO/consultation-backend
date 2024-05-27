package com.accion.consultation.mappers;

import com.accion.consultation.entities.AppointmentEntity;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppointmentMapper implements EntityMapper<AppointmentEntity, AppointmentDTO> {
    private final SpecialityMapper specialityMapper;
    private final ProviderMapper providerMapper;
    private final PatientMapper patientMapper;

    @Override
    public AppointmentEntity toEntity(AppointmentDTO model) {
        return null;
    }

    @Override
    public AppointmentDTO toModel(AppointmentEntity entity) {
        AppointmentDTO model = new AppointmentDTO();
        model.setId(entity.getId());
        model.setDateTime(entity.getDateTime());
        model.setSpeciality(specialityMapper.toModel(entity.getSpeciality()));
        model.setProvider(providerMapper.toModel(entity.getProvider()));
        model.setPatient(patientMapper.toModel(entity.getPatient()));
        model.setStatus(entity.getStatus());

        return null;
    }
}
