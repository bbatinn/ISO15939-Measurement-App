public class Metric {

    private String name;
    private double min;
    private double max;
    private boolean higherIsBetter;

    public Metric(String name, double min, double max, boolean higherIsBetter) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.higherIsBetter = higherIsBetter;
    }

    public String getName() {
        return name;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean isHigherIsBetter() {
        return higherIsBetter;
    }
}