package com.accion.consultation.controllers;

import com.accion.consultation.exceptions.RoleNotFoundException;
import com.accion.consultation.models.dto.role.CreateRoleRequestDTO;
import com.accion.consultation.models.dto.role.RoleDTO;
import com.accion.consultation.models.dto.role.UpdateRoleRequestDTO;
import com.accion.consultation.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles() {
        return ResponseEntity.ok().body(this.roleService.getRoles());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable long roleId) {
        return ResponseEntity.ok().body(this.roleService.getRoleById(roleId).orElseThrow(() -> new RoleNotFoundException(roleId)));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody CreateRoleRequestDTO role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.createRole(role));
    }

    @PutMapping("/{role_id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable long roleId, @Valid @RequestBody UpdateRoleRequestDTO role) {
        return ResponseEntity.ok().body(this.roleService.updateRole(roleId, role));
    }

    @DeleteMapping("/{role_id}")
    public ResponseEntity<Void> deleteRole(@PathVariable long roleId) {
        this.roleService.deleteRole(roleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
