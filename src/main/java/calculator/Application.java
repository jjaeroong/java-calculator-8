package calculator;

import calculator.controller.CalculatorController;

public class Application {
    public static void main(String[] args) {

        final CalculatorController controller = new CalculatorController();
        controller.run();
        
    }
}
