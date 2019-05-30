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

public class MatchesPane extends JPanel {
    private JLabel labelTitle;
    private Person user;
    private AccountService accountService;
    private ArrayList<Person> myMatches;
    public Color blackPearl = new Color(3, 34,54);
    public Color spindle = new Color(192, 200, 205);

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

    public void createView() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelTitle = new JLabel("Matches (" + myMatches.size() + ")" );
        labelTitle.setForeground(spindle);
        setBackground(blackPearl);
        add(labelTitle);
        for (Person p: myMatches) {
            add(new MatchPanel(p));
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
        }, 0, 15, TimeUnit.SECONDS);
    }

    public void displaySorryMessage () {
        JPanel panelSorry = new JPanel();
        panelSorry.add(new JLabel("Sorry. You have no matches yet."));
        add(panelSorry);
    }

    private class MatchPanel extends JPanel {

        private MatchPanel(Person feed) {
            super();
            GridLayout layout = new GridLayout(1, 2);
            this.setLayout(layout);
            setMaximumSize(new Dimension(450, 100));

            /* TODO remove comment
            ImageIcon profilePic = p.getProfilePic();
            Image temp = profilePic.getImage();
            Image scaledTemp = temp.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            profilePic = new ImageIcon(scaledTemp);
            JLabel labelProfilePic = new JLabel(profilePic);
            add(labelProfilePic, BorderLayout.PAGE_START);

             */

            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

            /*
            JTextArea bio = new JTextArea();
            bio.setLineWrap(true);
            bio.setEditable(false);
            bio.setWrapStyleWord(true);

             */

            JLabel labelName = new JLabel(feed.getName() + ", " + feed.getAge());
            labelName.setFont(new Font("Helvetica", Font.BOLD, 15));

            //panelInfo.add(new JLabel(p.getName() + ": " + p.getAge() + " years"));
            //panelInfo.add(bio);
            panelInfo.add(labelName);
            add(panelInfo, BorderLayout.EAST);
        }
    }
}
