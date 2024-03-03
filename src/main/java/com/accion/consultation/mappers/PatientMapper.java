package com.accion.consultation.mappers;

import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.PersonName;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.YesOrNoIndicator;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PatientMapper implements EntityMapper<PatientEntity, PatientDTO> {
    private final AddressMapper addressMapper;

    public PatientMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public PatientEntity toEntity(PatientDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        PatientEntity entity = new PatientEntity();
        entity.setUserId(model.getUserId());
        entity.setUsername(model.getEmail());
        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setMrn(model.getMrn());
        entity.setSsn(model.getSsn());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setMaritalStatus(model.getMaritalStatus());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setIs_deceased(YesOrNoIndicator.N);
        entity.setStatus(UserStatus.ACTIVE);

        entity.setAddresses(model
                .getAddresses()
                .stream()
                .map(addressMapper::toEntity)
                .collect(Collectors.toList()));

        return entity;
    }

    @Override
    public PatientDTO toModel(PatientEntity entity) {
        NameDTO personName = new NameDTO();
        personName.setGivenName(entity.getName().getGivenName());
        personName.setMiddleName(entity.getName().getMiddleName());
        personName.setFamilyName(entity.getName().getFamilyName());

        PatientDTO model = new PatientDTO();
        model.setUserId(entity.getUserId());
        model.setName(personName);
        model.setUsername(entity.getEmail());
        model.setEmail(entity.getEmail());
        model.setDob(entity.getDob());
        model.setMrn(entity.getMrn());
        model.setSsn(entity.getSsn());
        model.setLicenseNumber(entity.getLicenseNumber());
        model.setMaritalStatus(entity.getMaritalStatus());
        model.setAdministrativeSex(entity.getAdministrativeSex());
        model.setIs_deceased(entity.getIs_deceased());
        model.setDeceased_on(entity.getDeceased_on());
        model.setStatus(entity.getStatus());
        model.setCreatedAt(entity.getCreatedAt());
        model.setUpdatedAt(entity.getUpdatedAt());

        model.setAddresses(entity
                .getAddresses()
                .stream()
                .map(addressMapper::toModel)
                .collect(Collectors.toList()));

        return model;
    }

    public PatientEntity toCreatePatientEntity(CreatePatientRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        PatientEntity entity = new PatientEntity();
        entity.setUsername(model.getEmail());
        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setMrn(model.getMrn());
        entity.setSsn(model.getSsn());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setMaritalStatus(model.getMaritalStatus());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setIs_deceased(YesOrNoIndicator.N);
        entity.setStatus(UserStatus.ACTIVE);
        return entity;
    }

    public PatientEntity toUpdatePatientEntity(PatientEntity entity, UpdatePatientRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setMrn(model.getMrn());
        entity.setSsn(model.getSsn());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setMaritalStatus(model.getMaritalStatus());
        entity.setAdministrativeSex(model.getAdministrativeSex());


        return entity;
    }
}
