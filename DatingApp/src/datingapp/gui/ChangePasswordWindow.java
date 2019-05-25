package datingapp.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datingapp.gui.DashboardWindow.createSimpleButton;

/**
 * a useless window
 *
 * @author Achintya
 * @date 05/07/19
 */
public class ChangePasswordWindow extends JFrame {
    private JLabel message;
    private JButton buttonGoBack;
    private JFrame loginWindow;

    public ChangePasswordWindow(JFrame loginWindow) {
        createView();

        this.loginWindow = loginWindow;
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

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

    private class ButtonGoBackActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginWindow.setVisible(true);
            dispose();
        }
    }

}
