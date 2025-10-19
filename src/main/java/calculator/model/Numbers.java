package calculator.model;

public class Numbers {


    private final double[] values;

    public Numbers(final double[] values) {

        this.values = values;
    }


    public double sum() {
        double total = 0.0;
        for (final double value : values) {
            total += value;
        }
        return total;
    }



}
