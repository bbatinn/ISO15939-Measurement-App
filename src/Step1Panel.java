import javax.swing.*;
import java.awt.*;

public class Step1Panel extends JPanel {

    public Step1Panel(CardLayout layout, JPanel parent) {
        setLayout(new GridLayout(4,2));

        JTextField username = new JTextField();
        JTextField school = new JTextField();
        JTextField session = new JTextField();

        JButton next = new JButton("Next");

        add(new JLabel("Username:"));
        add(username);

        add(new JLabel("School:"));
        add(school);

        add(new JLabel("Session:"));
        add(session);

        add(new JLabel(""));
        add(next);

        next.addActionListener(e -> {
            if(username.getText().isEmpty() ||
               school.getText().isEmpty() ||
               session.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "All fields must be filled!");
            } else {
                layout.show(parent, "Step2");
            }
        });
    }
}