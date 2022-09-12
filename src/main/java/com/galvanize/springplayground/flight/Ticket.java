package com.galvanize.springplayground.flight;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Ticket {
    Person passenger;
    private int price;

//    @JsonProperty("Price")
    public int getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(int price) {
        this.price = price;
    }

//    @JsonProperty("Passenger")
    public Person getPassenger() {
        return passenger;
    }

    @JsonProperty("passenger")
    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }
}
