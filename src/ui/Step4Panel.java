package ui;

import model.AppState;
import model.Dimension;
import model.Metric;
import model.Scenario;
import service.AnalysisService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Step4Panel extends JPanel {

    private final MainFrame frame;
    private final AppState state;
    private final JPanel inputPanel;
    private final List<JSpinner> spinners;
    private final List<JLabel> scoreLabels;
    private final JLabel titleLabel;

    public Step4Panel(MainFrame frame, AppState state) {
        this.frame = frame;
        this.state = state;
        this.spinners = new ArrayList<>();
        this.scoreLabels = new ArrayList<>();

        setLayout(new BorderLayout(10, 10));
        titleLabel = new JLabel("Step 4: Collect Raw Data", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 1, 8, 8));
        add(new JScrollPane(inputPanel), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back");
        JButton calculate = new JButton("Calculate Scores");
        JButton next = new JButton("Next");
        buttonPanel.add(back);
        buttonPanel.add(calculate);
        buttonPanel.add(next);
        add(buttonPanel, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.showCard("Step3"));
        calculate.addActionListener(e -> updateScores());
        next.addActionListener(e -> {
            updateScores();
            if (!state.hasScenario()) {
                JOptionPane.showMessageDialog(this,
                        "Please select a scenario first.",
                        "No Scenario",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            frame.showCard("Step5");
        });
    }

    public void refresh() {
        inputPanel.removeAll();
        spinners.clear();
        scoreLabels.clear();

        Scenario scenario = state.getSelectedScenario();
        if (scenario == null) {
            titleLabel.setText("Step 4: Collect Raw Data");
            inputPanel.add(new JLabel("No scenario selected.", SwingConstants.CENTER));
            revalidate();
            repaint();
            return;
        }

        titleLabel.setText("Step 4: Collect Raw Data - " + scenario.getName());
        for (Dimension dimension : scenario.getDimensions()) {
            JPanel dimensionHeader = new JPanel(new BorderLayout());
            dimensionHeader.add(new JLabel("Dimension: " + dimension.getName()), BorderLayout.WEST);
            inputPanel.add(dimensionHeader);

            for (Metric metric : dimension.getMetrics()) {
                JPanel row = new JPanel(new GridLayout(1, 4, 10, 0));
                JLabel nameLabel = new JLabel(metric.getName());
                JSpinner spinner = new JSpinner(new SpinnerNumberModel(metric.getRawValue(), metric.getMin(), metric.getMax(), 0.5));
                JLabel unitLabel = new JLabel(metric.getUnit());
                JLabel scoreLabel = new JLabel("Score: -", SwingConstants.CENTER);

                row.add(nameLabel);
                row.add(spinner);
                row.add(unitLabel);
                row.add(scoreLabel);
                inputPanel.add(row);

                spinners.add(spinner);
                scoreLabels.add(scoreLabel);
            }
        }

        revalidate();
        repaint();
    }

    private void updateScores() {
        if (!state.hasScenario()) {
            return;
        }

        int metricIndex = 0;
        for (Dimension dimension : state.getSelectedScenario().getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                if (metricIndex >= spinners.size()) {
                    return;
                }
                JSpinner spinner = spinners.get(metricIndex);
                JLabel scoreLabel = scoreLabels.get(metricIndex);
                double rawValue = ((Number) spinner.getValue()).doubleValue();
                metric.setRawValue(rawValue);
                double score = AnalysisService.calculateMetricScore(metric);
                scoreLabel.setText(String.format("Score: %.1f", score));
                metricIndex++;
            }
        }
    }
}
