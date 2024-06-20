package com.accion.consultation.service.impl;

import com.accion.consultation.entities.*;
import com.accion.consultation.exceptions.RoleNotFoundException;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AddressMapper;
import com.accion.consultation.mappers.AppointmentMapper;
import com.accion.consultation.mappers.ProviderMapper;
import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.appointment.AppointmentSlotDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.provider.UpdateProviderRequestDTO;
import com.accion.consultation.repositories.*;
import com.accion.consultation.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.*;
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
    private final AppointmentMapper appointmentMapper;

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
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.of(foundProvider.getTimezone()));

        //  Start and end of the day
        LocalDateTime startOfDay = localDateTime.truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfDay = startOfDay.truncatedTo(ChronoUnit.DAYS).plusDays(1);

        List<AppointmentSlotDTO> slots = this.generateSlots(localDateTime, foundProvider.getSlotInMinutes())
            .stream()
            .filter(slot -> slot.getStartDateTime().isAfter(currentDateTime)).toList();

        List<AppointmentEntity> appointments = appointmentRepository.findApptsByProviderId(providerId, startOfDay, endOfDay);

        slots.forEach(slot -> {
            appointments.forEach(appointment -> {
                if(appointment.getDateTime().isEqual(slot.getStartDateTime().atZone(ZoneId.of(foundProvider.getTimezone())))) {
                    slot.setAvailable(false);
                }
            });
        });

        return slots;
    }

    @Override
    public List<AppointmentDTO> findAppointments(long providerId, Pageable page) {
        Page<AppointmentEntity> appointments = appointmentRepository.findAppointmentByProviderUserId(providerId, page);
        return appointments.get().map(appointmentMapper::toModel).toList();
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

    public List<AppointmentSlotDTO> generateSlots(LocalDateTime date, int gapInMinutes) {
        List<AppointmentSlotDTO> timeSlots = new ArrayList<>();

        // Adjust startDateTime to the nearest previous interval
        long minutes = date.getMinute();
        long adjustment = minutes % gapInMinutes;
        LocalDateTime adjustedStartTime = date.minusMinutes(adjustment).truncatedTo(ChronoUnit.MINUTES);

        // Define desired start and end times for slots (9 AM and 6 PM)
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0); // 6 PM in 24-hour format

        // Truncate adjustedStartTime to today's date
        adjustedStartTime = adjustedStartTime.toLocalDate().atTime(startTime);

        // End of the day (considering 6 PM)
        LocalDateTime endOfDay = adjustedStartTime.toLocalDate().atTime(endTime);

        // Generate slots only between start and end times
        LocalDateTime nextSlot = adjustedStartTime;
        while (nextSlot.isBefore(endOfDay) && nextSlot.toLocalTime().isBefore(endTime)) {
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
