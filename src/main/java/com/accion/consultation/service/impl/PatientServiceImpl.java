package com.accion.consultation.service.impl;

import com.accion.consultation.constants.RoleEnum;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.entities.UserAddressEntity;
import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.mappers.AddressMapper;
import com.accion.consultation.mappers.PatientMapper;
import com.accion.consultation.models.UserStatus;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.patient.PatientDTO;
import com.accion.consultation.models.dto.patient.UpdatePatientRequestDTO;
import com.accion.consultation.repositories.PatientRepository;
import com.accion.consultation.repositories.RoleRepository;
import com.accion.consultation.repositories.UserRepository;
import com.accion.consultation.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;
    private final AddressMapper addressMapper;

    public PatientServiceImpl(PatientRepository patientRepository, RoleRepository roleRepository, UserRepository userRepository, PatientMapper patientMapper, AddressMapper addressMapper) {
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.patientMapper = patientMapper;
        this.addressMapper = addressMapper;
    }

    public List<PatientDTO> findPatients() {
        return this.patientRepository.findAll().stream().map(patientMapper::toModel).collect(Collectors.toList());
    }

    public List<UserEntity> findUsers() {
        return this.userRepository.findAll();
    }

    public Optional<PatientDTO> findPatientById(long patientId) {
        return this.patientRepository.findById(patientId).map(patientMapper::toModel);
    }

    public PatientDTO createPatient(@RequestBody CreatePatientRequestDTO body) {
        Optional<RoleEntity> foundRole = roleRepository.findByName(RoleEnum.PATIENT.getDescription());
        if(foundRole.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode("helloworld");

            PatientEntity patient = patientMapper.toCreatePatientEntity(body);
            patient.setPassword(encryptedPassword);

            RoleEntity role = foundRole.get();
            patient.setRoles(List.of(role));

            List<UserAddressEntity> addresses = body.getAddresses()
                    .stream()
                    .map(addressMapper::toEntity)
                    .peek(address -> address.setUser(patient))
                    .collect(Collectors.toList());
            patient.setAddresses(addresses);

            PatientEntity savedPatient = patientRepository.save(patient);
            return patientMapper.toModel(savedPatient);
        }

        log.error("Role Missing: " + RoleEnum.PATIENT.getDescription());
        throw new RuntimeException("Error while creating new patient");
    }

    public PatientDTO updatePatient(long patientId, UpdatePatientRequestDTO body) {
        PatientEntity patient = this.patientMapper.toUpdatePatientEntity(body);
        return patientMapper.toModel(this.patientRepository.save(patient));
    }

    public void deletePatient(long patientId) {
        this.patientRepository.findById(patientId).ifPresent(patient -> {
            patient.setStatus(UserStatus.INACTIVE);
            this.patientRepository.save(patient);
        });
    }
}
