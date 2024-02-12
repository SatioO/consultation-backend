package com.accion.consultation.mappers;

import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.PersonName;
import com.accion.consultation.models.PatientStatus;
import com.accion.consultation.models.YesOrNoIndicator;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements EntityMapper<PatientEntity, PatientDTO> {
    @Override
    public PatientEntity toEntity(PatientDTO model) {
        return null;
    }

    @Override
    public PatientDTO toModel(PatientEntity entity) {
        return PatientDTO.builder().build();
    }

    public PatientEntity toCreatePatientEntity(CreatePatientRequestDTO model) {
        return null;
    }

    public PatientEntity toUpdatePatientEntity(UpdatePatientRequestDTO model) {
        return null;
    }
}
