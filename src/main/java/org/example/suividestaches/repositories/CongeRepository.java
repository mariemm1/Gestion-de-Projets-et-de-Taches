package org.example.suividestaches.repositories;

import org.example.suividestaches.models.ChefDequipe;
import org.example.suividestaches.models.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {

    List<Conge> findByUserId(Long userId);
}
