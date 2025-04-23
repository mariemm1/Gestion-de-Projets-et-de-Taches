package org.example.suividestaches.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.example.suividestaches.models.Enum.Role;

import java.util.List;

@Entity
public class ChefDequipe extends User {

    @OneToOne(mappedBy = "chefDequipe") // Relation OneToOne avec Equipe
    private Equipe equipe;

    public ChefDequipe() {
    }


    public ChefDequipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public ChefDequipe(Long id, String nom, String prenom, String email, String pwd, Role role, List<Notifications> notifications, List<Conge> conges, Equipe equipe) {
        super(id, nom, prenom, email, pwd, role, notifications, conges);
        this.equipe = equipe;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
