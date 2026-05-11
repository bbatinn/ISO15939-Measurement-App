public class Metric {

    private final String name;
    private final double min;
    private final double max;
    private final boolean higherIsBetter;
    private final int coefficient;
    private final String unit;
    private double rawValue;

    public Metric(String name, double min, double max, boolean higherIsBetter, int coefficient, String unit) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.higherIsBetter = higherIsBetter;
        this.coefficient = coefficient;
        this.unit = unit;
        this.rawValue = min;
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

    public int getCoefficient() {
        return coefficient;
    }

    public String getUnit() {
        return unit;
    }

    public double getRawValue() {
        return rawValue;
    }

    public void setRawValue(double rawValue) {
        this.rawValue = rawValue;
    }

    public String getDirection() {
        return higherIsBetter ? "Higher is better" : "Lower is better";
    }
}