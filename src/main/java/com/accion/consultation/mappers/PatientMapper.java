package com.accion.consultation.mappers;

import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.PersonName;
import com.accion.consultation.models.PatientStatus;
import com.accion.consultation.models.YesOrNoIndicator;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements EntityMapper<PatientEntity, PatientDTO> {
    @Override
    public PatientEntity toEntity(PatientDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        PatientEntity patient = new PatientEntity();
        patient.setUsername(model.getEmail());
        patient.setName(personName);
        patient.setEmail(model.getEmail());
        patient.setDob(model.getDob());
        patient.setMrn(model.getMrn());
        patient.setSsn(model.getSsn());
        patient.setLicenseNumber(model.getLicenseNumber());
        patient.setMaritalStatus(model.getMaritalStatus());
        patient.setAdministrativeSex(model.getAdministrativeSex());
        patient.setIs_deceased(YesOrNoIndicator.N);
        patient.setStatus(PatientStatus.ACTIVE);

        return patient;
    }

    @Override
    public PatientDTO toModel(PatientEntity entity) {
        NameDTO personName = new NameDTO();
        personName.setGivenName(entity.getName().getGivenName());
        personName.setMiddleName(entity.getName().getMiddleName());
        personName.setFamilyName(entity.getName().getFamilyName());

        PatientDTO patient = new PatientDTO();
        patient.setPatientId(entity.getUserId());
        patient.setName(personName);
        patient.setEmail(entity.getEmail());
        patient.setDob(entity.getDob());
        patient.setMrn(entity.getMrn());
        patient.setSsn(entity.getSsn());
        patient.setLicenseNumber(entity.getLicenseNumber());
        patient.setMaritalStatus(entity.getMaritalStatus());
        patient.setAdministrativeSex(entity.getAdministrativeSex());
        patient.setIs_deceased(entity.getIs_deceased());
        patient.setDeceased_on(entity.getDeceased_on());
        patient.setStatus(entity.getStatus());
        patient.setCreatedAt(entity.getCreatedAt());
        patient.setUpdatedAt(entity.getUpdatedAt());
        return patient;
    }

    public PatientEntity toCreatePatientEntity(CreatePatientRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        PatientEntity patient = new PatientEntity();
        patient.setUsername(model.getEmail());
        patient.setName(personName);
        patient.setEmail(model.getEmail());
        patient.setDob(model.getDob());
        patient.setMrn(model.getMrn());
        patient.setSsn(model.getSsn());
        patient.setLicenseNumber(model.getLicenseNumber());
        patient.setMaritalStatus(model.getMaritalStatus());
        patient.setAdministrativeSex(model.getAdministrativeSex());
        patient.setIs_deceased(YesOrNoIndicator.N);
        patient.setStatus(PatientStatus.ACTIVE);
        return patient;
    }

    public PatientEntity toUpdatePatientEntity(UpdatePatientRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        PatientEntity patient = new PatientEntity();
        patient.setName(personName);
        patient.setEmail(model.getEmail());
        patient.setDob(model.getDob());
        patient.setMrn(model.getMrn());
        patient.setSsn(model.getSsn());
        patient.setLicenseNumber(model.getLicenseNumber());
        patient.setMaritalStatus(model.getMaritalStatus());
        patient.setAdministrativeSex(model.getSex());

        return patient;
    }
}
