import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

/**
 *  Provides GUI for registering and logging in users.
 */
public class LoginWindow extends JFrame {

    private JLabel labelEmail, labelPassword;
    private JTextField fieldEmail;
    private JPasswordField passwordField;
    private JButton buttonLogin, buttonCreateAccount;
    private JFrame loginWindow;

    public LoginWindow () {
        createView();

        loginWindow = this;
        setTitle("Welcome to TinderButBetter!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.PINK);
        getContentPane().add(panel);

        labelEmail = new JLabel("Email: ");
        labelPassword = new JLabel("Password: ");

        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension (175, 20));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension (175, 20));

        JPanel panelEmail = new JPanel();
        panelEmail.setBackground(Color.PINK);
        panelEmail.add(labelEmail);
        panelEmail.add(fieldEmail);
        JPanel panelPassword = new JPanel();
        panelPassword.setBackground(Color.PINK);
        panelPassword.add(labelPassword);
        panelPassword.add(passwordField);

        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ButtonLoginActionListener());
        buttonCreateAccount = new JButton("Create a New Account");
        buttonCreateAccount.addActionListener(new ButtonCreateAccountActionListener());

        panel.add(panelEmail);
        panel.add(panelPassword);
        panel.add(buttonLogin);
        panel.add(buttonCreateAccount);
    }

    public static void main(String[]args) {

        new LoginWindow().setVisible(true);
    }

    private class ButtonLoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            char[] passArr =  passwordField.getPassword();
            String password = "";
            for (char c: passArr)
            {
                password += c;
            }
            try {
                new Login().isValid(fieldEmail.getText(), password);
            } catch (AccountNotFoundException exception) {
                fieldEmail.setText("");
                passwordField.setText("");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private class ButtonCreateAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new CreateAccountWindow(loginWindow);
        }
    }
}