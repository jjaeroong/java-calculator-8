package calculator.model;

/**
 * 파싱된 숫자 배열 검증 및 합계 수행
 */
public class Numbers {

    private static final String NEGATIVE_NUMBER_ERROR_MESSAGE = "입력 숫자 값은 양수여야 합니다.";
    private final double[] values;

    public Numbers(final double[] values) {
        validatePositiveNumbers(values);
        this.values = values;
    }

    /**
     * 숫자가 양수인지 검증
     */
    private void validatePositiveNumbers(final double[] numbers) {
        for (final double number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException(NEGATIVE_NUMBER_ERROR_MESSAGE);
            }
        }
    }

    public double sum() {
        double total = 0.0;
        for (final double value : values) {
            total += value;
        }
        return total;
    }

}
