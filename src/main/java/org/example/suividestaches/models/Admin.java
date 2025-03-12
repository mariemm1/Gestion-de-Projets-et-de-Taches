package org.example.suividestaches.models;


import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Admin extends User {




    public Admin() {
    }

    public Admin(Long id, String nom, String email, List<Notifications> notifications, String pwd, Disponibilite disponibilite) {
        super(id, nom, email, notifications, pwd, disponibilite);
    }

}
