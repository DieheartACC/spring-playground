package com.galvanize.springplayground;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)

public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHomepage() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Spring!"));
    }

    @Test
    public void getSpecificCats() throws Exception {
        this.mvc.perform(get("/cats?name=Fluffy&color=tabby"))
                .andExpect(content().string("The name of the cat is Fluffy and it is a tabby cat"));
    }
}