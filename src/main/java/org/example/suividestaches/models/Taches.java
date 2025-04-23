package org.example.suividestaches.models;

import jakarta.persistence.*;
import org.example.suividestaches.models.Enum.Priorite;
import org.example.suividestaches.models.Enum.Statut;

import java.util.Date;
import java.util.List;

@Entity
public class Taches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    //private Float temps_estime;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    // Liste des dépendances précédentes (tâches qui doivent être faites avant celle-ci)
    @OneToMany(mappedBy = "tacheSuivante", cascade = CascadeType.ALL)
    private List<DependanceTaches> dependancesPrecedentes;

    // Liste des dépendances suivantes (tâches qui dépendent de celle-ci)
    @OneToMany(mappedBy = "tachePrecedente", cascade = CascadeType.ALL)
    private List<DependanceTaches> dependancesSuivantes;

    public Taches() {
    }

    public Taches(Long id, String nom, String description, Date dateDebut, Date dateFin, Statut statut, Priorite priorite, Projet projet, List<DependanceTaches> dependancesPrecedentes, List<DependanceTaches> dependancesSuivantes) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.priorite = priorite;
        this.projet = projet;
        this.dependancesPrecedentes = dependancesPrecedentes;
        this.dependancesSuivantes = dependancesSuivantes;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<DependanceTaches> getDependancesPrecedentes() {
        return dependancesPrecedentes;
    }

    public void setDependancesPrecedentes(List<DependanceTaches> dependancesPrecedentes) {
        this.dependancesPrecedentes = dependancesPrecedentes;
    }

    public List<DependanceTaches> getDependancesSuivantes() {
        return dependancesSuivantes;
    }

    public void setDependancesSuivantes(List<DependanceTaches> dependancesSuivantes) {
        this.dependancesSuivantes = dependancesSuivantes;
    }
}
