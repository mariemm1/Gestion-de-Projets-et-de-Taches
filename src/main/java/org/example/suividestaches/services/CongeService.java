package org.example.suividestaches.services;

import org.example.suividestaches.models.Conge;
import org.example.suividestaches.models.JoursFeries;
import org.example.suividestaches.models.User;
import org.example.suividestaches.repositories.CongeRepository;
import org.example.suividestaches.repositories.JoursFeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CongeService {
    @Autowired
    private CongeRepository congeRepo;

    @Autowired
    private JoursFeriesRepository joursFeriesRepo;

    public Conge saveConge(Conge conge) {
        return congeRepo.save(conge);
    }


    public void deleteConge(Long id) {
        congeRepo.deleteById(id);
    }


    public List<Conge> getCongesByUser(Long userId) {
        return congeRepo.findByUserId(userId);
    }


    public boolean isUserAvailableOn(User user, LocalDate date) {
        List<Conge> conges = congeRepo.findByUserId(user.getId());
        List<JoursFeries> holidays = joursFeriesRepo.findAll();

        for (Conge c : conges) {
            if (!date.isBefore(c.getStartDate()) && !date.isAfter(c.getEndDate())) {
                return false;
            }
        }

        for (JoursFeries holiday : holidays) {
            if (holiday.getDate().equals(date)) {
                return false;
            }
        }

        DayOfWeek day = date.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }

        return true;
    }

    public List<LocalDate> getAvailableDates(User user, LocalDate from, LocalDate to) {
        List<LocalDate> availableDates = new ArrayList<>();
        for (LocalDate date = from; !date.isAfter(to); date = date.plusDays(1)) {
            if (isUserAvailableOn(user, date)) {
                availableDates.add(date);
            }
        }
        return availableDates;
    }

    public Map<LocalDate, Integer> calculateAvailableHoursByWeek(User user, LocalDate from, LocalDate to) {
        Map<LocalDate, Integer> weeklyAvailability = new LinkedHashMap<>();
        List<JoursFeries> holidays = joursFeriesRepo.findAll();
        List<Conge> conges = congeRepo.findByUserId(user.getId());

        for (LocalDate currentDate = from; !currentDate.isAfter(to); currentDate = currentDate.plusDays(1)) {
            final LocalDate date = currentDate;  //  make it effectively final

            DayOfWeek day = date.getDayOfWeek();
            boolean isWeekend = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
            boolean isHoliday = holidays.stream().anyMatch(h -> h.getDate().equals(date));
            boolean isOnLeave = conges.stream().anyMatch(c ->
                    !date.isBefore(c.getStartDate()) && !date.isAfter(c.getEndDate())
            );

            if (!isWeekend && !isHoliday && !isOnLeave) {
                LocalDate monday = date.with(DayOfWeek.MONDAY);
                weeklyAvailability.put(monday, weeklyAvailability.getOrDefault(monday, 0) + 8);
            }
        }

        return weeklyAvailability;
    }

}
