package org.example.suividestaches.controllers;

import org.example.suividestaches.models.ChefDequipe;
import org.example.suividestaches.services.ChefDequipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("chefDequipe")
public class ChefDequipeController {
    @Autowired
    private ChefDequipeService chefDequipeService;

    // Get all ChefDequipe
    @GetMapping("/all")
    public ResponseEntity<List<ChefDequipe>> getAllChefDequipe() {
        List<ChefDequipe> chefDequipes = chefDequipeService.getAllChefDequipe();
        return ResponseEntity.ok(chefDequipes);
    }

    // Get ChefDequipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChefDequipe> getChefDequipeById(@PathVariable Long id) {
        ChefDequipe chefDequipe = chefDequipeService.getChefDequipeById(id);
        return chefDequipe != null ? ResponseEntity.ok(chefDequipe) : ResponseEntity.notFound().build();
    }

    // Create new ChefDequipe
    @PostMapping("/create")
    public ResponseEntity<ChefDequipe> createChefDequipe(@RequestBody ChefDequipe chefDequipe) {
        ChefDequipe createdChefDequipe = chefDequipeService.createChefDequipe(chefDequipe);
        return ResponseEntity.ok(createdChefDequipe);
    }

    // Update ChefDequipe by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ChefDequipe> updateChefDequipe(@PathVariable Long id, @RequestBody ChefDequipe chefDequipe) {
        ChefDequipe updatedChefDequipe = chefDequipeService.updateChefDequipe(id, chefDequipe);
        return ResponseEntity.ok(updatedChefDequipe);
    }

    // Delete ChefDequipe by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteChefDequipe(@PathVariable Long id) {
        chefDequipeService.deleteChefDequipe(id);
        return ResponseEntity.noContent().build();
    }
}
