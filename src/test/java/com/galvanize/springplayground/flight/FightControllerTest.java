package com.galvanize.springplayground.flight;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class FightControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getFlightsRendersCorrectJSON() throws Exception {
        this.mvc.perform(get("/flights/flight")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is ("2017-04-21 14:34")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("Dwayne")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.lastName", is("Johnson")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void getFlightsRendersMoreThings() throws Exception {
        this.mvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is ("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("Dwayne")))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.lastName", is("Johnson")))
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Departs", is ("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("John")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.lastName", nullValue()))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
    }
}