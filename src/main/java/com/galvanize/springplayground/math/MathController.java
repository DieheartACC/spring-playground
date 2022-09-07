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
//        myMathService.setOperation(operation);
//        myMathService.setX(x);
//        myMathService.setY(y);

        return myMathService.calculate(operation, x, y);
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> query) {
        return myMathService.sum(query);
    }

    @RequestMapping ("/math/volume/{length}/{width}/{height}")
    public String calcVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        int volume = length * width * height;
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, volume);
    }

    @PostMapping("/math/area")
    public String calcArea(@RequestParam Map<String, String> contentMap) {
        StringBuilder sb = new StringBuilder();
        float result;
        // Switch Case based on what type we have
        switch (contentMap.get("type")) {
            case "circle":
                result = Float.parseFloat(getPi()) *
                        (Float.parseFloat(contentMap.get("radius")) * Float.parseFloat(contentMap.get("radius")));
                sb.append(String.format("Area of a circle with a radius of %.0f is %.5f",
                        Float.parseFloat(contentMap.get("radius")),
                        result));
                break;

            case "rectangle":
                if (contentMap.get("radius") != null) {
                    sb.append("Invalid");
                }

                else {
                    result = Float.parseFloat(contentMap.get("width")) * Float.parseFloat(contentMap.get("height"));
                    sb.append(String.format("Area of a %.0fx%.0f rectangle is %.0f",
                            Float.parseFloat(contentMap.get("width")),
                            Float.parseFloat(contentMap.get("height")),
                            result));
                }
                break;

            default:
                break;
        }
        return sb.toString();
    }
}