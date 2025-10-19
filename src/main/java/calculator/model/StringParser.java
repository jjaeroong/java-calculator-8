package calculator.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 문자열 파서 클래스
 * 입력된 문자열에서 숫자 추출 후, 구분자 처리
 */
public class StringParser {

    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_DELIMITER = "//(.+)\\\\n(.*)";
    private static final String INVALID_CUSTOM_DELIMITER_ERROR = "잘못된 커스텀 구분자 형식입니다.";
    private static final String INVALID_NUMBER_FORMAT_ERROR = "숫자 형식이 올바르지 않습니다.";
    private static final String NO_NUMBERS_ERROR = "숫자가 입력되지 않았습니다.";


    public double[] parse(final String input) {

        if (isNullOrEmpty(input)) {
            return new double[]{0.0};
        }

        final String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            return new double[]{0.0};
        }

        if (hasCustomDelimiter(trimmedInput)) {
            return parseWithCustomDelimiter(trimmedInput);
        }

        return parseWithDefaultDelimiter(trimmedInput);
    }

    /**
     * 문자열이 null이거나 비어있는지 확인
     */
    private boolean isNullOrEmpty(final String input) {
        return input == null || input.isEmpty();
    }

    /**
     * 기본 구분자만 사용하는 입력 문자열을 처리 ex) "1,2:3" → [1, 2, 3]
     */
    private double[] parseWithDefaultDelimiter(final String input) {
        final String[] parts = input.split(DEFAULT_DELIMITER, -1);
        return convertToDoubleArray(parts);
    }

    /**
     *  커스텀 구분자 존재 여부 확인
     */
    private boolean hasCustomDelimiter(final String input) {
        final Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
        return pattern.matcher(input).find();
    }

    /**
     * 커스텀 구분자로 구분된 숫자 문자열 처리
     */
    private double[] parseWithCustomDelimiter(final String input) {
        final Pattern pattern = Pattern.compile(CUSTOM_DELIMITER);
        final Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            final String customDelimiter = matcher.group(1);
            final String numbers = matcher.group(2);

            // 커스텀 구분자 + 기본 구분자 모두 사용 가능 처리
            final String combinedDelimiter = Pattern.quote(customDelimiter) + "|" + DEFAULT_DELIMITER;
            return convertToDoubleArray(numbers.split(combinedDelimiter));
        }

        throw new IllegalArgumentException(INVALID_CUSTOM_DELIMITER_ERROR);
    }

    /**
     * 문자열 배열을 double 배열로 변환
     * 각 요소는 공백 제거 후 검증 및 변환
     */
    private double[] convertToDoubleArray(final String[] parts) {
        double[] result = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = parseToDouble(parts[i]);
        }
        return result;
    }

    /**
     * 개별 문자열을 double로 변환
     * 빈 문자열 또는 숫자 형식 오류 시 예외 발생
     */
    private double parseToDouble(final String value) {
        final String trimmedValue = value.trim();
        validateNotEmpty(trimmedValue);
        return parseDoubleValue(trimmedValue);
    }

    /**
     * 문자열이 비어있는지 검증
     */
    private void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(NO_NUMBERS_ERROR);
        }
    }

    /**
     * 문자열을 double로 변환
     */
    private double parseDoubleValue(final String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT_ERROR);
        }
    }
}
