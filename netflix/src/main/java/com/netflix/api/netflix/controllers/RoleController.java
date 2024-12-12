package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.models.Role;
import com.netflix.api.netflix.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        return roleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role roleDetails) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setName(roleDetails.getName());
                    return ResponseEntity.ok(roleRepository.save(role));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        return roleRepository.findById(id)
                .map(role -> {
                    roleRepository.delete(role);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}