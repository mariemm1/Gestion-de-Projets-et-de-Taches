package org.example.suividestaches.services;

import org.example.suividestaches.models.Equipe;
import org.example.suividestaches.models.Projet;
import org.example.suividestaches.repositories.EquipeRepository;
import org.example.suividestaches.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetService {
    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    public Projet createProjet(Projet projet, Long equipeId) {
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new RuntimeException("Equipe not found"));
        projet.setEquipe(equipe);
        return projetRepository.save(projet);
    }

    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Projet getProjetById(Long id) {
        return projetRepository.findById(id).orElseThrow(() -> new RuntimeException("Projet not found"));
    }

    public Projet updateProjet(Long id, Projet projetDetails) {
        Projet projet = getProjetById(id);
        projet.setNom(projetDetails.getNom());
        projet.setDescription(projetDetails.getDescription());
        return projetRepository.save(projet);
    }

    public void deleteProjet(Long id) {
        projetRepository.deleteById(id);
    }
}
