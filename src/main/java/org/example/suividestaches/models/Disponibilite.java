package org.example.suividestaches.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;

@Entity
public class Disponibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // Foreign key column
    private User user;

    @ElementCollection
    private List<LocalDate> joursFeries;  // Liste des jours fériés
    @ElementCollection
    private List<LocalDate> conges;       // Liste des jours de congé


    // Jours de travail : Lundi au Vendredi de 8h à 17h
    private String joursTravail = "Lundi-Vendredi: 08:00-17:00";

    private boolean dispoSamediDimanche = false;  // Disponibilité le samedi et dimanche

    // Durée quotidienne de travail (en heures)
    private int heuresParJour = 8;

    public Disponibilite() {
    }

    public Disponibilite(User user, List<LocalDate> joursFeries, List<LocalDate> conges) {
        this.user = user;
        this.joursFeries = joursFeries;
        this.conges = conges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LocalDate> getJoursFeries() {
        return joursFeries;
    }

    public void setJoursFeries(List<LocalDate> joursFeries) {
        this.joursFeries = joursFeries;
    }

    public List<LocalDate> getConges() {
        return conges;
    }

    public void setConges(List<LocalDate> conges) {
        this.conges = conges;
    }

    public String getJoursTravail() {
        return joursTravail;
    }

    public void setJoursTravail(String joursTravail) {
        this.joursTravail = joursTravail;
    }

    public boolean isDispoSamediDimanche() {
        return dispoSamediDimanche;
    }

    public void setDispoSamediDimanche(boolean dispoSamediDimanche) {
        this.dispoSamediDimanche = dispoSamediDimanche;
    }

    public int getHeuresParJour() {
        return heuresParJour;
    }

    public void setHeuresParJour(int heuresParJour) {
        this.heuresParJour = heuresParJour;
    }



    // Vérification de la disponibilité pour un jour spécifique et une heure
    public boolean isAvailable(User user, LocalDate date) {
        // Vérifier si c'est un jour de week-end (samedi ou dimanche)
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return dispoSamediDimanche;  // Retourne false si l'utilisateur n'est pas disponible le week-end
        }

        // Vérifier si c'est un jour férié ou un jour de congé
        if (joursFeries.contains(date) || conges.contains(date)) {
            return false;  // Non disponible
        }

        // Vérifier si c'est un jour de travail (lundi à vendredi)
        if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
            return true;  // Disponible du lundi au vendredi
        }

        return false;  // Par défaut, non disponible si aucune condition n'est remplie
    }
}
