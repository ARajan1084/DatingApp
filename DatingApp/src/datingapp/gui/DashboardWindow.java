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
        myPerson = person;
        createView();
        setSize(new Dimension(1000, 800));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(105, 126, 108));

    }

    private void createView() {
        BorderLayout layout = new BorderLayout();
        add(new ProfilePanel(myPerson), layout.WEST);
    }

    private JPanel westPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel westPanel = new JPanel();
        westPanel.setMaximumSize(new Dimension(300, 800));
        westPanel.add(new ProfilePanel(myPerson), layout.NORTH);
        return westPanel;
    }

    public static void main (String[] args)
    {
       // new DashboardWindow(new Person )
    }
}
