package calculator.service;

import calculator.model.Numbers;
import calculator.model.StringParser;

public class CalculatorService {

    private final StringParser stringParser;

    public CalculatorService() {
        this.stringParser = new StringParser();
    }

    public double calculate(final String input) {
        final double[] parsedNumbers = stringParser.parse(input);
        final Numbers numbers = new Numbers(parsedNumbers);
        return numbers.sum();
    }
}