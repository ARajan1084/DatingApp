package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;

/**
 * main GUI interface for user interactions. Users answer questions, communicate with each other, and view profiles
 * on this window
 *
 * @author Akanksha
 * @version 05/09
 */
public class DashboardWindow extends JFrame {

    private Person myPerson;

    public DashboardWindow(Person person) {
        createView();
        setSize(new Dimension(1000, 800));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createView() {
        BorderLayout layout = new BorderLayout();
    }

    private Panel eastPanel() {
        return null; // TODO: fix
    }

    private Panel westPanel() {
        return null; // TODO: fix
    }
}
