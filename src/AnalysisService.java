import java.util.ArrayList;
import java.util.List;

public class AnalysisService {

    public static double calculateMetricScore(Metric metric) {
        return ScoreCalculator.calculate(metric.getRawValue(), metric.getMin(), metric.getMax(), metric.isHigherIsBetter());
    }

    public static double calculateDimensionScore(Dimension dimension) {
        double weightedSum = 0;
        double coefficientSum = 0;
        for (Metric metric : dimension.getMetrics()) {
            double score = calculateMetricScore(metric);
            weightedSum += score * metric.getCoefficient();
            coefficientSum += metric.getCoefficient();
        }
        if (coefficientSum == 0) {
            return 0;
        }
        return Math.round((weightedSum / coefficientSum) * 2) / 2.0;
    }

    public static GapAnalysisResult analyze(List<Dimension> dimensions) {
        if (dimensions == null || dimensions.isEmpty()) {
            return new GapAnalysisResult("No data", 0, 5, "Unknown");
        }

        Dimension lowest = dimensions.get(0);
        double lowestScore = calculateDimensionScore(lowest);
        for (Dimension dimension : dimensions) {
            double score = calculateDimensionScore(dimension);
            if (score < lowestScore) {
                lowestScore = score;
                lowest = dimension;
            }
        }

        String quality = qualityLevel(lowestScore);
        double gap = Math.round((5.0 - lowestScore) * 10) / 10.0;
        return new GapAnalysisResult(lowest.getName(), lowestScore, gap, quality);
    }

    public static String qualityLevel(double score) {
        if (score >= 4.5) {
            return "Excellent";
        }
        if (score >= 3.5) {
            return "Good";
        }
        if (score >= 2.5) {
            return "Needs Improvement";
        }
        return "Poor";
    }

    public static List<Double> collectDimensionScores(List<Dimension> dimensions) {
        List<Double> scores = new ArrayList<>();
        for (Dimension dimension : dimensions) {
            scores.add(calculateDimensionScore(dimension));
        }
        return scores;
    }
}
