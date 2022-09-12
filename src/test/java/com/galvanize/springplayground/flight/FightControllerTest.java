package com.galvanize.springplayground.flight;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.MultiValueMap;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
                .andExpect(jsonPath("$.Tickets[0].passenger.firstName", is("Dwayne")))
                .andExpect(jsonPath("$.Tickets[0].passenger.lastName", is("Johnson")))
                .andExpect(jsonPath("$.Tickets[0].price", is(200)));
    }

    @Test
    public void getFlightsRendersMoreThings() throws Exception {
        this.mvc.perform(get("/flights"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is ("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].Tickets[0].passenger.firstName", is("Dwayne")))
                .andExpect(jsonPath("$[0].Tickets[0].passenger.lastName", is("Johnson")))
                .andExpect(jsonPath("$[0].Tickets[0].price", is(200)))
                .andExpect(jsonPath("$[1].Departs", is ("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].Tickets[0].passenger.firstName", is("John")))
                .andExpect(jsonPath("$[1].Tickets[0].price", is(400)));
    }

    @Test
    public void postJSONAsStringLiteral() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "tickets": [
                            {
                              "passenger": {
                                "firstName": "Some name",
                                "lastName": "Some other name"
                              },
                              "price": 200
                            },
                            {
                              "passenger": {
                                "firstName": "Name B",
                                "lastName": "Name C"
                              },
                              "price": 150
                            }
                          ]
                        }
                        """);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void postJSONWithJackson() throws Exception {
        Map<String, List<Ticket>> data = new HashMap<>();
        List<Ticket> TicketList = new ArrayList<>();
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Person person1 = new Person();
        person1.setFirstName("Dwayne");
        person1.setLastName("Johnson");
        Person person2 = new Person();
        person2.setFirstName("Stone");
        person2.setLastName("Cold");

        ticket1.setPassenger(person1);
        ticket1.setPrice(200);
        ticket2.setPassenger(person2);
        ticket2.setPrice(150);
        TicketList.add(ticket1);
        TicketList.add(ticket2);

        data.put("tickets", TicketList);

        String json = objectMapper.writeValueAsString(data);
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    @Test
    public void postJSONWithFileFixtures() throws Exception {
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(350)));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}