package datingapp.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datingapp.gui.DashboardWindow.createSimpleButton;

/**
 * a useless window
 *
 * @author Achintya
 * @date 05/07/19
 */
public class ForgotPasswordWindow extends JFrame {
    private JLabel message;
    private JButton buttonGoBack;
    private JFrame loginWindow;

    /**
     * constructs the ForgotPassword window
     * @param loginWindow the main login window
     */
    public ForgotPasswordWindow(JFrame loginWindow) {
        createView();

        this.loginWindow = loginWindow;
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * creates view + displays message
     */
    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        message = new JLabel("Sorry, the developers of this app do not know how to implement password changes yet :(" +
                " Please try to remember your password.");
        buttonGoBack = createSimpleButton(buttonGoBack, "Go Back to Login Page");
        buttonGoBack.addActionListener(new ButtonGoBackActionListener());
        panel.add(message);
        panel.add(buttonGoBack);
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Go Back button
     */
    private class ButtonGoBackActionListener implements ActionListener {
        /**
         * closes the window
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            loginWindow.setVisible(true);
            dispose();
        }
    }

}
