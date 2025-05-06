package org.example.suividestaches.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    @OneToOne
    @JoinColumn(name = "chef_id") // Un seul chef par équipe
    @JsonManagedReference
    private ChefDequipe chefDequipe;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Membre> membres;

    @OneToMany(mappedBy = "equipe")
    private List<Projet> projets;


    public Equipe() {
    }

    public Equipe(Long id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Equipe(Long id, String nom, String description, ChefDequipe chefDequipe, List<Membre> membres, List<Projet> projets) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.chefDequipe = chefDequipe;
        this.membres = membres;
        this.projets = projets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChefDequipe getChefDequipe() {
        return chefDequipe;
    }

    public void setChefDequipe(ChefDequipe chefDequipe) {
        this.chefDequipe = chefDequipe;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
}
