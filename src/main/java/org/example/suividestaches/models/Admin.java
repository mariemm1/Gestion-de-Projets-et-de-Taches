package org.example.suividestaches.models;


import jakarta.persistence.Entity;
import org.example.suividestaches.models.Enum.Role;

import java.util.List;

@Entity
public class Admin extends User {




    public Admin() {
    }

    public Admin(Long id, String nom, String prenom, String email, String pwd, Role role, List<Notifications> notifications, List<Conge> conges) {
        super(id, nom, prenom, email, pwd, role, notifications, conges);
    }

}
