package org.example.suividestaches.controllers;

import org.example.suividestaches.models.Enum.Statut;
import org.example.suividestaches.models.Projet;
import org.example.suividestaches.models.Taches;
import org.example.suividestaches.services.EquipeService;
import org.example.suividestaches.services.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("projet")
public class ProjetController {
    @Autowired
    private ProjetService projetService;

    @PostMapping("/create/{equipeId}")
    public Projet createProjet(@RequestBody Projet projet, @PathVariable Long equipeId) {
        return projetService.createProjet(projet, equipeId);
    }

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetService.getAllProjets();
    }

    @GetMapping("/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetService.getProjetById(id);
    }

    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet projetDetails) {
        return projetService.updateProjet(id, projetDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
    }
}
