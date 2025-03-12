package org.example.suividestaches.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;
    private Date dateEnvoi;
    private String statut;
    private String typeNotification;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notifications() {
    }

    public Notifications(Long id, String message, Date dateEnvoi, String statut, String typeNotification) {
        this.id = id;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.statut = statut;
        this.typeNotification = typeNotification;
    }

    public Notifications(Long id, String message, Date dateEnvoi, String statut, String typeNotification, User user) {
        this.id = id;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.statut = statut;
        this.typeNotification = typeNotification;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(String typeNotification) {
        this.typeNotification = typeNotification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
