package datingapp.gui;

import datingapp.backend.Login;
import datingapp.exceptions.AccountNotFoundException;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static datingapp.gui.DashboardWindow.*;

/**
 * Provides a window where the user can login, or create an account. The window displays an appropriate error message
 * or signs the user into the program.
 *
 * @author Achintya
 * @version 05/20/19
 */
public class LoginWindow extends JFrame {

    private final Color backgroundColor = new Color(204, 218, 252);
    private JLabel labelEmail, labelPassword, labelError;
    private JTextField fieldEmail;
    private JPasswordField passwordField;
    private JButton buttonLogin, buttonCreateAccount, buttonForgotPassword;
    private JFrame loginWindow;

    /**
     * constructs a login window
     */
    public LoginWindow() {
        createView();

        loginWindow = this;
        setTitle("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * helper method responsible for most of the component construction for the window
     */
    private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        getContentPane().add(panel);

        labelEmail = new JLabel("Email: ");
        labelEmail.setFont(FONT_1);
        labelEmail.setForeground(NAVY_BLUE);
        labelPassword = new JLabel("Password: ");
        labelPassword.setFont(FONT_1);
        labelPassword.setForeground(NAVY_BLUE);
        // TODO: Fix Alignment

        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension(175, 20));
        fieldEmail.setBorder(null);
        passwordField = new JPasswordField();
        passwordField.addActionListener(new PasswordFieldActionListener());
        passwordField.setPreferredSize(new Dimension(175, 20));
        passwordField.setBorder(null);
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

        buttonLogin = createSimpleButton(buttonLogin, "Login");
        buttonLogin.addActionListener(new ButtonLoginActionListener());
        buttonCreateAccount = createSimpleButton(buttonCreateAccount, "Create a New Account");
        buttonCreateAccount.addActionListener(new ButtonCreateAccountActionListener());
        buttonForgotPassword = createSimpleButton(buttonForgotPassword, "Forgot Your Password?");
        buttonForgotPassword.addActionListener(new ButtonForgotPasswordActionListener());
        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCreateAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonForgotPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelError = new JLabel();
        labelError.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(panelEmail);
        panel.add(panelPassword);
        panel.add(buttonLogin);
        panel.add(buttonCreateAccount);
        panel.add(buttonForgotPassword);
        panel.add(labelError);
    }

    /**
     * ActionListener for the Login button. Uses AccountService as a backend to check if the given information matches
     * the database records. If it does, the window creates a DashBoardWindow, logging the user in. If it doesnt, the
     * window displays an accurate error message.
     */
    private class ButtonLoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Person login = new Login().isValid(fieldEmail.getText(), new String(passwordField.getPassword()));
                dispose();
                new DashboardWindow(login);
            } catch (AccountNotFoundException ex) {
                fieldEmail.setText("");
                passwordField.setText("");
                labelError.setText("Error: invalid username or password");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                labelError.setText("Internal Error: please try again later");
            } catch (SQLException ex) {
                ex.printStackTrace();
                labelError.setText("Internal Error: please try again later");
            } catch (IOException ex) {
                ex.printStackTrace();
                labelError.setText("Internal Error: please try again later");
            }
        }
    }

    /**
     * ActionListener for the Create Account button. Creates a CreateAccountWindow, disposing of this window.
     */
    private class ButtonCreateAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new CreateAccountWindow(loginWindow);
        }
    }

    /**
     * ActionListener for the Forgot Password button. Creates a ForgotPasswordWindow
     */
    private class ButtonForgotPasswordActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new ForgotPasswordWindow(loginWindow);
        }
    }

    /**
     * ActionListener for the password field. Registers a loginAttempt when the user hits enter in the password field
     */
    private class PasswordFieldActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonLogin.doClick();
        }
    }
}