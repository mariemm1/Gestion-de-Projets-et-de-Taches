package org.example.suividestaches.services;

import org.example.suividestaches.models.ChefDequipe;
import org.example.suividestaches.models.Equipe;
import org.example.suividestaches.repositories.ChefDequipeRepository;
import org.example.suividestaches.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {
    @Autowired
    private EquipeRepository equipeRepository;


    @Autowired
    private ChefDequipeRepository chefDequipeRepository;

    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    public Optional<Equipe> getEquipeById(Long id) {
        return equipeRepository.findById(id);
    }


    public Equipe createEquipeWithChefName(Equipe equipe, String chefNom) {
        // Récupérer le chef par son nom
        ChefDequipe chef = chefDequipeRepository.findByNom(chefNom);
        if (chef == null) {
            throw new RuntimeException("ChefDequipe avec nom " + chefNom + " introuvable");
        }

        // Associer l'équipe au chef (dans ChefDequipe)
        chef.setEquipe(equipe);

        // Sauvegarder le chef pour s'assurer qu’il est persistant
        chef = chefDequipeRepository.save(chef);

        // Associer le chef (persistant) à l’équipe
        equipe.setChefDequipe(chef);

        // Sauvegarder l’équipe
        return equipeRepository.save(equipe);
    }

    public Equipe updateEquipe(Long id, Equipe updatedEquipe) {
        return equipeRepository.findById(id).map(equipe -> {
            equipe.setNom(updatedEquipe.getNom());
            equipe.setDescription(updatedEquipe.getDescription());
            equipe.setChefDequipe(updatedEquipe.getChefDequipe());
            equipe.setMembres(updatedEquipe.getMembres());
            equipe.setProjets(updatedEquipe.getProjets());
            return equipeRepository.save(equipe);
        }).orElse(null);
    }

    public boolean deleteEquipe(Long id) {
        if (equipeRepository.existsById(id)) {
            equipeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
