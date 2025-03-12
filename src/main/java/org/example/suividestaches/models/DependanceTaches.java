package org.example.suividestaches.models;

import jakarta.persistence.*;

@Entity
public class DependanceTaches {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Référence à la tâche précédente
    @ManyToOne
    @JoinColumn(name = "tache_precedente_id")
    private Taches tachePrecedente;

    // Référence à la tâche suivante
    @ManyToOne
    @JoinColumn(name = "tache_suivante_id")
    private Taches tacheSuivante;

    public DependanceTaches() {
    }

    public DependanceTaches(Long id, Taches tachePrecedente, Taches tacheSuivante) {
        this.id = id;
        this.tachePrecedente = tachePrecedente;
        this.tacheSuivante = tacheSuivante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Taches getTachePrecedente() {
        return tachePrecedente;
    }

    public void setTachePrecedente(Taches tachePrecedente) {
        this.tachePrecedente = tachePrecedente;
    }

    public Taches getTacheSuivante() {
        return tacheSuivante;
    }

    public void setTacheSuivante(Taches tacheSuivante) {
        this.tacheSuivante = tacheSuivante;
    }
}
