package com.accion.consultation.mappers;

public interface EntityMapper<E, M> {
    E toEntity(M model);
    M toModel(E entity);
}
