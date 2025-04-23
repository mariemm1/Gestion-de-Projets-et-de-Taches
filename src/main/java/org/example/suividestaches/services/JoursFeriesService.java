package org.example.suividestaches.services;

import org.example.suividestaches.models.JoursFeries;
import org.example.suividestaches.repositories.JoursFeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JoursFeriesService {
    @Autowired
    private JoursFeriesRepository joursFeriesRepo;

    public boolean isHoliday(LocalDate date) {
        return joursFeriesRepo.existsByDate(date);
    }

    public JoursFeries save(JoursFeries j) {
        return joursFeriesRepo.save(j);
    }

    public List<JoursFeries> getAll() {
        return joursFeriesRepo.findAll();
    }

    public void delete(Long id) {
        joursFeriesRepo.deleteById(id);
    }
}
