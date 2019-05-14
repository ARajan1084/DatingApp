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
        setBackground(new Color(105, 126, 108));

    }

    private void createView() {
        BorderLayout layout = new BorderLayout();
        add(new ProfilePanel(myPerson), layout.EAST);
    }

    public static void main (String[] args)
    {
       // new DashboardWindow(new Person )
    }
}
