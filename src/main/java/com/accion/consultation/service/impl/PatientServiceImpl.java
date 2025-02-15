package com.accion.consultation.service.impl;

import com.accion.consultation.entities.AppointmentEntity;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.UserAddressEntity;
import com.accion.consultation.exceptions.RoleNotFoundException;
import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.mappers.AddressMapper;
import com.accion.consultation.mappers.AppointmentMapper;
import com.accion.consultation.mappers.PatientMapper;
import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.appointment.AppointmentDTO;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import com.accion.consultation.repositories.AppointmentRepository;
import com.accion.consultation.repositories.PatientRepository;
import com.accion.consultation.repositories.RoleRepository;
import com.accion.consultation.repositories.UserAddressRepository;
import com.accion.consultation.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserAddressRepository userAddressRepository;
    private final AppointmentRepository appointmentRepository;
    private final RoleRepository roleRepository;
    private final PatientMapper patientMapper;
    private final AddressMapper addressMapper;
    private final AppointmentMapper appointmentMapper;

    public PatientServiceImpl(PatientRepository patientRepository, UserAddressRepository userAddressRepository, AppointmentRepository appointmentRepository, RoleRepository roleRepository, PatientMapper patientMapper, AddressMapper addressMapper, AppointmentMapper appointmentMapper) {
        this.patientRepository = patientRepository;
        this.userAddressRepository = userAddressRepository;
        this.appointmentRepository = appointmentRepository;
        this.roleRepository = roleRepository;
        this.patientMapper = patientMapper;
        this.addressMapper = addressMapper;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentDTO> findAppointments(long providerId, Pageable page) {
        Page<AppointmentEntity> appointments = appointmentRepository.findAppointmentByPatientUserId(providerId, page);
        return appointments.get().map(appointmentMapper::toModel).toList();
    }

    public List<PatientDTO> findPatients() {
        return this.patientRepository
                .findByRoles_Name(AdministrativeSex.Role.PATIENT.getDescription())
                .stream().map(patientMapper::toModel)
                .collect(Collectors.toList());
    }

    public Optional<PatientDTO> findPatientById(long patientId) {
        return this.patientRepository.findById(patientId).map(patientMapper::toModel);
    }

    public PatientDTO createPatient(@RequestBody CreatePatientRequestDTO body) {
        return roleRepository.findByName(AdministrativeSex.Role.PATIENT.getDescription()).map(role -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode(body.getPassword());

            PatientEntity patient = patientMapper.toCreatePatientEntity(body);
            patient.setPassword(encryptedPassword);

            patient.setRoles(List.of(role));

            List<UserAddressEntity> addresses = body.getAddresses()
                    .stream()
                    .map(addressMapper::toEntity)
                    .peek(address -> address.setUser(patient))
                    .collect(Collectors.toList());
            patient.setAddresses(addresses);

            PatientEntity savedPatient = patientRepository.save(patient);
            return patientMapper.toModel(savedPatient);
        }).orElseThrow(() -> new RoleNotFoundException(AdministrativeSex.Role.PATIENT.getDescription()));
    }

    @Transactional
    public PatientDTO updatePatient(long patientId, UpdatePatientRequestDTO body) {
        return patientRepository.findById(patientId)
                .map(patient -> this.patientRepository.save(this.patientMapper.toUpdatePatientEntity(patient, body)))
                .map(patient -> {
                    List<UserAddressEntity> addresses = body.getAddresses().stream()
                            .map(addressMapper::toEntity)
                            .collect(Collectors.toList());

                    userAddressRepository.saveAll(addresses);
                    return patient;
                })
                .map(patientMapper::toModel)
                .orElseThrow(() -> new UserNotFoundException(patientId));
    }

    public void deletePatient(long patientId) {
        this.patientRepository.findById(patientId).ifPresent(this.patientRepository::delete);
    }
}
