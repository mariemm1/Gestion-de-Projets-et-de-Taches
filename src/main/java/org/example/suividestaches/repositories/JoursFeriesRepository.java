package org.example.suividestaches.repositories;

import org.example.suividestaches.models.JoursFeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JoursFeriesRepository extends JpaRepository<JoursFeries,Long> {
    boolean existsByDate(LocalDate date);
}
