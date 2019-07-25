package ua.com.ticketservise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.ticketservise.model.Departure;
import ua.com.ticketservise.model.Ticket;
import ua.com.ticketservise.repo.DepartureRepo;
import ua.com.ticketservise.repo.TicketRepo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
class TicketController {

    @Autowired
    TicketRepo repository;
    DepartureRepo departureRepo;


    TicketController(TicketRepo repository, DepartureRepo departureRepo) {
        this.departureRepo = departureRepo;
        this.repository = repository;
    }

    @PostMapping(value = "/ticket", produces = "application/json")
    public ResponseEntity<Long> newTicket(@RequestParam String departure,
                                              @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                              @RequestParam  LocalTime time
    ) {


       List<Departure> dep = departureRepo.findByDepartureNameAndDepartureDate(departure, date);
        System.out.println(dep.isEmpty());
        Ticket ticket = new Ticket(departure,date,time);
        if(dep.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            for (int i = 0; i < dep.size(); i++) {
               if ( dep.get(i).getDepartureTime().equals(time)&&!date.isBefore(LocalDate.now())
                       &&!time.isBefore(LocalTime.now())){
                    repository.save(ticket);
                    return new ResponseEntity<Long>(repository.getOne(ticket.getId()).getId(),HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }


        return null;
    }


    @GetMapping(value = "/ticket/{id}",produces = "application/json")
    public String oneTicketStatus(@PathVariable Long id) {
        String statusString = null;

        Boolean status = repository.findById(id).get().getPayStatus();
        if (Boolean.TRUE.equals(status)) {
            statusString = "true";

        }
        if (Boolean.FALSE.equals(status)) {
            statusString = "false";

        }
        if (!Boolean.TRUE.equals(status) && !Boolean.FALSE.equals(status)) {

            statusString = "pending";
        }

        return statusString ;
    }




}