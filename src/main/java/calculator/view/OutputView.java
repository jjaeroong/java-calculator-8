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

    /**
     * 정수 결과 출력
     * double 값을 long으로 변환하여 소수점  제거 ex) 5.0 -> 5
     */
    private void printIntegerResult(final double result) {
        System.out.println(RESULT_PREFIX + (long) result);
    }
    /**
     * 실수 결과 출력
     */
    private void printDoubleResult(final double result) {
        System.out.println(RESULT_PREFIX + result);
    }

    /**
     * double을 long으로 변환했을 때 값이 변하지 않으면 정수로 판별
     * ex) 5.0 == (long)5.0 -> true
     */
    private boolean isInteger(final double value) {
        return value == (long) value;
    }
}
