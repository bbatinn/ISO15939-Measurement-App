import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    CardLayout cardLayout;
    JPanel mainPanel;

    public MainFrame() {
        setTitle("ISO 15939 Measurement Simulator");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new Step1Panel(cardLayout, mainPanel), "Step1");
        mainPanel.add(new Step2Panel(cardLayout, mainPanel), "Step2");

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}