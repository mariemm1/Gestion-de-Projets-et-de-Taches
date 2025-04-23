package org.example.suividestaches.controllers;

import org.example.suividestaches.models.JoursFeries;
import org.example.suividestaches.services.JoursFeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jourFerie")
public class JoursFeriesController {
    @Autowired
    private JoursFeriesService joursFeriesService;

    @GetMapping("/allJoursFeries")
    public List<JoursFeries> getAll() {
        return joursFeriesService.getAll();
    }

    @PostMapping("/createJourFerie")
    public JoursFeries create(@RequestBody JoursFeries jf) {
        return joursFeriesService.save(jf);
    }

    @DeleteMapping("/jourFerie/{id}")
    public void delete(@PathVariable Long id) {
        joursFeriesService.delete(id);
    }
}
