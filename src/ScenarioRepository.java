import java.util.ArrayList;
import java.util.List;

public class ScenarioRepository {

    private static final List<Scenario> SCENARIOS = createScenarios();

    public static List<Scenario> getScenarios(String qualityType, String mode) {
        List<Scenario> result = new ArrayList<>();
        for (Scenario scenario : SCENARIOS) {
            if (scenario.getQualityType().equals(qualityType) && scenario.getMode().equals(mode)) {
                result.add(scenario);
            }
        }
        return result;
    }

    public static Scenario getScenarioByName(String name) {
        for (Scenario scenario : SCENARIOS) {
            if (scenario.getName().equals(name)) {
                return scenario;
            }
        }
        return null;
    }

    private static List<Scenario> createScenarios() {
        List<Scenario> scenarios = new ArrayList<>();

        Scenario medCore = new Scenario("Team MedCore", "Product", "Health", "A health product team focused on service availability and safe operation.");
        Dimension medPerformance = new Dimension("Performance", 3);
        medPerformance.addMetric(new Metric("Response Time", 0, 10, false, 2, "seconds"));
        medPerformance.addMetric(new Metric("Availability", 90, 100, true, 1, "%"));
        Dimension medSecurity = new Dimension("Security", 2);
        medSecurity.addMetric(new Metric("Vulnerability Count", 0, 50, false, 2, "items"));
        medSecurity.addMetric(new Metric("Encryption Coverage", 0, 100, true, 1, "%"));
        medCore.addDimension(medPerformance);
        medCore.addDimension(medSecurity);
        scenarios.add(medCore);

        Scenario careFlow = new Scenario("CareFlow", "Process", "Health", "A healthcare process team improving compliance and maintainability.");
        Dimension careCompliance = new Dimension("Compliance", 3);
        careCompliance.addMetric(new Metric("Audit Success", 0, 100, true, 2, "%"));
        careCompliance.addMetric(new Metric("Defect Rate", 0, 20, false, 1, "%"));
        Dimension careMaintainability = new Dimension("Maintainability", 2);
        careMaintainability.addMetric(new Metric("Change Lead Time", 0, 30, false, 2, "days"));
        careMaintainability.addMetric(new Metric("Version Control Usage", 0, 100, true, 1, "%"));
        careFlow.addDimension(careCompliance);
        careFlow.addDimension(careMaintainability);
        scenarios.add(careFlow);

        Scenario alphaLms = new Scenario("Team Alpha LMS", "Product", "Education", "An education product team measuring learning experience quality.");
        Dimension alphaUsability = new Dimension("Usability", 3);
        alphaUsability.addMetric(new Metric("Satisfaction Score", 1, 5, true, 2, "points"));
        alphaUsability.addMetric(new Metric("Learning Time", 0, 20, false, 1, "hours"));
        Dimension alphaContent = new Dimension("Content Quality", 2);
        alphaContent.addMetric(new Metric("Coverage", 0, 100, true, 2, "%"));
        alphaContent.addMetric(new Metric("Update Frequency", 0, 12, true, 1, "per year"));
        alphaLms.addDimension(alphaUsability);
        alphaLms.addDimension(alphaContent);
        scenarios.add(alphaLms);

        Scenario betaLms = new Scenario("Team Beta LMS", "Process", "Education", "An education process team measuring support and adaptability.");
        Dimension betaSupport = new Dimension("Support", 3);
        betaSupport.addMetric(new Metric("Help Response Time", 0, 48, false, 2, "hours"));
        betaSupport.addMetric(new Metric("Training Completion", 0, 100, true, 1, "%"));
        Dimension betaAdaptability = new Dimension("Adaptability", 2);
        betaAdaptability.addMetric(new Metric("Custom Rule Support", 0, 10, true, 2, "items"));
        betaAdaptability.addMetric(new Metric("Integration Success", 0, 100, true, 1, "%"));
        betaLms.addDimension(betaSupport);
        betaLms.addDimension(betaAdaptability);
        scenarios.add(betaLms);

        return scenarios;
    }
}
