package com.accion.consultation.seeders;

import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Order(value = 1)
@AllArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Optional<Boolean> seed = Arrays.stream(args).map(arg -> arg.equals("seed")).findFirst();

        if (seed.isPresent()){
            System.out.println("seeding roles");

            try {
                List.of("admin", "provider", "patient").forEach(role -> {
                    CreateRoleRequestDTO createRoleRequestDTO = new CreateRoleRequestDTO();
                    createRoleRequestDTO.setName(role);

                    roleService.createRole(createRoleRequestDTO);
                });
            } catch (Exception e) {
                System.out.println("Skipping role seeding");
            }
        }

    }
}
