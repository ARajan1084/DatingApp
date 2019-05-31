package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.backend.Login;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a window that asks the user to confirm whether or not they really want to delete their account
 * @author Achintya
 */
public class DeleteAccountConfirmationWindow extends JFrame {
    DashboardWindow myDash;
    AccountService accountService;
    Person user;
    JLabel labelQuestion, labelError;
    JButton buttonCancel, buttonConfirm;

    /**
     * constructs a window that asks whether a user really wants to delete their account
     * @param dashboardWindow the DashboardWindow from which the user clicked the Delete Account button to delete their
     *                        account
     * @param user the user who would like to have their account deleted
     * @param accountService the AccountService of this user
     */
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

    /**
     * helper method that creates the GUI of this window
     * adds JButtons and text (for the instructions/prompts) and sets the background and fonts and colors
     */
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

    /**
     * an ActionListener that tells what to do when the user clicks the Cancel button
     */
    private class ButtonCancelActionListener implements ActionListener {
        /**
         * closes the window
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Confirm button
     */
    private class ButtonConfirmActionListener implements ActionListener {
        /**
         * removes the user from the service/database, permanently deleting their account
         * @param e the event in which the body of the code will be carried out
         */
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
