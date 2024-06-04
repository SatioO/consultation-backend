package com.accion.consultation.service.impl;

import com.accion.consultation.entities.*;
import com.accion.consultation.exceptions.RoleNotFoundException;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AddressMapper;
import com.accion.consultation.mappers.ProviderMapper;
import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.appointment.AppointmentSlotDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;
import com.accion.consultation.repositories.*;
import com.accion.consultation.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;
    private final UserAddressRepository userAddressRepository;
    private final RoleRepository roleRepository;
    private final AddressMapper addressMapper;
    private final SpecialityRepository specialityRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<ProviderDTO> findProviders() {
        return this.providerRepository
                .findByRoles_Name(AdministrativeSex.Role.PROVIDER.getDescription())
                .stream().map(providerMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentSlotDTO> getAvailableSlots(Long providerId, Instant date) {
        ProviderEntity foundProvider = this.providerRepository.findById(providerId).orElseThrow(() -> new UserNotFoundException(providerId));

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date, ZoneId.of(foundProvider.getTimezone()));

        //  Start of the day
        LocalDateTime startOfDay = localDateTime.truncatedTo(ChronoUnit.DAYS);
        // End of the day
        LocalDateTime endOfDay = startOfDay.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        List<AppointmentSlotDTO> slots = this.generateSlots(startOfDay, foundProvider.getSlotInMinutes());

        List<AppointmentEntity> appointments = appointmentRepository.findAppointmentsByProviderId(providerId, startOfDay, endOfDay);

        slots.forEach(slot -> {
            appointments.forEach(appointment -> {
                if(appointment.getDateTime().isEqual(ChronoZonedDateTime.from(slot.getStartDateTime()))) {
                    slot.setAvailable(false);
                }
            });
        });

        return slots;
    }

    @Override
    public Optional<ProviderDTO> findProviderById(long providerId) {
        return this.providerRepository.findById(providerId).map(providerMapper::toModel);
    }

    @Override
    public ProviderDTO createProvider(CreateProviderRequestDTO body) {
        RoleEntity foundRole = roleRepository
            .findByName(AdministrativeSex.Role.PROVIDER.getDescription())
            .orElseThrow(() -> new RoleNotFoundException(AdministrativeSex.Role.PROVIDER.getDescription()));

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

    public List<AppointmentSlotDTO> generateSlots(LocalDateTime startDate, int gapInMinutes) {
        List<AppointmentSlotDTO> timeSlots = new ArrayList<>();

        // Adjust startDateTime to the nearest previous interval
        long minutes = startDate.getMinute();
        long adjustment = minutes % gapInMinutes;
        LocalDateTime adjustedStartTime = startDate.minusMinutes(adjustment).truncatedTo(ChronoUnit.MINUTES);

        // End of the day
        LocalDateTime endOfDay = adjustedStartTime.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        // Generate the slots based on the gap
        LocalDateTime nextSlot = adjustedStartTime;
        while (nextSlot.isBefore(endOfDay)) {
            AppointmentSlotDTO dto = new AppointmentSlotDTO();
            dto.setStartDateTime(nextSlot);
            dto.setEndDateTime(nextSlot.plusMinutes(gapInMinutes));
            dto.setAvailable(true);
            timeSlots.add(dto);
            nextSlot = nextSlot.plusMinutes(gapInMinutes);
        }

        return timeSlots;
    }
}
