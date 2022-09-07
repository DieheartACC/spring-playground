package com.galvanize.springplayground.math;


import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

        return myMathService.calculate(operation, x, y);
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> query) {
        return myMathService.sum(query);
    }

    @RequestMapping ("/math/volume/{length}/{width}/{height}")
    public String calcVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        return myMathService.calcVolume(length, width, height);
    }

    @PostMapping("/math/area")
    public String calcArea(@RequestParam Map<String, String> contentMap) {
        // The controller should only be responsible for determining if it is a rectangle or circle
        // to the MathService
        // Switch Case based on what type we have
        switch (contentMap.get("type")) {
            case "circle":
                return myMathService.calcCircArea(Float.parseFloat(contentMap.get("radius")));

            case "rectangle":
                if (contentMap.get("radius") != null) {
                    return ("Invalid");
                }

                else {
                    return myMathService.calcRectArea(Integer.parseInt(contentMap.get("width")),
                            Integer.parseInt(contentMap.get("height")));
                }

            default:
                return "";
        }
    }
}