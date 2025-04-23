package org.example.suividestaches.repositories;

import org.example.suividestaches.models.ChefDequipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefDequipeRepository extends JpaRepository<ChefDequipe,Long> {
}
