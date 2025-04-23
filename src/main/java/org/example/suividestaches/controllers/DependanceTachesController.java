package org.example.suividestaches.controllers;

import org.example.suividestaches.models.DependanceTaches;
import org.example.suividestaches.services.DependanceTachesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dependanceTaches")
public class DependanceTachesController {
    @Autowired
    private DependanceTachesService dependanceTachesService;

    // Create dependency between two tasks
    @PostMapping("/create")
    public ResponseEntity<DependanceTaches> createDependency(@RequestBody DependanceTaches dependanceTaches) {
        try {
            // Retrieve the tasks using the IDs from the DependanceTaches model
            Long precedenteId = dependanceTaches.getTachePrecedente().getId();
            Long suivanteId = dependanceTaches.getTacheSuivante().getId();

            // Create the dependency with the given task IDs
            DependanceTaches dependance = dependanceTachesService.createDependance(precedenteId, suivanteId);

            return ResponseEntity.ok(dependance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }




    // Modify an existing dependency
    @PutMapping("/modify/{dependanceId}")
    public ResponseEntity<DependanceTaches> modifyDependency(@PathVariable Long dependanceId,
                                                             @RequestParam Long precedenteId,
                                                             @RequestParam Long suivanteId) {
        try {
            DependanceTaches dependance = dependanceTachesService.modifyDependance(dependanceId, precedenteId, suivanteId);
            return ResponseEntity.ok(dependance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    // Delete a dependency
    @DeleteMapping("/delete/{dependanceId}")
    public ResponseEntity<Void> deleteDependency(@PathVariable Long dependanceId) {
        dependanceTachesService.deleteDependance(dependanceId);
        return ResponseEntity.noContent().build();
    }

    // Check if a task is ready to start (i.e., if all predecessor tasks are completed)
    @GetMapping("/ready/{tacheId}")
    public ResponseEntity<Boolean> isTaskReady(@PathVariable Long tacheId) {
        boolean isReady = dependanceTachesService.arePredecessorsCompleted(tacheId);
        return ResponseEntity.ok(isReady);
    }



}
