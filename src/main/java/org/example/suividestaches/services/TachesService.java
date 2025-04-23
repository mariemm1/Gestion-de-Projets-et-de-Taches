package org.example.suividestaches.services;

import org.example.suividestaches.models.Projet;
import org.example.suividestaches.models.Taches;
import org.example.suividestaches.repositories.ProjetRepository;
import org.example.suividestaches.repositories.TachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TachesService {
    @Autowired
    private TachesRepository tacheRepository;

    @Autowired
    private ProjetRepository projetRepository;

    public Taches createTaches(Taches tache, Long projetId) {
        Projet projet = projetRepository.findById(projetId).orElseThrow(() -> new RuntimeException("Projet not found"));
        tache.setProjet(projet);
        return tacheRepository.save(tache);
    }

    public List<Taches> getAllTachess() {
        return tacheRepository.findAll();
    }

    public Taches getTachesById(Long id) {
        return tacheRepository.findById(id).orElseThrow(() -> new RuntimeException("Taches not found"));
    }

    public Taches updateTaches(Long id, Taches tacheDetails) {
        Taches tache = getTachesById(id);
        tache.setNom(tacheDetails.getNom());
        tache.setDescription(tacheDetails.getDescription());
        tache.setStatut(tacheDetails.getStatut());
        return tacheRepository.save(tache);
    }

    public void deleteTaches(Long id) {
        tacheRepository.deleteById(id);
    }
}
