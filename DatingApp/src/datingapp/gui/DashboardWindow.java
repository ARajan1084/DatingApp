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
    private final Dimension dashSize = new Dimension(1200, 800);

    public DashboardWindow(Person person) {
        myPerson = person;
        createView();
        setSize(dashSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createView() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        JPanel panelDash = new JPanel();
        panelDash.setLayout(layout);
        panelDash.setPreferredSize(dashSize);
        panelDash.add(westPanel(), BorderLayout.WEST);
        panelDash.setBackground(Color.PINK);
        add(panelDash);
    }

    private JPanel westPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel westPanel = new JPanel();
        westPanel.setLayout(layout);
        westPanel.setBackground(Color.CYAN);
        westPanel.setPreferredSize(new Dimension(250, 800));
        westPanel.add(new ProfilePanel(myPerson), BorderLayout.NORTH);
        return westPanel;
    }
}
