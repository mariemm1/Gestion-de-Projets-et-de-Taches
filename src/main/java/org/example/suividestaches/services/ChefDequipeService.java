package org.example.suividestaches.services;

import org.example.suividestaches.models.ChefDequipe;
import org.example.suividestaches.repositories.ChefDequipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefDequipeService {

    @Autowired
    private ChefDequipeRepository chefDequipeRepository;

    // Get all ChefDequipe
    public List<ChefDequipe> getAllChefDequipe() {
        return chefDequipeRepository.findAll();
    }

    // Get ChefDequipe by ID
    public ChefDequipe getChefDequipeById(Long id) {
        Optional<ChefDequipe> chefDequipe = chefDequipeRepository.findById(id);
        return chefDequipe.orElse(null);
    }

    // Create new ChefDequipe
    public ChefDequipe createChefDequipe(ChefDequipe chefDequipe) {
        return chefDequipeRepository.save(chefDequipe);
    }

    // Update ChefDequipe by ID
    public ChefDequipe updateChefDequipe(Long id, ChefDequipe updatedChefDequipe) {
        ChefDequipe chefDequipe = chefDequipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChefDequipe not found"));

        chefDequipe.setNom(updatedChefDequipe.getNom());
        chefDequipe.setPrenom(updatedChefDequipe.getPrenom());
        chefDequipe.setEmail(updatedChefDequipe.getEmail());
        chefDequipe.setPwd(updatedChefDequipe.getPwd());
        chefDequipe.setRole(updatedChefDequipe.getRole());
        // Any other fields specific to ChefDequipe can be updated here

        return chefDequipeRepository.save(chefDequipe);
    }

    // Delete ChefDequipe by ID
    public void deleteChefDequipe(Long id) {
        if (!chefDequipeRepository.existsById(id)) {
            throw new RuntimeException("ChefDequipe not found for deletion");
        }
        chefDequipeRepository.deleteById(id);
    }
}
