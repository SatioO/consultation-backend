package com.accion.consultation.controllers;

import com.accion.consultation.entities.ProviderEntity;
import com.accion.consultation.exceptions.SpecialityNotFound;
import com.accion.consultation.models.SpecialityCategory;
import com.accion.consultation.models.dto.provider.GetProviderDTO;
import com.accion.consultation.models.dto.provider.ProviderDTO;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.speciality.UpdateSpecialityRequestDTO;
import com.accion.consultation.service.SpecialityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/specialities")
@AllArgsConstructor
public class SpecialityController {
    private final SpecialityService specialityService;

    @GetMapping("/categories")
    public ResponseEntity<SpecialityCategory[]> getCategories() {
        return ResponseEntity.ok().body(SpecialityCategory.values());
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> findSpecialities(@RequestParam(value = "category", required = false) SpecialityCategory category) {
        List<SpecialityDTO> foundSpecialities = specialityService.findSpecialities(category);
        return ResponseEntity.status(HttpStatus.OK).body(foundSpecialities);
    }

    @GetMapping("/{specialityId}/providers")
    public ResponseEntity<List<GetProviderDTO>> findProviders(@PathVariable long specialityId) {
        List<GetProviderDTO> foundProviders = specialityService.findProviders(specialityId);
        return ResponseEntity.status(HttpStatus.OK).body(foundProviders);
    }

    @GetMapping("/{specialityId}")
    public ResponseEntity<SpecialityDTO> findSpecialityById(@PathVariable long specialityId) {
        return specialityService.findSpecialityById(specialityId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new SpecialityNotFound(specialityId));
    }

    @PostMapping
    public ResponseEntity<SpecialityDTO> createSpeciality(@RequestBody @Valid CreateSpecialityRequestDTO body) {
        SpecialityDTO createdSpeciality = specialityService.createSpeciality(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpeciality);
    }

    @PutMapping("/{specialityId}")
    public ResponseEntity<SpecialityDTO> updateSpeciality(@PathVariable long specialityId, @RequestBody @Valid UpdateSpecialityRequestDTO body) {
        SpecialityDTO updatedSpeciality = specialityService.updateSpeciality(specialityId, body);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSpeciality);
    }

    @DeleteMapping("/{specialityId}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable long specialityId) {
        specialityService.deleteSpeciality(specialityId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
