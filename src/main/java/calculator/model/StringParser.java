package calculator.model;

/**
 * 문자열 파서 클래스
 * 입력된 문자열에서 숫자 추출 후, 커스텀 구분자 처리
 */
public class StringParser {

    private static final String DEFAULT_DELIMITER = "[,:]";


    public double[] parse(final String input) {

        if (isNullOrEmpty(input)) {
            return new double[]{0.0};
        }
        final String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            return new double[]{0.0};
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
     * 문자열 배열을 double 배열로 변환한다.
     * 숫자가 아닌 값이 포함되어 있으면 예외를 발생시킨다.
     */
    private double[] convertToDoubleArray(String[] arr) {
        double[] result = new double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            String s = arr[i].trim();

            if (s.isEmpty()) {
                throw new IllegalArgumentException("잘못된 입력 형식입니다. 구분자 사이에 숫자가 없습니다.");
            }

            try {
                result[i] = Double.parseDouble(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
            }
        }
        return result;
    }

}
