public class GapAnalysisResult {

    private final String lowestDimension;
    private final double score;
    private final double gap;
    private final String qualityLevel;

    public GapAnalysisResult(String lowestDimension, double score, double gap, String qualityLevel) {
        this.lowestDimension = lowestDimension;
        this.score = score;
        this.gap = gap;
        this.qualityLevel = qualityLevel;
    }

    public String getLowestDimension() {
        return lowestDimension;
    }

    public double getScore() {
        return score;
    }

    public double getGap() {
        return gap;
    }

    public String getQualityLevel() {
        return qualityLevel;
    }
}
