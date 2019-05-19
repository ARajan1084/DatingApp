package datingapp.gui;

import datingapp.program.Chat;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * main GUI interface for user interactions. Users answer questions, communicate with each other, and view profiles
 * on this window.
 * The user's profile is displayed in the top left panel. The user's current chats are displayed in the top right panel.
 * The user's questions are displayed in the center panel. A logout button exists in the top of the center panel.
 * The user's matches are displayed in the bottom half of the center panel and message requests are displayed in the
 * bottom left panel
 *
 * @author Akanksha
 * @version 05/09
 */
public class DashboardWindow extends JFrame {

    private Person myPerson;
    private ArrayList<Chat> myChats;
    private ArrayList<Person> myMatches;
    private final Dimension dashSize = new Dimension(1200, 769);

    /**
     * constructs a window that serves as the user's dashboard
     * @param person person object to pull the profile panel from
     * @param chats list of chats to pull the chats panel from
     * @param matches list of matches to pull the matches pane from
     */
    public DashboardWindow(Person person, ArrayList<Chat> chats, ArrayList<Person> matches) {
        myPerson = person;
        myChats = chats;
        myMatches = matches;
        createView();
        setSize(dashSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * helper method that primarily creates the GUI
     */
    private void createView() {
        BorderLayout layout = new BorderLayout();
        JPanel panelDash = new JPanel();
        panelDash.setLayout(layout);
        panelDash.setPreferredSize(dashSize);
        panelDash.add(centerPanel(), BorderLayout.CENTER);
        panelDash.add(westPanel(), BorderLayout.WEST);
        panelDash.add(eastPanel(), BorderLayout.EAST);
        panelDash.setBackground(Color.PINK);
        add(panelDash);
    }

    /**
     * helper method that constructs the west panel
     * @return completed west panel
     */
    private JPanel westPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel westPanel = new JPanel();
        westPanel.setLayout(layout);
        westPanel.setBackground(Color.CYAN);
        westPanel.setPreferredSize(new Dimension(250, 800));
        westPanel.add(new ProfilePanel(myPerson), BorderLayout.NORTH);
        return westPanel;
    }

    /**
     * helper method that constructs the center panel
     * @return completed center panel
     */
    private JPanel centerPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(layout);
        centerPanel.add(centerNorthPanel(), BorderLayout.NORTH);
        centerPanel.add(centerSouthPane(), BorderLayout.SOUTH);
        centerPanel.setBackground(Color.PINK);
        return centerPanel;
    }

    /**
     * helper method of centerPanel() that constructs the Logout button and its action listener
     * @return completed north panel of centerPanel()
     */
    private JPanel centerNorthPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel northPanel = new JPanel();
        northPanel.setLayout(layout);
        northPanel.setPreferredSize(new Dimension(500, 30));
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.addActionListener(new ButtonLogoutActionListener());
        buttonLogout.setAlignmentX(Component.RIGHT_ALIGNMENT);
        northPanel.add(buttonLogout, BorderLayout.EAST);
        return northPanel;
    }

    /**
     * helper method of centerPanel() that constructs the bottom half where matches are displayed
     * @return completed south pane of centerPanel()
     */
    private JScrollPane centerSouthPane() {
        JScrollPane southPane = new MatchesPane(myMatches);
        return southPane;
    }

    /**
     * helper method that constructs the east Panel
     * @return completed
     */
    private JPanel eastPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(layout);
        eastPanel.setPreferredSize(new Dimension(250, 800));
        eastPanel.add(new ChatsPanel(myPerson, myChats), BorderLayout.NORTH);
        return eastPanel;
    }

    /**
     * Action Listener for Logout Button
     */
    private class ButtonLogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginWindow();
        }
    }
}
