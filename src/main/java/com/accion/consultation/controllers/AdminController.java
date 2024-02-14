package com.accion.consultation.controllers;

import com.accion.consultation.exceptions.UserNotFoundException;
import com.accion.consultation.models.dto.admin.AdminDTO;
import com.accion.consultation.models.dto.admin.CreateAdminRequestDTO;
import com.accion.consultation.models.dto.admin.UpdateAdminRequestDTO;
import com.accion.consultation.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAdmins() {
        List<AdminDTO> foundAdmins = adminService.findAdmins();
        return ResponseEntity.status(HttpStatus.OK).body(foundAdmins);
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable long adminId) {
        return adminService.findAdminById(adminId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UserNotFoundException(adminId));
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody @Valid CreateAdminRequestDTO body) {
        AdminDTO createdAdmin = adminService.createAdmin(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable long adminId, @RequestBody @Valid UpdateAdminRequestDTO body) {
        AdminDTO updatedAdmin = adminService.updateAdmin(adminId, body);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAdmin);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
