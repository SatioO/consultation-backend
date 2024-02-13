package com.accion.consultation.service.impl;

import com.accion.consultation.constants.RoleEnum;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.UserEntity;
import com.accion.consultation.mappers.PatientMapper;
import com.accion.consultation.models.PatientStatus;
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

    public PatientServiceImpl(PatientRepository patientRepository, RoleRepository roleRepository, UserRepository userRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.patientMapper = patientMapper;
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
        return roleRepository.findByName(RoleEnum.PATIENT.getDescription()).map(role -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode("helloworld");

            PatientEntity patient = patientMapper.toCreatePatientEntity(body);
            patient.setRole(role);
            patient.setPassword(encryptedPassword);

            PatientEntity savedPatient = patientRepository.save(patient);
            return patientMapper.toModel(savedPatient);
        }).orElseThrow(() -> {
            log.error("Role Missing: " + RoleEnum.PATIENT.getDescription());
            return new RuntimeException("Error while creating new patient");
        });
    }

    public PatientDTO updatePatient(long patientId, UpdatePatientRequestDTO body) {
        PatientEntity patient = this.patientMapper.toUpdatePatientEntity(body);
        return patientMapper.toModel(this.patientRepository.save(patient));
    }

    public void deletePatient(long patientId) {
        this.patientRepository.findById(patientId).ifPresent(patient -> {
            patient.setStatus(PatientStatus.INACTIVE);
            this.patientRepository.save(patient);
        });
    }
}
