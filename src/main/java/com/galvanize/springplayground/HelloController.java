package com.galvanize.springplayground;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.HTML;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello from Spring!";
    }

//    @GetMapping("/cats")
//    public String getSpecificCats(@RequestParam String name,
//                                  @RequestParam(required = false, defaultValue = "brown" ) String color) {
//
//        return String.format("The name of the cat is %s and it is a %s cat", name, color);
//    }

//    @GetMapping("/cats")
//    public String getSpecificCats(@RequestParam Map<String,String> queryString) {
//
//        return String.format("The name of the cat is %s and it is a %s cat", queryString.get("name")
//                , queryString.get("color"));
//    }

    @GetMapping("/cats")
    public String getSpecificCats(Cat newCat) {

        return String.format("The name of the cat is %s and it is a %s cat", newCat.getName()
                , newCat.getColor());
    }

    @GetMapping("/dogs/{dogId}")
    public String getDogById(@PathVariable String dogId) {
        return "This is dog number " + dogId;
    }
}