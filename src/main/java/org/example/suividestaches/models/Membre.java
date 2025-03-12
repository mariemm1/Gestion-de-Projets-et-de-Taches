package org.example.suividestaches.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.List;
@Entity
public class Membre extends User{

    @ElementCollection
    private List<String> competences;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Membre() {
    }



    public Membre(List<String> competences, Equipe equipe) {
        this.competences = competences;
        this.equipe = equipe;
    }

    public Membre(Long id, String nom, String email, List<Notifications> notifications, String pwd, Disponibilite disponibilite, List<String> competences, Equipe equipe) {
        super(id, nom, email, notifications, pwd, disponibilite);
        this.competences = competences;
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
}
