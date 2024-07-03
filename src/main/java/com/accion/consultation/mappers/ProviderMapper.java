package com.accion.consultation.mappers;

import com.accion.consultation.entities.PersonName;
import com.accion.consultation.entities.ProviderEntity;
import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.GetProviderDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProviderMapper implements EntityMapper<ProviderEntity, ProviderDTO> {
    private final AddressMapper addressMapper;
    private final SpecialityMapper specialityMapper;

    @Override
    public ProviderEntity toEntity(ProviderDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        ProviderEntity entity = new ProviderEntity();
        entity.setUserId(model.getUserId());
        entity.setUsername(model.getEmail());
        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setMaritalStatus(model.getMaritalStatus());
        entity.setSlotInMinutes(model.getSlotInMinutes());
        entity.setTimezone(model.getTimezone());

        entity.setSsn(model.getSsn());
        entity.setNpi(model.getNpi());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setLicenseExpirationDate(model.getLicenseExpirationDate());
        entity.setStateLicenseIssued(model.getStateLicenseIssued());
        entity.setAcceptingNewPatients(model.isAcceptingNewPatients());
        entity.setTimezone(model.getTimezone());

        entity.setBio(model.getBio());
        entity.setPhotoUrl(model.getPhotoUrl());
        entity.setWebsiteUrl(model.getWebsiteUrl());

        entity.setStatus(model.getStatus());

        entity.setAddresses(model
                .getAddresses()
                .stream()
                .map(addressMapper::toEntity)
                .collect(Collectors.toList()));

        return entity;
    }

    @Override
    public ProviderDTO toModel(ProviderEntity entity) {
        NameDTO personName = new NameDTO();
        personName.setGivenName(entity.getName().getGivenName());
        personName.setMiddleName(entity.getName().getMiddleName());
        personName.setFamilyName(entity.getName().getFamilyName());


        ProviderDTO model = new ProviderDTO();
        model.setUserId(entity.getUserId());
        model.setUsername(entity.getEmail());
        model.setName(personName);
        model.setEmail(entity.getEmail());
        model.setDob(entity.getDob());
        model.setAdministrativeSex(entity.getAdministrativeSex());
        model.setMaritalStatus(entity.getMaritalStatus());
        model.setTimezone(entity.getTimezone());

        model.setSsn(entity.getSsn());
        model.setNpi(entity.getNpi());
        model.setLicenseNumber(entity.getLicenseNumber());
        model.setLicenseExpirationDate(entity.getLicenseExpirationDate());
        model.setStateLicenseIssued(entity.getStateLicenseIssued());
        model.setAcceptingNewPatients(entity.isAcceptingNewPatients());
        model.setRoles(entity.getRoles().stream().map(RoleEntity::getName).toList());
        model.setSpecialities(entity.getSpecialities().stream().map(specialityMapper::toModel).toList());
        model.setBio(entity.getBio());
        model.setPhotoUrl(entity.getPhotoUrl());
        model.setWebsiteUrl(entity.getWebsiteUrl());

        model.setStatus(entity.getStatus());

        model.setAddresses(entity
                .getAddresses()
                .stream()
                .map(addressMapper::toModel)
                .collect(Collectors.toList()));

        return model;
    }

    public ProviderEntity toCreateProviderEntity(CreateProviderRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        ProviderEntity entity = new ProviderEntity();
        entity.setUsername(model.getEmail());
        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setMaritalStatus(model.getMaritalStatus());
        entity.setSlotInMinutes(model.getSlotInMinutes());
        entity.setTimezone(model.getTimezone());

        entity.setSsn(model.getSsn());
        entity.setNpi(model.getNpi());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setLicenseExpirationDate(model.getLicenseExpirationDate());
        entity.setStateLicenseIssued(model.getStateLicenseIssued());
        entity.setAcceptingNewPatients(model.isAcceptingNewPatients());

        entity.setBio(model.getBio());
        entity.setPhotoUrl(model.getPhotoUrl());
        entity.setWebsiteUrl(model.getWebsiteUrl());

        entity.setStatus(UserStatus.ACTIVE);

        return entity;
    }

    public ProviderEntity toUpdateProviderEntity(ProviderEntity entity, UpdateProviderRequestDTO model) {
        PersonName personName = new PersonName();
        personName.setGivenName(model.getName().getGivenName());
        personName.setMiddleName(model.getName().getMiddleName());
        personName.setFamilyName(model.getName().getFamilyName());

        entity.setName(personName);
        entity.setEmail(model.getEmail());
        entity.setDob(model.getDob());
        entity.setAdministrativeSex(model.getAdministrativeSex());
        entity.setMaritalStatus(model.getMaritalStatus());
        entity.setSlotInMinutes(model.getSlotInMinutes());
        entity.setTimezone(model.getTimezone());

        entity.setSsn(model.getSsn());
        entity.setNpi(model.getNpi());
        entity.setLicenseNumber(model.getLicenseNumber());
        entity.setLicenseExpirationDate(model.getLicenseExpirationDate());
        entity.setStateLicenseIssued(model.getStateLicenseIssued());
        entity.setAcceptingNewPatients(model.isAcceptingNewPatients());

        entity.setBio(model.getBio());
        entity.setPhotoUrl(model.getPhotoUrl());
        entity.setWebsiteUrl(model.getWebsiteUrl());

        return entity;
    }
}
