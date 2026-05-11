public class ScoreCalculator {

    public static double calculate(double value, double min, double max, boolean higher) {
        if (max <= min) {
            return 3.0;
        }

        double ratio = (value - min) / (max - min);
        ratio = Math.max(0.0, Math.min(1.0, ratio));

        double score;
        if (higher) {
            score = 1 + ratio * 4;
        } else {
            score = 5 - ratio * 4;
        }

        score = Math.max(1.0, Math.min(5.0, score));
        return Math.round(score * 2) / 2.0;
    }
}