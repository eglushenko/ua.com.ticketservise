package ua.com.ticketservise.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "departure")
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String departureName;
    private LocalDate departureDate;
    @Column(columnDefinition = "varchar(8)")
    private LocalTime departureTime;

    public Departure(){}

    public Departure(String departureName, LocalDate departureDate, LocalTime departureTime) {
        this.departureName = departureName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureName() {
        return departureName;
    }

    public void setDepartureName(String departureName) {
        this.departureName = departureName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
