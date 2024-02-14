package com.accion.consultation.mappers;

import com.accion.consultation.entities.UserAddressEntity;
import com.accion.consultation.models.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements EntityMapper<UserAddressEntity, AddressDTO> {
    @Override
    public UserAddressEntity toEntity(AddressDTO model) {
        UserAddressEntity entity = new UserAddressEntity();
        entity.setId(model.getId());
        entity.setType(model.getType());
        entity.setAddress_1(model.getAddress_1());
        entity.setAddress_2(model.getAddress_2());
        entity.setCity(model.getCity());
        entity.setState(model.getState());
        entity.setCountry(model.getCountry());
        entity.setZip(model.getZip());
        return entity;
    }

    @Override
    public AddressDTO toModel(UserAddressEntity entity) {
        AddressDTO model = new AddressDTO();
        model.setId(entity.getId());
        model.setType(entity.getType());
        model.setAddress_1(entity.getAddress_1());
        model.setAddress_2(entity.getAddress_2());
        model.setCity(entity.getCity());
        model.setState(entity.getState());
        model.setCountry(entity.getCountry());
        model.setZip(entity.getZip());

        return model;
    }
}
