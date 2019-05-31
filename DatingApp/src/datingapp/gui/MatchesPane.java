package datingapp.gui;
import datingapp.backend.AccountService;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * THE MATCHBOX - displays all the user's matches
 * @author Akanksha
 */
public class MatchesPane extends JPanel {
    private JLabel labelTitle;
    private Person user;
    private AccountService accountService;
    private ArrayList<Person> myMatches;
    public Color blackPearl = new Color(3, 34,54);
    public Color spindle = new Color(192, 200, 205);

    /**
     * constructs the MatchPane
     * @param user the user
     * @param matches the user's matches
     * @param accountService the AccountService
     */
    public MatchesPane(Person user, ArrayList<Person> matches, AccountService accountService) {
        super();
        this.accountService = accountService;
        if (matches == null) {
            displaySorryMessage();
        } else {
            myMatches = matches;
            setPreferredSize(new Dimension(300, 769));
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            createView();
        }
    }

    /**
     * creates the view for the window and formats everything to make it all nice and pretty
     */
    public void createView() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelTitle = new JLabel("Matches (" + myMatches.size() + ")" );
        labelTitle.setFont(new Font("Helvetica", Font.ITALIC, 15));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitle.setForeground(spindle);
        setBackground(blackPearl);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(labelTitle);
        add(Box.createRigidArea(new Dimension(0, 15)));

        for (Person p: myMatches) {
            add(new MatchPanel(p));
            add(Box.createRigidArea(new Dimension(0, 5)));
        }
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    myMatches = accountService.fetchMatches(user);
                    revalidate();
                    repaint();
                } catch (SQLException ex) {
                    displaySorryMessage();
                    ex.printStackTrace();
                } catch (IOException ex) {
                    displaySorryMessage();
                    ex.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * mini method that displays the sorry message for when a user has no matches
     */
    public void displaySorryMessage () {
        JPanel panelSorry = new JPanel();
        panelSorry.add(new JLabel("Sorry. You have no matches yet."));
        add(panelSorry);
    }

    /**
     * creates a mini match panel for each of the user's other matches
     */
    private class MatchPanel extends JPanel {
        /**
         * creates and formats the whole mini panel
         * @param feed the user
         */
        private MatchPanel(Person feed) {
            super();
            GridLayout layout = new GridLayout(1, 2);
            this.setLayout(layout);
            setMaximumSize(new Dimension(400, 100));
            setBackground(blackPearl);

            ImageIcon profilePic = feed.getProfilePic();
            Image temp = profilePic.getImage();
            Image scaledTemp = temp.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            profilePic = new ImageIcon(scaledTemp);
            JLabel labelProfilePic = new JLabel(profilePic);
            add(labelProfilePic, BorderLayout.PAGE_START);

            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
            panelInfo.setBackground(blackPearl);

            JLabel labelName = new JLabel(feed.getName() + ", " + feed.getAge());
            labelName.setForeground(Color.WHITE);
            labelName.setFont(new Font("Helvetica", Font.BOLD, 15));
            JLabel labelEmail = new JLabel(feed.getEmail());
            labelEmail.setForeground(Color.WHITE);
            labelEmail.setFont(new Font("Helvetica",0, 15));
            panelInfo.add(labelName);
            panelInfo.add(labelEmail);
            add(panelInfo, BorderLayout.EAST);
        }
    }
}
