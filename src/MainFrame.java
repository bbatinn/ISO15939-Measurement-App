import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
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

        state = new AppState();

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

        add(mainPanel);
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
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}