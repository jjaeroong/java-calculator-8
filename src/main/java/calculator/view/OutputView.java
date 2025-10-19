package calculator.view;

public class OutputView {
    private static final String RESULT_PREFIX = "결과 : ";

    public void printResult(final double result) {
        if (isInteger(result)) {
            printIntegerResult(result);
            return;
        }
        printDoubleResult(result);
    }

    private void printIntegerResult(final double result) {
        System.out.println(RESULT_PREFIX + (long) result);
    }

    private void printDoubleResult(final double result) {
        System.out.println(RESULT_PREFIX + result);
    }

    private boolean isInteger(final double value) {
        return value == (long) value;
    }
}
