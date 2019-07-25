package ua.com.ticketservise.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.ticketservise.model.Ticket;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Long> {


    List<Ticket> findAllByPayStatusIsNull();




}
