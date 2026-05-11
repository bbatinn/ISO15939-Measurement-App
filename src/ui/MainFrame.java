package ui;

import model.AppState;
import model.Profile;
import model.Scenario;
import service.AnalysisService;
import service.ScoreCalculator;
import service.ScenarioRepository;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JLabel stepIndicatorLabel;
    private AppState state;
    private Step1Panel step1Panel;
    private Step2Panel step2Panel;
    private Step3Panel step3Panel;
    private Step4Panel step4Panel;
    private Step5Panel step5Panel;

    public MainFrame() {
        setTitle("ISO 15939 Measurement Simulator");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        state = new AppState();

        stepIndicatorLabel = new JLabel("", SwingConstants.CENTER);
        stepIndicatorLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        stepIndicatorLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(stepIndicatorLabel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        step1Panel = new Step1Panel(this, state);
        step2Panel = new Step2Panel(this, state);
        step3Panel = new Step3Panel(this, state);
        step4Panel = new Step4Panel(this, state);
        step5Panel = new Step5Panel(this, state);

        mainPanel.add(step1Panel, "Step1");
        mainPanel.add(step2Panel, "Step2");
        mainPanel.add(step3Panel, "Step3");
        mainPanel.add(step4Panel, "Step4");
        mainPanel.add(step5Panel, "Step5");

        add(mainPanel, BorderLayout.CENTER);
        showCard("Step1");
        setVisible(true);
    }

    public void showCard(String name) {
        if ("Step2".equals(name)) {
            step2Panel.refresh();
        } else if ("Step3".equals(name)) {
            step3Panel.refresh();
        } else if ("Step4".equals(name)) {
            step4Panel.refresh();
        } else if ("Step5".equals(name)) {
            step5Panel.refresh();
        }

        updateStepIndicator(name);
        cardLayout.show(mainPanel, name);
    }

    public void resetApplication() {
        state.reset();
        step1Panel.reset();
        step2Panel.refresh();
        step3Panel.refresh();
        step4Panel.refresh();
        step5Panel.refresh();
        showCard("Step1");
    }

    private void updateStepIndicator(String step) {
        String profile = step.equals("Step1") ? "<b>Profile</b>" : "Profile";
        String define = step.equals("Step2") ? "<b>Define</b>" : "Define";
        String plan = step.equals("Step3") ? "<b>Plan</b>" : "Plan";
        String collect = step.equals("Step4") ? "<b>Collect</b>" : "Collect";
        String analyse = step.equals("Step5") ? "<b>Analyse</b>" : "Analyse";
        stepIndicatorLabel.setText(String.format("<html>%s → %s → %s → %s → %s</html>", profile, define, plan, collect, analyse));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}