package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RadarChartPanel extends JPanel {

    private List<String> labels = new ArrayList<>();
    private List<Double> values = new ArrayList<>();

    public RadarChartPanel() {
        setPreferredSize(new java.awt.Dimension(380, 320));
    }

    public void setDimensionScores(List<String> labels, List<Double> values) {
        this.labels = labels;
        this.values = values;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2 - 40;

        if (values.isEmpty() || labels.isEmpty()) {
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("No analysis data available.", centerX - 60, centerY);
            g2.dispose();
            return;
        }

        int count = values.size();
        for (int ring = 1; ring <= 4; ring++) {
            int ringRadius = radius * ring / 4;
            g2.setColor(new Color(180, 180, 180));
            g2.drawOval(centerX - ringRadius, centerY - ringRadius, ringRadius * 2, ringRadius * 2);
        }

        Polygon radar = new Polygon();
        for (int i = 0; i < count; i++) {
            double angle = -Math.PI / 2 + i * 2 * Math.PI / count;
            double ratio = (values.get(i) - 1.0) / 4.0;
            ratio = Math.max(0.0, Math.min(1.0, ratio));
            int pointRadius = (int) (ratio * radius);
            int x = centerX + (int) (Math.cos(angle) * pointRadius);
            int y = centerY + (int) (Math.sin(angle) * pointRadius);
            radar.addPoint(x, y);
            g2.setColor(Color.GRAY);
            int xAxis = centerX + (int) (Math.cos(angle) * radius);
            int yAxis = centerY + (int) (Math.sin(angle) * radius);
            g2.drawLine(centerX, centerY, xAxis, yAxis);
            g2.setColor(Color.BLACK);
            int labelX = centerX + (int) (Math.cos(angle) * (radius + 20));
            int labelY = centerY + (int) (Math.sin(angle) * (radius + 20));
            g2.drawString(labels.get(i), labelX - 20, labelY);
        }

        g2.setColor(new Color(0, 120, 215, 90));
        g2.fillPolygon(radar);
        g2.setColor(new Color(0, 78, 140));
        g2.setStroke(new BasicStroke(2));
        g2.drawPolygon(radar);

        g2.dispose();
    }
}
