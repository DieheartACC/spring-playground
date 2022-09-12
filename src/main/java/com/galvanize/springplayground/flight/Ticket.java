package com.galvanize.springplayground.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Ticket {
    @JsonProperty("Passenger")
    Person passenger;
    @JsonProperty("Price")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Person getPassenger() {
        return passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }
}
