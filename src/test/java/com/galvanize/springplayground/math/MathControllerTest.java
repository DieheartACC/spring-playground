package com.galvanize.springplayground.math;

import com.galvanize.springplayground.math.MathController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)

public class MathControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPiReturnsValidPiString() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void calculateShouldReturnCorrectOperation() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));

        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));

        this.mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));

        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));

        this.mvc.perform(get("/math/calculate?x=30&y=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void postSumAddsTheInput() throws Exception {
        this.mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void requestToVolumeCalculatesVolume() throws Exception {
        this.mvc.perform(get("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

        this.mvc.perform(post("/math/volume/42/56/79"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 42x56x79 rectangle is 185808"));

        this.mvc.perform(patch("/math/volume/6/7/8"))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }

    @Test
    public void calcAreaShouldCalcCircle() throws Exception {
        // Build the request
        MockHttpServletRequestBuilder request1 = post("/math/area")
                .param("type", "circle")
                .param("radius", "4");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
    }

    @Test
    public void calcAreaShouldCalcRectangle() throws Exception {
        // Build the request
        MockHttpServletRequestBuilder request1 = post("/math/area")
                .param("type", "rectangle")
                .param("width", "4")
                .param("height", "7");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void calcAreaShouldRenderInvalidWithRectangleRadius() throws Exception {
        MockHttpServletRequestBuilder request1 = post("/math/area")
                .param("type", "rectangle")
                .param("radius", "5");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}