package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.backend.Login;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteAccountConfirmationWindow extends JFrame {
    DashboardWindow myDash;
    AccountService accountService;
    Person user;
    JLabel labelQuestion, labelError;
    JButton buttonCancel, buttonConfirm;

    public DeleteAccountConfirmationWindow (DashboardWindow dashboardWindow, Person user, AccountService accountService) {
        super("Delete Your Account");
        createView();
        myDash = dashboardWindow;
        this.accountService = accountService;
        this.user = user;
        setResizable(false);
        setSize(new Dimension(700, 100));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createView() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        labelQuestion = new JLabel("Are you sure you want to delete your account? This action is irreversible.");
        JPanel panelButtons = new JPanel();
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ButtonCancelActionListener());
        buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(new ButtonConfirmActionListener());
        panelButtons.add(buttonCancel);
        panelButtons.add(buttonConfirm);
        contentPane.add(labelQuestion);
        contentPane.add(panelButtons);
        labelError = new JLabel("");
        contentPane.add(labelError);
        add(contentPane);
    }

    private class ButtonCancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private class ButtonConfirmActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                accountService.removeUser(user);
                dispose();
                myDash.dispose();
                new LoginWindow();
            } catch (Exception ex) {
                ex.printStackTrace();
                labelError.setText("There was an error with deleting your account. Please try again later.");
            }
        }
    }
}
