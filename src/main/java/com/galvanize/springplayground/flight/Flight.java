package com.galvanize.springplayground.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class Flight {
    //    private String destination;
    @JsonProperty("Departs")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private java.time.LocalDateTime departs;

    @JsonProperty("Tickets")
    private List<Ticket> tickets;

    public LocalDateTime getDeparts() {
        return departs;
    }

    public void setDeparts(LocalDateTime departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}