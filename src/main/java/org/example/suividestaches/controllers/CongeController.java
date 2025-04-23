package org.example.suividestaches.controllers;

import org.example.suividestaches.models.Conge;
import org.example.suividestaches.models.User;
import org.example.suividestaches.repositories.UserRepository;
import org.example.suividestaches.services.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("conge")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{userId}")
    public List<Conge> getUserConges(@PathVariable Long userId) {
        return congeService.getCongesByUser(userId);
    }

    @PostMapping("/createConge")
    public Conge createConge(@RequestBody Conge conge) {
        return congeService.saveConge(conge);
    }

    @DeleteMapping("/conge/{id}")
    public void deleteConge(@PathVariable Long id) {
        congeService.deleteConge(id);
    }

    @GetMapping("/isAvailable")
    public boolean isAvailable(@RequestParam Long userId,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return congeService.isUserAvailableOn(user, date);
    }

    @GetMapping("/availableDates")
    public List<LocalDate> getAvailableDates(@RequestParam Long userId,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        return congeService.getAvailableDates(user, from, to);
    }

    // Get weekly available hours between two dates
    @GetMapping("/availableHours")
    public ResponseEntity<Map<LocalDate, Integer>> getWeeklyAvailableHours(@RequestParam Long userId,
                                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Map<LocalDate, Integer> availability = congeService.calculateAvailableHoursByWeek(user, from, to);
        return ResponseEntity.ok(availability);
    }
}
