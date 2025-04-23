package org.example.suividestaches.repositories;

import org.example.suividestaches.models.DependanceTaches;
import org.example.suividestaches.models.Taches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependanceTachesRepository extends JpaRepository<DependanceTaches,Long> {
    List<DependanceTaches> findByTacheSuivanteId(Long tacheSuivanteId);

    List<DependanceTaches> findByTachePrecedenteId(Long tachePrecedenteId);
}
