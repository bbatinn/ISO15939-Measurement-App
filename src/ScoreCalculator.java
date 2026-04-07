public class ScoreCalculator {

    public static double calculate(double value, double min, double max, boolean higher) {

        double score;

        if (higher) {
            score = 1 + (value - min) / (max - min) * 4;
        } else {
            score = 5 - (value - min) / (max - min) * 4;
        }

        return Math.round(score * 2) / 2.0;
    }
}