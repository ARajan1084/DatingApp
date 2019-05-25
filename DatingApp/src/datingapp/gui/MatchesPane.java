package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class MatchesPane extends JScrollPane {
    private JLabel labelTitle;
    private ArrayList<Person> matches;

    public MatchesPane(ArrayList<Person> matches) {
        super();
        this.matches = matches;
        setPreferredSize(new Dimension(600, 370));
        createView();
    }

    public void createView() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelTitle = new JLabel("My Matches");
        add(labelTitle);
        for (Person p: matches) {
            add(new MatchPanel(p));
        }
    }

    private class MatchPanel extends JPanel {
        private MatchPanel(Person p) {
            super();
            BorderLayout layout = new BorderLayout();
            setLayout(layout);
            setPreferredSize(new Dimension(450, 100));

            ImageIcon profilePic = p.getProfilePic();
            Image temp = profilePic.getImage();
            Image scaledTemp = temp.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            profilePic = new ImageIcon(scaledTemp);
            JLabel labelProfilePic = new JLabel(profilePic);
            add(labelProfilePic, BorderLayout.WEST);

            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JTextArea bio = new JTextArea();
            bio.setLineWrap(true);
            bio.setWrapStyleWord(true);

            panelInfo.add(new JLabel(p.getName() + ": " + p.getAge() + " years"));
            panelInfo.add(bio);
        }
    }
}
