package org.example.suividestaches.services;

import org.example.suividestaches.models.Enum.PostMembre;
import org.example.suividestaches.models.Equipe;
import org.example.suividestaches.models.Membre;
import org.example.suividestaches.repositories.EquipeRepository;
import org.example.suividestaches.repositories.MembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembreService {
    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    public List<Membre> getAllMembres() {
        return membreRepository.findAll();
    }

    public Membre getMembreById(Long id) {
        return membreRepository.findById(id).orElseThrow(() -> new RuntimeException("Membre not found"));
    }




    public Membre createMembre(Membre membre) {
        // Récupérer l'équipe par son nom
        Equipe equipe = equipeRepository.findByNom(membre.getEquipe().getNom()).orElse(null);

        // Vérifier si l'équipe existe
        if (equipe == null) {
            // Si l'équipe n'existe pas, créer une nouvelle équipe
            equipe = new Equipe();
            equipe.setNom(membre.getEquipe().getNom());
            equipe = equipeRepository.save(equipe); // Sauvegarder la nouvelle équipe
        }

        // Associer l'équipe au membre
        membre.setEquipe(equipe);

        // Sauvegarder le membre
        return membreRepository.save(membre);
    }

    public Membre updateMembre(Long id, Membre updatedMembre) {
        Membre membre = getMembreById(id);
        membre.setNom(updatedMembre.getNom());
        membre.setPrenom(updatedMembre.getPrenom());
        membre.setEmail(updatedMembre.getEmail());
        membre.setPwd(updatedMembre.getPwd());
        membre.setRole(updatedMembre.getRole());
        membre.setCompetences(updatedMembre.getCompetences());
        membre.setPostMembre(updatedMembre.getPostMembre());
        membre.setEquipe(updatedMembre.getEquipe());
        return membreRepository.save(membre);
    }

    public void deleteMembre(Long id) {
        membreRepository.deleteById(id);
    }
}
