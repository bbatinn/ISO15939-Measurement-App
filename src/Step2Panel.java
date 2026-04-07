
import javax.swing.*;
import java.awt.*;

public class Step2Panel extends JPanel {

    public Step2Panel(CardLayout layout, JPanel parent) {

        setLayout(new GridLayout(5,1));

        JLabel label = new JLabel("Step 2: Define Quality", SwingConstants.CENTER);

        // Quality Type
        JRadioButton product = new JRadioButton("Product");
        JRadioButton process = new JRadioButton("Process");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(product);
        qualityGroup.add(process);

        // Mode
        JRadioButton health = new JRadioButton("Health");
        JRadioButton education = new JRadioButton("Education");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(health);
        modeGroup.add(education);

        JButton back = new JButton("Back");

        add(label);
        add(product);
        add(process);
        add(health);
        add(education);
        add(back);

        back.addActionListener(e -> layout.show(parent, "Step1"));
    }
}