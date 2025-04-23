package org.example.suividestaches.controllers;

import org.example.suividestaches.models.Membre;
import org.example.suividestaches.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("membre")
public class MembreController {
    @Autowired
    private MembreService membreService;

    @GetMapping("/all")
    public ResponseEntity<List<Membre>> getAllMembres() {
        return ResponseEntity.ok(membreService.getAllMembres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membre> getMembreById(@PathVariable Long id) {
        return ResponseEntity.ok(membreService.getMembreById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Membre> createMembre(@RequestBody Membre membre) {
        return ResponseEntity.ok(membreService.createMembre(membre));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Membre> updateMembre(@PathVariable Long id, @RequestBody Membre membre) {
        return ResponseEntity.ok(membreService.updateMembre(id, membre));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable Long id) {
        membreService.deleteMembre(id);
        return ResponseEntity.noContent().build();
    }
}
