package com.galvanize.springplayground.math;


import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/math")
public class MathController {
    MathService myMathService = new MathService();

    @GetMapping("/math/pi")
    public String getPi() {
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String getCalculation(@RequestParam (required = false, defaultValue = "add") String operation,
                                    int x, int y) {
        myMathService.setOperation(operation);
        myMathService.setX(x);
        myMathService.setY(y);

        return myMathService.calculate();
    }

    @PostMapping("/math/sum")
    public String postCalculation(@RequestParam MultiValueMap<String, String> query){
        return myMathService.sum(query);
    }
}
