package com.galvanize.springplayground.math;

import org.springframework.util.MultiValueMap;

import java.util.List;

public class MathService {
    private String operation;
    private int x;
    private int y;

    MultiValueMap<String, String> query;

    // Constructor to default operation
//    public MathService(String operation) {
//        this.operation = "add";
//    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    public String calculate() {
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
        StringBuilder sbResult = new StringBuilder();
        int sumResult = 0;
        List<String> valueList = query.get("n");
//        valueList = query.values();
        // Iterate to sum up the map
        for (int i = 0; i < valueList.size(); i++) {
            sumResult += Integer.parseInt(valueList.get(i));
            sbResult.append(valueList.get(i));

            if (i == (valueList.size() - 1))
                sbResult.append(" = ");

            else
                sbResult.append(" + ");
        }
        sbResult.append(sumResult);
        return sbResult.toString();
    }
}