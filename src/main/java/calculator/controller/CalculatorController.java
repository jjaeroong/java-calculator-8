package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CalculatorService calculatorService;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.calculatorService = new CalculatorService();
    }

    public void run() {
        final String input = inputView.readInput();
        final double result = calculatorService.calculate(input);
        outputView.printResult(result);
    }
}
