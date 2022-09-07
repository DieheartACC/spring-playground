package com.galvanize.springplayground.math;

import org.springframework.util.MultiValueMap;

import java.util.List;

// Refactor to remove data fields
public class MathService {
    public String calculate(String operation, int x, int y) {
        int result = 0;
        switch (operation) {
            case "add":
                result = x + y;
                operation = "+";
                break;

            case "subtract":
                result = x - y;
                operation = "-";
                break;

            case "multiply":
                result = x * y;
                operation = "*";
                break;

            case "divide":
                result = x / y;
                operation = "/";
                break;

            default:
                break;
        }

        return String.format("%d %s %d = %d", x, operation, y, result);
    }

    public String sum(MultiValueMap<String, String> query) {
        // Instead of Map can use List<Integer> or List<String>
        // Would save from having to create the list from the map
        StringBuilder sbResult = new StringBuilder();
        int sumResult = 0;
//        List<String> valueList = query.get("n");
////        valueList = query.values();
//        // Iterate to sum up the map
//        for (int i = 0; i < valueList.size(); i++) {
//            sumResult += Integer.parseInt(valueList.get(i));
//            sbResult.append(valueList.get(i));
//
//            if (i == (valueList.size() - 1))
//                sbResult.append(" = ");
//
//            else
//                sbResult.append(" + ");
//        }

        // Enhanced for loop
        for (String n : query.get("n")) {
            sbResult.append(n);
            sbResult.append(" + ");

            sumResult += Integer.parseInt(n);
        }
        sbResult.replace(sbResult.length() - 3, sbResult.length(), " = ");

        sbResult.append(sumResult);
        return sbResult.toString();
    }

    public String calcVolume(int length, int width, int height) {
        int volume = length * width * height;
        return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, volume);
    }


    public String calcCircArea(float radius) {
        StringBuilder sb = new StringBuilder();
        float result;

        result = 3.141592653589793f * (radius * radius);

        sb.append(String.format("Area of a circle with a radius of %.0f is %.5f",
                radius,
                result));

        return sb.toString();
    }

    public String calcRectArea(int width, int height) {
        StringBuilder sb = new StringBuilder();
        int result;

        result = width * height;
        sb.append(String.format("Area of a %dx%d rectangle is %d", width, height, result));

        return sb.toString();
    }
}