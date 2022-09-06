package com.galvanize.springplayground.math;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/math")
public class MathController {

    @GetMapping("/math/pi")
    public String getPi() {
        return "3.141592653589793";
    }
}
