package org.example.suividestaches.models;

import jakarta.persistence.*;
import org.example.suividestaches.models.Enum.PostMembre;
import org.example.suividestaches.models.Enum.Role;

import java.util.List;
@Entity
public class Membre extends User{

    @ElementCollection
    private List<String> competences;

    @Enumerated(EnumType.STRING)
    private PostMembre postMembre;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Membre() {
    }


    public Membre(List<String> competences, PostMembre postMembre, Equipe equipe) {
        this.competences = competences;
        this.postMembre = postMembre;
        this.equipe = equipe;
    }

    public Membre(Long id, String nom, String prenom, String email, String pwd, Role role, List<Notifications> notifications, List<Conge> conges, List<String> competences, PostMembre postMembre, Equipe equipe) {
        super(id, nom, prenom, email, pwd, role, notifications, conges);
        this.competences = competences;
        this.postMembre = postMembre;
        this.equipe = equipe;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public PostMembre getPostMembre() {
        return postMembre;
    }

    public void setPostMembre(PostMembre postMembre) {
        this.postMembre = postMembre;
    }
}
