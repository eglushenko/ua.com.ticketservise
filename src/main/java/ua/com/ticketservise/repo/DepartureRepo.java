package ua.com.ticketservise.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.ticketservise.model.Departure;

import java.time.LocalDate;
import java.util.List;

public interface DepartureRepo extends JpaRepository<Departure,Long> {
    List<Departure> findByDepartureNameAndDepartureDate(String dep,LocalDate date);

}
