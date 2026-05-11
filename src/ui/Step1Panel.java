package ui;

import model.AppState;
import model.Profile;
import javax.swing.*;
import java.awt.*;

public class Step1Panel extends JPanel {

    private final MainFrame frame;
    private final AppState state;
    private final JTextField username;
    private final JTextField school;
    private final JTextField session;

    public Step1Panel(MainFrame frame, AppState state) {
        this.frame = frame;
        this.state = state;

        setLayout(new BorderLayout(10, 10));
        JLabel title = new JLabel("Step 1: Profile Information", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        username = new JTextField();
        school = new JTextField();
        session = new JTextField();

        form.add(new JLabel("Username:"));
        form.add(username);
        form.add(new JLabel("School:"));
        form.add(school);
        form.add(new JLabel("Session Name:"));
        form.add(session);

        add(form, BorderLayout.CENTER);

        JButton next = new JButton("Next");
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.add(next);
        add(actionPanel, BorderLayout.SOUTH);

        next.addActionListener(e -> {
            if (username.getText().trim().isEmpty() ||
                school.getText().trim().isEmpty() ||
                session.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "All fields must be filled before continuing.",
                        "Missing Data",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            state.setProfile(new Profile(username.getText().trim(), school.getText().trim(), session.getText().trim()));
            frame.showCard("Step2");
        });
    }

    public void reset() {
        username.setText("");
        school.setText("");
        session.setText("");
    }
}