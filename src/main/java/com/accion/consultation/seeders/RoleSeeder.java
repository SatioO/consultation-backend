package com.accion.consultation.seeders;

import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(value = 1)
@AllArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("seeding roles");
        List.of("admin", "provider", "patient").forEach(role -> {
            CreateRoleRequestDTO createRoleRequestDTO = new CreateRoleRequestDTO();
            createRoleRequestDTO.setName(role);

            roleService.createRole(createRoleRequestDTO);
        });
    }
}
