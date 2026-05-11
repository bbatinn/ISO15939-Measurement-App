package model;

public class AppState {

    private Profile profile;
    private String qualityType;
    private String mode;
    private Scenario selectedScenario;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getQualityType() {
        return qualityType;
    }

    public void setQualityType(String qualityType) {
        this.qualityType = qualityType;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Scenario getSelectedScenario() {
        return selectedScenario;
    }

    public void setSelectedScenario(Scenario selectedScenario) {
        this.selectedScenario = selectedScenario;
        if (selectedScenario != null) {
            for (Dimension dimension : selectedScenario.getDimensions()) {
                for (Metric metric : dimension.getMetrics()) {
                    metric.setRawValue(metric.getMin());
                }
            }
        }
    }

    public void reset() {
        profile = null;
        qualityType = null;
        mode = null;
        selectedScenario = null;
    }

    public boolean hasScenario() {
        return selectedScenario != null;
    }
}
