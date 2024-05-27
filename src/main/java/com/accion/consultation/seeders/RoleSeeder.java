package com.accion.consultation.seeders;

import com.accion.consultation.models.AdministrativeSex;
import com.accion.consultation.models.dto.NameDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.service.AdminService;
import com.accion.consultation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationRunner {
    private final RoleService roleService;
    private final AdminService adminService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.seedRoles();
        this.seedAdmin();
    }

    private void seedRoles() {
        System.out.println("seeding roles");
        List.of("admin", "provider", "patient").forEach(role -> {
            CreateRoleRequestDTO createRoleRequestDTO = new CreateRoleRequestDTO();
            createRoleRequestDTO.setName(role);

            roleService.createRole(createRoleRequestDTO);
        });
    }

    private void seedAdmin() {
        System.out.println("seeding admins");

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
