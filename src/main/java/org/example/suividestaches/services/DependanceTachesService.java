package org.example.suividestaches.services;

import org.example.suividestaches.models.DependanceTaches;
import org.example.suividestaches.models.Enum.Statut;
import org.example.suividestaches.models.Taches;
import org.example.suividestaches.repositories.DependanceTachesRepository;
import org.example.suividestaches.repositories.TachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependanceTachesService {
    @Autowired
    private DependanceTachesRepository dependanceTachesRepository;

    @Autowired
    private TachesRepository tachesRepository;

    // Create dependency between tasks
    public DependanceTaches createDependance(Long tachePrecedenteId, Long tacheSuivanteId) {
        Optional<Taches> tachePrecedente = tachesRepository.findById(tachePrecedenteId);
        Optional<Taches> tacheSuivante = tachesRepository.findById(tacheSuivanteId);

        if (tachePrecedente.isPresent() && tacheSuivante.isPresent()) {
            DependanceTaches dependance = new DependanceTaches();
            dependance.setTachePrecedente(tachePrecedente.get());
            dependance.setTacheSuivante(tacheSuivante.get());
            return dependanceTachesRepository.save(dependance);
        }

        throw new IllegalArgumentException("Both tasks must exist.");
    }

    // Modify an existing dependency
    public DependanceTaches modifyDependance(Long dependanceId, Long tachePrecedenteId, Long tacheSuivanteId) {
        Optional<DependanceTaches> existingDependance = dependanceTachesRepository.findById(dependanceId);
        if (existingDependance.isPresent()) {
            Optional<Taches> tachePrecedente = tachesRepository.findById(tachePrecedenteId);
            Optional<Taches> tacheSuivante = tachesRepository.findById(tacheSuivanteId);

            if (tachePrecedente.isPresent() && tacheSuivante.isPresent()) {
                DependanceTaches dependance = existingDependance.get();
                dependance.setTachePrecedente(tachePrecedente.get());
                dependance.setTacheSuivante(tacheSuivante.get());
                return dependanceTachesRepository.save(dependance);
            }
        }
        throw new IllegalArgumentException("Dependency or task does not exist.");
    }

    // Delete a dependency
    public void deleteDependance(Long dependanceId) {
        dependanceTachesRepository.deleteById(dependanceId);
    }

    // Check if all predecessor tasks are completed
    public boolean arePredecessorsCompleted(Long tacheSuivanteId) {
        List<DependanceTaches> dependencies = dependanceTachesRepository.findByTacheSuivanteId(tacheSuivanteId);

        for (DependanceTaches dep : dependencies) {
            if (dep.getTachePrecedente().getStatut() != Statut.TERMINE) {
                return false;
            }
        }
        return true;
    }

    // Get all dependencies of a specific task
    public List<DependanceTaches> getDependenciesForTask(Long tacheId) {
        return dependanceTachesRepository.findByTachePrecedenteId(tacheId);
    }
}
