package com.accion.consultation.seeders;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.MaritalStatus;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.patient.CreatePatientRequestDTO;
import com.accion.consultation.models.dto.provider.CreateProviderRequestDTO;
import com.accion.consultation.service.AdminService;
import com.accion.consultation.service.PatientService;
import com.accion.consultation.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Order(value = 3)
@AllArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private final AdminService adminService;
    private final ProviderService providerService;
    private final PatientService patientService;

    @Override
    public void run(String... args) throws Exception {
        Optional<Boolean> seed = Arrays.stream(args).map(arg -> arg.equals("seed")).findFirst();

        if (seed.isPresent()){
            this.seedAdmin();
            this.seedProvider();
            this.seedPatient();
        }
    }

    private void seedAdmin() {
        System.out.println("seeding admin");
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        CreateAdminRequestDTO createAdminRequestDTO = new CreateAdminRequestDTO();
        createAdminRequestDTO.setName(NameDTO.builder().givenName("Master").familyName("User").build());
        createAdminRequestDTO.setEmail("master@accionlabs.com");
        createAdminRequestDTO.setAdministrativeSex(AdministrativeSex.A);
        createAdminRequestDTO.setDob(zonedDateTime.minusYears(18).toInstant());

        this.adminService.createAdmin(createAdminRequestDTO);

    }

    private void seedPatient() {
        System.out.println("seeding patient");
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        CreatePatientRequestDTO patient1 = new CreatePatientRequestDTO();
        patient1.setName(NameDTO.builder().givenName("John").familyName("Doe").build());
        patient1.setEmail("john.doe@accionlabs.com");
        patient1.setAdministrativeSex(AdministrativeSex.M);
        patient1.setMaritalStatus(MaritalStatus.M);
        patient1.setMrn("123456789");
        patient1.setSsn("987-65-4321");
        patient1.setDob(zonedDateTime.minusYears(32).toInstant());

        this.patientService.createPatient(patient1);

        CreatePatientRequestDTO patient2 = new CreatePatientRequestDTO();
        patient2.setName(NameDTO.builder().givenName("Emily").familyName("Davis").build());
        patient2.setEmail("emily.davis@accionlabs.com");
        patient2.setAdministrativeSex(AdministrativeSex.F);
        patient2.setMaritalStatus(MaritalStatus.S);
        patient2.setMrn("456789012");
        patient2.setSsn("345-67-8901");
        patient2.setDob(zonedDateTime.minusYears(18).toInstant());

        this.patientService.createPatient(patient2);
    }

    private void seedProvider() {
        System.out.println("seeding providers");
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        CreateProviderRequestDTO provider1 = new CreateProviderRequestDTO();
        provider1.setName(NameDTO.builder().givenName("Ted").familyName("Smith").build());
        provider1.setEmail("ted.smith@accionlabs.com");
        provider1.setAdministrativeSex(AdministrativeSex.M);
        provider1.setDob(zonedDateTime.minusYears(42).toInstant());
        provider1.setMaritalStatus(MaritalStatus.M);
        provider1.setNpi(1003810581L);
        provider1.setSsn("8005507691");
        provider1.setSlotInMinutes(15);
        provider1.setSpecialities(List.of(1L));
        provider1.setTimezone("Asia/Kolkata");
        this.providerService.createProvider(provider1);

        CreateProviderRequestDTO provider2 = new CreateProviderRequestDTO();
        provider2.setName(NameDTO.builder().givenName("James").familyName("Mitchell").build());
        provider2.setEmail("james.mitchell@accionlabs.com");
        provider2.setAdministrativeSex(AdministrativeSex.M);
        provider2.setDob(zonedDateTime.minusYears(50).toInstant());
        provider2.setMaritalStatus(MaritalStatus.M);
        provider2.setNpi(1003810582L);
        provider2.setSsn("9005507692");
        provider2.setSlotInMinutes(15);
        provider2.setSpecialities(List.of(2L));
        provider2.setTimezone("Asia/Kolkata");
        this.providerService.createProvider(provider2);

        CreateProviderRequestDTO provider3 = new CreateProviderRequestDTO();
        provider3.setName(NameDTO.builder().givenName("Michael").familyName("Thompson").build());
        provider3.setEmail("michael.thompson@accionlabs.com");
        provider3.setAdministrativeSex(AdministrativeSex.M);
        provider3.setDob(zonedDateTime.minusYears(55).toInstant());
        provider3.setMaritalStatus(MaritalStatus.D);
        provider3.setNpi(1003810584L);
        provider3.setSsn("8005507694");
        provider3.setSlotInMinutes(15);
        provider3.setSpecialities(List.of(3L));
        provider3.setTimezone("Asia/Kolkata");
        this.providerService.createProvider(provider3);

        CreateProviderRequestDTO provider4 = new CreateProviderRequestDTO();
        provider4.setName(NameDTO.builder().givenName("Ava").familyName("Johnson").build());
        provider4.setEmail("ava.johnson@accionlabs.com");
        provider4.setAdministrativeSex(AdministrativeSex.F);
        provider4.setDob(zonedDateTime.minusYears(40).toInstant());
        provider4.setMaritalStatus(MaritalStatus.M);
        provider4.setNpi(1003810585L);
        provider4.setSsn("8005507695");
        provider4.setSlotInMinutes(15);
        provider4.setSpecialities(List.of(4L));
        provider4.setTimezone("Asia/Kolkata");
        this.providerService.createProvider(provider4);

        CreateProviderRequestDTO provider5 = new CreateProviderRequestDTO();
        provider5.setName(NameDTO.builder().givenName("Ethan").familyName("Davis").build());
        provider5.setEmail("ethan.davis@accionlabs.com");
        provider5.setAdministrativeSex(AdministrativeSex.M);
        provider5.setDob(zonedDateTime.minusYears(38).toInstant());
        provider5.setMaritalStatus(MaritalStatus.S);
        provider5.setNpi(1003810586L);
        provider5.setSsn("8005507696");
        provider5.setSlotInMinutes(15);
        provider5.setSpecialities(List.of(1L));
        provider5.setTimezone("Asia/Kolkata");
        this.providerService.createProvider(provider5);

    }
}
