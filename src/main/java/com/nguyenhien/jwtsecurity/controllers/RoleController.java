package com.nguyenhien.jwtsecurity.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nguyenhien.jwtsecurity.dtos.roles.RoleCreateUpdateDTO;
import com.nguyenhien.jwtsecurity.dtos.roles.RoleMasterDTO;
import com.nguyenhien.jwtsecurity.services.interfaces.IRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "APIs for managing roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    // Get all
    @GetMapping
    @Operation(summary = "Get all roles")
    @ApiResponse(responseCode = "200", description = "Return all roles")
    public ResponseEntity<List<RoleMasterDTO>> getAll() {
        var roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    // Get by name
    @GetMapping("/searchByName")
    @Operation(summary = "Search roles by role number")
    @ApiResponse(responseCode = "200", description = "Return roles that match the role number")
    public ResponseEntity<List<RoleMasterDTO>> searchByName(
            @RequestParam(required = false) String keyword) {
        var roles = roleService.findByName(keyword);
        return ResponseEntity.ok(roles);
    }

    // Get by Id
    @GetMapping("/{id}")
    @Operation(summary = "Get role by id")
    @ApiResponse(responseCode = "200", description = "Return role that match the id")
    public ResponseEntity<RoleMasterDTO> getById(@PathVariable UUID id) {
        var role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    // Create
    @PostMapping()
    @Operation(summary = "Create new role")
    @ApiResponse(responseCode = "200", description = "Return created role")
    @ApiResponse(responseCode = "400", description = "Return error message if create failed")
    public ResponseEntity<?> create(
            @Valid @RequestBody RoleCreateUpdateDTO roleDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var newRole = roleService.create(roleDTO);
        return ResponseEntity.ok(newRole);
    }

    // Update
    @PutMapping("/{id}")
    @Operation(summary = "Update role by id")
    @ApiResponse(responseCode = "200", description = "Return updated role")
    @ApiResponse(responseCode = "400", description = "Return error message if update failed")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @Valid @RequestBody RoleCreateUpdateDTO roleDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        var updatedRole = roleService.update(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    // Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role by id")
    @ApiResponse(responseCode = "200", description = "Return true if delete successfully")
    @ApiResponse(responseCode = "400", description = "Return error message if delete failed")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        var result = roleService.delete(id);
        return ResponseEntity.ok(result);
    }
}
