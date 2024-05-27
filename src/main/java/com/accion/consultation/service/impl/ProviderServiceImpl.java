package com.accion.consultation.service.impl;

import com.accion.consultation.entities.ProviderEntity;
import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.entities.SpecialityEntity;
import com.accion.consultation.entities.UserAddressEntity;
import com.accion.consultation.enums.RoleEnum;
import com.accion.consultation.exceptions.RoleNotFoundException;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AddressMapper;
import com.accion.consultation.mappers.ProviderMapper;
import com.accion.consultation.mappers.SpecialityMapper;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;
import com.accion.consultation.repositories.ProviderRepository;
import com.accion.consultation.repositories.RoleRepository;
import com.accion.consultation.repositories.SpecialityRepository;
import com.accion.consultation.repositories.UserAddressRepository;
import com.accion.consultation.service.ProviderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final UserAddressRepository userAddressRepository;
    private final RoleRepository roleRepository;
    private final AddressMapper addressMapper;
    private final SpecialityMapper specialityMapper;
    private final SpecialityRepository specialityRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository, ProviderMapper providerMapper, UserAddressRepository userAddressRepository, RoleRepository roleRepository, AddressMapper addressMapper, SpecialityMapper specialityMapper, SpecialityRepository specialityRepository) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
        this.userAddressRepository = userAddressRepository;
        this.roleRepository = roleRepository;
        this.addressMapper = addressMapper;
        this.specialityMapper = specialityMapper;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public List<ProviderDTO> findProviders() {
        return this.providerRepository
                .findByRoles_Name(RoleEnum.PROVIDER.getDescription())
                .stream().map(providerMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProviderDTO> findProviderById(long providerId) {
        return this.providerRepository.findById(providerId).map(providerMapper::toModel);
    }

    @Override
    public ProviderDTO createProvider(CreateProviderRequestDTO body) {
        RoleEntity foundRole = roleRepository
            .findByName(RoleEnum.PROVIDER.getDescription())
            .orElseThrow(() -> new RoleNotFoundException(RoleEnum.PROVIDER.getDescription()));

        List<SpecialityEntity> foundSpecialities = specialityRepository.findByIdIn(body.getSpecialities());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPassword = encoder.encode("helloworld");

        ProviderEntity provider = providerMapper.toCreateProviderEntity(body);
        provider.setPassword(encryptedPassword);
        provider.setRoles(List.of(foundRole));
        provider.setSpecialities(foundSpecialities);

        List<UserAddressEntity> addresses = body.getAddresses()
            .stream()
            .map(addressMapper::toEntity)
            .peek(address -> address.setUser(provider))
            .toList();
        provider.setAddresses(addresses);

        ProviderEntity savedProvider = providerRepository.save(provider);
        return providerMapper.toModel(savedProvider);
    }

    @Override
    public ProviderDTO updateProvider(long providerId, UpdateProviderRequestDTO body) {
        return providerRepository.findById(providerId)
                .map(provider -> this.providerRepository.save(this.providerMapper.toUpdateProviderEntity(provider, body)))
                .map(provider -> {
                    List<UserAddressEntity> addresses = body.getAddresses().stream()
                            .map(addressMapper::toEntity)
                            .collect(Collectors.toList());

                    userAddressRepository.saveAll(addresses);
                    return provider;
                })
                .map(providerMapper::toModel)
                .orElseThrow(() -> new UserNotFoundException(providerId));
    }

    @Override
    public void deleteProvider(long providerId) {
        this.providerRepository.findById(providerId).ifPresent(provider -> {
            provider.setStatus(UserStatus.INACTIVE);
            this.providerRepository.save(provider);
        });
    }
}
