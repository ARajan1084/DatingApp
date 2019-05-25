package datingapp.gui;

import datingapp.backend.Login;
import datingapp.program.Chat;
import datingapp.program.Person;
import datingapp.exceptions.AccountNotFoundException;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import static datingapp.gui.DashboardWindow.*;

/**
 *  Provides GUI for registering and logging in users.
 */
public class LoginWindow extends JFrame {

    private JLabel labelEmail, labelPassword, labelError;
    private JTextField fieldEmail;
    private JPasswordField passwordField;
    private JButton buttonLogin, buttonCreateAccount, buttonForgotPassword;
    private JFrame loginWindow;
    private final Color backgroundColor = new Color(204, 218, 252);

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
        labelEmail.setFont(FONT_1);
        labelEmail.setForeground(NAVY_BLUE);
        labelPassword = new JLabel("Password: ");
        labelPassword.setFont(FONT_1);
        labelPassword.setForeground(NAVY_BLUE);
        // TODO: Fix Alignment

        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension (175, 20));
        fieldEmail.setBorder(null);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension (175, 20));
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

    public static void main (String[]args) {
        new LoginWindow();
    }

    private class ButtonLoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                try {
                    Person login = new Login().isValid(fieldEmail.getText(), new String(passwordField.getPassword()));
                    if (login != null) {
                        dispose();
                        new DashboardWindow(login, new ArrayList<Chat>(), new ArrayList<Person>());
                    } else {
                        passwordField.setText("");
                        labelError.setText("Error: Invalid Password.");
                    }
                } catch (ClassNotFoundException ex) {
                    throw new AccountNotFoundException();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    labelError.setText("Internal Error: please try again later");
                }
            } catch (AccountNotFoundException exception) {
                fieldEmail.setText("");
                passwordField.setText("");
                labelError.setText("Account not found.");
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