package ui;

import model.AppState;
import model.Dimension;
import model.GapAnalysisResult;
import model.Scenario;
import service.AnalysisService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Step5Panel extends JPanel {

    private final MainFrame frame;
    private final AppState state;
    private final JLabel titleLabel;
    private final JPanel barPanel;
    private final RadarChartPanel radarChart;
    private final JLabel summaryLabel;

    public Step5Panel(MainFrame frame, AppState state) {
        this.frame = frame;
        this.state = state;

        setLayout(new BorderLayout(10, 10));
        titleLabel = new JLabel("Step 5: Analyse Results", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        barPanel = new JPanel();
        barPanel.setLayout(new GridLayout(0, 1, 8, 8));
        JPanel left = new JPanel(new BorderLayout(8, 8));
        left.add(new JLabel("Dimension Scores"), BorderLayout.NORTH);
        left.add(barPanel, BorderLayout.CENTER);

        radarChart = new RadarChartPanel();
        JPanel right = new JPanel(new BorderLayout(8, 8));
        right.add(new JLabel("Radar Chart"), BorderLayout.NORTH);
        right.add(radarChart, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(left);
        centerPanel.add(right);
        add(centerPanel, BorderLayout.CENTER);

        summaryLabel = new JLabel("", SwingConstants.CENTER);
        summaryLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        summaryLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back");
        JButton restart = new JButton("Restart");
        navPanel.add(back);
        navPanel.add(restart);

        JPanel southPanel = new JPanel(new BorderLayout(10, 10));
        southPanel.add(summaryLabel, BorderLayout.CENTER);
        southPanel.add(navPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.showCard("Step4"));
        restart.addActionListener(e -> frame.resetApplication());
    }

    public void refresh() {
        barPanel.removeAll();
        if (!state.hasScenario()) {
            titleLabel.setText("Step 5: Analyse Results");
            summaryLabel.setText("No scenario selected.");
            radarChart.setDimensionScores(new ArrayList<>(), new ArrayList<>());
            revalidate();
            repaint();
            return;
        }

        Scenario scenario = state.getSelectedScenario();
        titleLabel.setText("Step 5: Analyse Results - " + scenario.getName());

        List<String> labels = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        for (Dimension dimension : scenario.getDimensions()) {
            double dimensionScore = AnalysisService.calculateDimensionScore(dimension);
            scores.add(dimensionScore);
            labels.add(dimension.getName());

            JProgressBar bar = new JProgressBar(0, 100);
            bar.setValue((int) (dimensionScore * 20));
            bar.setStringPainted(true);
            bar.setString(dimension.getName() + " - " + dimensionScore);
            barPanel.add(bar);
        }

        GapAnalysisResult gapAnalysis = AnalysisService.analyze(scenario.getDimensions());
        summaryLabel.setText(String.format("Lowest dimension: %s | Gap: %.1f | Quality level: %s",
                gapAnalysis.getLowestDimension(), gapAnalysis.getGap(), gapAnalysis.getQualityLevel()));

        radarChart.setDimensionScores(labels, scores);
        revalidate();
        repaint();
    }
}
