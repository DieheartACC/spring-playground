package com.galvanize.springplayground.flight;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlights() {
        List<Ticket> tickets = new ArrayList<>();
        Person person = new Person();
        Ticket ticket = new Ticket();
        person.setFirstName("Dwayne");
        person.setLastName("Johnson");
        ticket.setPrice(200);
        ticket.setPassenger(person);
        tickets.add(ticket);

        Flight myFlight = new Flight();

        LocalDateTime testTime = LocalDateTime.of(2017,04,21,14,34);
        myFlight.setDeparts(testTime);
        myFlight.setTickets(tickets);
        return myFlight;
    }

    @GetMapping("flights")
    public List<Flight> getFlightArray() {
        List<Flight> flightArray = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        List<Ticket> tickets1 = new ArrayList<>();
        Person person = new Person();
        Ticket ticket = new Ticket();
        person.setFirstName("Dwayne");
        person.setLastName("Johnson");
        ticket.setPrice(200);
        ticket.setPassenger(person);
        tickets.add(ticket);

        Flight myFlight = new Flight();

        LocalDateTime testTime = LocalDateTime.of(2017,04,21,14,34);
        myFlight.setDeparts(testTime);
        myFlight.setTickets(tickets);

        flightArray.add(myFlight);

        Person person1 = new Person();
        Ticket ticket1 = new Ticket();
        person1.setFirstName("John");
        ticket1.setPrice(400);
        ticket1.setPassenger(person1);
        tickets1.add(ticket1);

        Flight myFlight1 = new Flight();

        myFlight1.setDeparts(testTime);
        myFlight1.setTickets(tickets1);

        flightArray.add(myFlight1);

        return flightArray;
    }

    @PostMapping("/flights/tickets/total")
    public String postTicketInfoAndCalcTotal(@RequestBody TicketTotal tickets ) {
        int totalPrice = 0;

        for (int i = 0; i < tickets.getTickets().size(); i++) {
            totalPrice += tickets.getTickets().get(i).getPrice();
        }

        return String.format("""
                {
                 "result": %d
                }
                 """, totalPrice);
    }

}