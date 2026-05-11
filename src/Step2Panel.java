import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Step2Panel extends JPanel {

    private final MainFrame frame;
    private final AppState state;
    private final JRadioButton product;
    private final JRadioButton process;
    private final JRadioButton health;
    private final JRadioButton education;
    private final DefaultComboBoxModel<String> scenarioModel;
    private final JComboBox<String> scenarioCombo;

    public Step2Panel(MainFrame frame, AppState state) {
        this.frame = frame;
        this.state = state;

        setLayout(new BorderLayout(10, 10));
        JLabel title = new JLabel("Step 2: Define Quality Scope", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel optionPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel qualityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        product = new JRadioButton("Product");
        process = new JRadioButton("Process");
        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(product);
        qualityGroup.add(process);
        qualityPanel.setBorder(BorderFactory.createTitledBorder("Quality Type"));
        qualityPanel.add(product);
        qualityPanel.add(process);

        health = new JRadioButton("Health");
        education = new JRadioButton("Education");
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(health);
        modeGroup.add(education);
        modePanel.setBorder(BorderFactory.createTitledBorder("Mode"));
        modePanel.add(health);
        modePanel.add(education);

        optionPanel.add(qualityPanel);
        optionPanel.add(modePanel);
        add(optionPanel, BorderLayout.CENTER);

        JPanel scenarioPanel = new JPanel(new BorderLayout(10, 10));
        scenarioPanel.setBorder(BorderFactory.createTitledBorder("Scenario"));
        scenarioModel = new DefaultComboBoxModel<>();
        scenarioCombo = new JComboBox<>(scenarioModel);
        scenarioPanel.add(scenarioCombo, BorderLayout.CENTER);
        add(scenarioPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton back = new JButton("Back");
        JButton next = new JButton("Next");
        buttonPanel.add(back);
        buttonPanel.add(next);
        add(buttonPanel, BorderLayout.PAGE_END);

        ActionListener filterAction = e -> refreshScenarioList();
        product.addActionListener(filterAction);
        process.addActionListener(filterAction);
        health.addActionListener(filterAction);
        education.addActionListener(filterAction);

        back.addActionListener(e -> frame.showCard("Step1"));
        next.addActionListener(e -> {
            String qualityType = product.isSelected() ? "Product" : process.isSelected() ? "Process" : null;
            String mode = health.isSelected() ? "Health" : education.isSelected() ? "Education" : null;
            String scenarioName = (String) scenarioCombo.getSelectedItem();

            if (qualityType == null || mode == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select quality type and mode.",
                        "Selection Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (scenarioName == null || scenarioName.isEmpty() || scenarioName.startsWith("No ")) {
                JOptionPane.showMessageDialog(this,
                        "Please choose a valid scenario.",
                        "Scenario Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Scenario scenario = ScenarioRepository.getScenarioByName(scenarioName);
            if (scenario == null) {
                JOptionPane.showMessageDialog(this,
                        "Scenario not found.",
                        "Data Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            state.setQualityType(qualityType);
            state.setMode(mode);
            state.setSelectedScenario(scenario);
            frame.showCard("Step3");
        });

        refreshScenarioList();
    }

    public void refresh() {
        if ("Product".equals(state.getQualityType())) {
            product.setSelected(true);
        } else if ("Process".equals(state.getQualityType())) {
            process.setSelected(true);
        } else {
            product.setSelected(false);
            process.setSelected(false);
        }

        if ("Health".equals(state.getMode())) {
            health.setSelected(true);
        } else if ("Education".equals(state.getMode())) {
            education.setSelected(true);
        } else {
            health.setSelected(false);
            education.setSelected(false);
        }

        refreshScenarioList();
        if (state.getSelectedScenario() != null) {
            scenarioCombo.setSelectedItem(state.getSelectedScenario().getName());
        }
    }

    private void refreshScenarioList() {
        scenarioModel.removeAllElements();

        String qualityType = product.isSelected() ? "Product" : process.isSelected() ? "Process" : null;
        String mode = health.isSelected() ? "Health" : education.isSelected() ? "Education" : null;

        if (qualityType != null && mode != null) {
            List<Scenario> scenarios = ScenarioRepository.getScenarios(qualityType, mode);
            if (scenarios.isEmpty()) {
                scenarioModel.addElement("No scenarios available");
            } else {
                for (Scenario scenario : scenarios) {
                    scenarioModel.addElement(scenario.getName());
                }
            }
        } else {
            scenarioModel.addElement("Select type and mode first");
        }
    }
}