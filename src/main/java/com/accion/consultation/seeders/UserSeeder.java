package com.accion.consultation.seeders;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Order(value = 3)
@AllArgsConstructor
public class UserSeeder implements CommandLineRunner {
    private  final AdminService adminService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("seeding users");

        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        CreateAdminRequestDTO createAdminRequestDTO = new CreateAdminRequestDTO();
        createAdminRequestDTO.setName(NameDTO.builder().givenName("master").familyName("user").build());
        createAdminRequestDTO.setEmail("master@accionlabs.com");
        createAdminRequestDTO.setAdministrativeSex(AdministrativeSex.A);
        createAdminRequestDTO.setDob(zonedDateTime.minusYears(18).toInstant());

        this.adminService.createAdmin(createAdminRequestDTO);
    }
}
