package com.accion.consultation.seeders;

import com.accion.consultation.models.SpecialityCategory;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.service.SpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(value = 2)
@AllArgsConstructor
public class SpecialitySeeder implements CommandLineRunner {
    private final SpecialityService specialityService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("seeding specialities");
        List<String> behavioral = List.of("Psychiatrists", "Psychologists");
        List<String> non_behavioral = List.of("Cardiologists", "Dermatologists", "Endocrinologists", "Neurologists", "Oncologists");
        List<String> therapy = List.of("Physical Therapists", "Occupational Therapists", "Speech-Language Pathologists");

        try {
            behavioral.forEach(speciality -> {
                CreateSpecialityRequestDTO createSpecialityRequestDTO = new CreateSpecialityRequestDTO();
                createSpecialityRequestDTO.setName(speciality);
                createSpecialityRequestDTO.setDescription("provider with " + speciality);
                createSpecialityRequestDTO.setCategory(SpecialityCategory.BEHAVIORAL);
                specialityService.createSpeciality(createSpecialityRequestDTO);
            });

            non_behavioral.forEach(speciality -> {
                CreateSpecialityRequestDTO createSpecialityRequestDTO = new CreateSpecialityRequestDTO();
                createSpecialityRequestDTO.setName(speciality);
                createSpecialityRequestDTO.setDescription("provider with " + speciality);
                createSpecialityRequestDTO.setCategory(SpecialityCategory.NON_BEHAVIORAL);
                specialityService.createSpeciality(createSpecialityRequestDTO);
            });

            therapy.forEach(speciality -> {
                CreateSpecialityRequestDTO createSpecialityRequestDTO = new CreateSpecialityRequestDTO();
                createSpecialityRequestDTO.setName(speciality);
                createSpecialityRequestDTO.setDescription("provider with " + speciality);
                createSpecialityRequestDTO.setCategory(SpecialityCategory.THERAPY);

                specialityService.createSpeciality(createSpecialityRequestDTO);
            });
        } catch(Exception e) {
            System.out.println("Failed to insert specialities: " + e.getMessage());
        }
    }
}
