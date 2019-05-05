import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 *  Provides GUI for registering and logging in users.
 */
public class LoginWindow extends JFrame {

    private JLabel labelEmail, labelPassword;
    private JTextField fieldEmail;
    private JPasswordField passwordField;
    private JButton buttonLogin, buttonCreateAccount, buttonForgotPassword;
    private JFrame loginWindow;
    private final Color backgroundColor = Color.PINK;

    public LoginWindow () {
        createView();

        loginWindow = this;
        setTitle("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        getContentPane().add(panel);

        labelEmail = new JLabel("Email: ");
        labelPassword = new JLabel("Password: ");
        // TODO: Fix Alignment

        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension (175, 20));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension (175, 20));
        // TODO: Fix Alignment

        JPanel panelEmail = new JPanel();
        panelEmail.setBackground(backgroundColor);
        panelEmail.setMaximumSize(new Dimension(300, 30));
        panelEmail.add(labelEmail);
        panelEmail.add(fieldEmail);

        JPanel panelPassword = new JPanel();
        panelPassword.setBackground(backgroundColor);
        panelPassword.setMaximumSize(new Dimension(300, 30));
        panelPassword.setAlignmentY(Component.TOP_ALIGNMENT);
        panelPassword.add(labelPassword);
        panelPassword.add(passwordField);

        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(new ButtonLoginActionListener());
        buttonCreateAccount = new JButton("Create a New Account");
        buttonCreateAccount.addActionListener(new ButtonCreateAccountActionListener());
        buttonForgotPassword = new JButton("Forgot Your Password?");
        buttonForgotPassword.addActionListener(new ButtonForgotPasswordActionListener());
        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreateAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonForgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(panelEmail);
        panel.add(panelPassword);
        panel.add(buttonLogin);
        panel.add(buttonCreateAccount);
        panel.add(buttonForgotPassword);
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

    private class ButtonForgotPasswordActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new ChangePasswordWindow(loginWindow);
        }
    }
}