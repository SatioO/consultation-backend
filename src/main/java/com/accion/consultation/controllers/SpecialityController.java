package com.accion.consultation.controllers;

import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.models.dto.speciality.SpecialityDTO;
import com.accion.consultation.models.dto.speciality.CreateSpecialityRequestDTO;
import com.accion.consultation.models.dto.speciality.UpdateSpecialityRequestDTO;
import com.accion.consultation.service.SpecialityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/speciality")
public class SpecialityController {
    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> findSpecialities() {
        List<SpecialityDTO> foundSpecialities = specialityService.findSpecialities();
        return ResponseEntity.status(HttpStatus.OK).body(foundSpecialities);
    }

    @GetMapping("/{specialityId}")
    public ResponseEntity<SpecialityDTO> findSpecialityById(@PathVariable long specialityId) {
        return specialityService.findSpecialityById(specialityId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(specialityId));
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
