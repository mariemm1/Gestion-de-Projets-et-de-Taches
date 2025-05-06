package org.example.suividestaches.repositories;

import org.example.suividestaches.models.ChefDequipe;
import org.example.suividestaches.models.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Long> {
    Optional<Equipe> findByNom(String nom);}
