package org.example.suividestaches.controllers;

import org.example.suividestaches.models.Taches;
import org.example.suividestaches.services.TachesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("taches")
public class TachesController {
    @Autowired
    private TachesService tacheService;

    @PostMapping("/create/{projetId}")
    public Taches createTaches(@RequestBody Taches tache, @PathVariable Long projetId) {
        return tacheService.createTaches(tache, projetId);
    }

    @GetMapping
    public List<Taches> getAllTachess() {
        return tacheService.getAllTachess();
    }

    @GetMapping("/{id}")
    public Taches getTachesById(@PathVariable Long id) {
        return tacheService.getTachesById(id);
    }

    @PutMapping("/{id}")
    public Taches updateTaches(@PathVariable Long id, @RequestBody Taches tacheDetails) {
        return tacheService.updateTaches(id, tacheDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTaches(@PathVariable Long id) {
        tacheService.deleteTaches(id);
    }
}
