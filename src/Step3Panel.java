import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Step3Panel extends JPanel {

    private final MainFrame frame;
    private final AppState state;
    private final DefaultTableModel tableModel;
    private final JLabel titleLabel;

    public Step3Panel(MainFrame frame, AppState state) {
        this.frame = frame;
        this.state = state;

        setLayout(new BorderLayout(10, 10));
        titleLabel = new JLabel("Step 3: Plan", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        String[] columns = {"Dimension", "Metric", "Coefficient", "Direction", "Range", "Unit"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back");
        JButton next = new JButton("Next");
        buttonPanel.add(back);
        buttonPanel.add(next);
        add(buttonPanel, BorderLayout.SOUTH);

        back.addActionListener(e -> frame.showCard("Step2"));
        next.addActionListener(e -> {
            if (state.getSelectedScenario() == null) {
                JOptionPane.showMessageDialog(this,
                        "Please choose a scenario before continuing.",
                        "Incomplete Selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            frame.showCard("Step4");
        });
    }

    public void refresh() {
        tableModel.setRowCount(0);
        Scenario scenario = state.getSelectedScenario();
        if (scenario == null) {
            titleLabel.setText("Step 3: Plan");
            return;
        }

        titleLabel.setText("Step 3: Plan - " + scenario.getName());
        for (Dimension dimension : scenario.getDimensions()) {
            for (Metric metric : dimension.getMetrics()) {
                String range = String.format("%.1f - %.1f", metric.getMin(), metric.getMax());
                tableModel.addRow(new Object[]{
                        dimension.getName(),
                        metric.getName(),
                        metric.getCoefficient(),
                        metric.getDirection(),
                        range,
                        metric.getUnit()
                });
            }
        }
    }
}
