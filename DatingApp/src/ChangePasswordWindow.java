import javafx.scene.control.TextFormatter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        message = new JLabel("Sorry, the developers of this app do not know how to implement password changes yet : (." +
                "Please try to remember your password.");
        buttonGoBack = new JButton("Go Back to Login");
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
