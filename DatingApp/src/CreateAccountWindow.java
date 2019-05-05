import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountWindow extends JFrame {
    private JLabel labelFirstName, labelLastName, labelEmail, labelPassword, labelConfirmPassword, labelGender,
            labelAge, labelSexuality;
    private JTextField fieldFirstName, fieldLastName, fieldEmail, fieldAge;
    private JPasswordField fieldPassword, fieldConfirmPassword;
    private JComboBox comboBoxGender, comboBoxSexuality;
    private JButton buttonCreate, buttonCancel;
    private JFrame loginWindow;

    public CreateAccountWindow (JFrame loginWindow) {
        createView();

        this.loginWindow = loginWindow;
        setTitle("Welcome to TinderButBetter! - Create an Account");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        getContentPane().add(panel);

        labelFirstName = new JLabel("First Name: ");
        labelLastName = new JLabel("Last Name: ");
        labelEmail = new JLabel("Email Address: ");
        labelPassword = new JLabel("Password: ");
        labelConfirmPassword = new JLabel("Confirm Password: ");
        labelGender = new JLabel("Gender:");
        labelAge = new JLabel("Age: ");
        labelSexuality = new JLabel("Sexual Orientation: ");

        fieldFirstName = new JTextField();
        fieldFirstName.setPreferredSize(new Dimension(75, 20));
        fieldLastName = new JTextField();
        fieldLastName.setPreferredSize(new Dimension(75, 20));
        fieldAge = new JTextField();
        fieldAge.setPreferredSize(new Dimension(20, 20));
        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension(175, 20));
        fieldPassword = new JPasswordField();
        fieldPassword.setPreferredSize(new Dimension(175, 20));
        fieldConfirmPassword = new JPasswordField();
        fieldConfirmPassword.setPreferredSize(new Dimension(175, 20));

        comboBoxGender = new JComboBox();
        comboBoxGender.addItem("Male");
        comboBoxGender.addItem("Female");
        comboBoxGender.addItem("Other");
        comboBoxSexuality = new JComboBox<String>();
        comboBoxSexuality.addItem("Heterosexual");
        comboBoxSexuality.addItem("Homosexual");
        comboBoxSexuality.addItem("Bisexual");

        buttonCreate = new JButton("Create!");
        buttonCreate.addActionListener(new buttonCreateActionListener());
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new buttonCancelActionListener());

        panel.add(labelFirstName);
        panel.add(fieldFirstName);
        panel.add(labelLastName);
        panel.add(fieldLastName);
        panel.add(labelEmail);
        panel.add(fieldEmail);
        panel.add(labelPassword);
        panel.add(fieldPassword);
        panel.add(labelConfirmPassword);
        panel.add(fieldConfirmPassword);
        panel.add(labelAge);
        panel.add(fieldAge);
        panel.add(labelGender);
        panel.add(comboBoxGender);
        panel.add(labelSexuality);
        panel.add(comboBoxSexuality);
        panel.add(buttonCreate);
        panel.add(buttonCancel);
    }

    private class buttonCreateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // new CreateAccount(fieldFirstName, fieldLastName, )
        }
    }

    private class buttonCancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            loginWindow.setVisible(true);
        }
    }
}
