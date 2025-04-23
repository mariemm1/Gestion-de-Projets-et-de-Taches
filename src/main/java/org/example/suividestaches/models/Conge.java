package org.example.suividestaches.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String theCause;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int availableHours;  // New field to store available hours during the period

    public Conge() {
    }

    public Conge(Long id, LocalDate startDate, LocalDate endDate, String theCause, User user, int availableHours) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.theCause = theCause;
        this.user = user;
        this.availableHours = availableHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTheCause() {
        return theCause;
    }

    public void setTheCause(String theCause) {
        this.theCause = theCause;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(int availableHours) {
        this.availableHours = availableHours;
    }
}
