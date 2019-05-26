package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class MatchesPane extends JPanel {
    private JLabel labelTitle;
    private ArrayList<Person> myMatches;

    public Color blackPearl = new Color(3, 34,54);
    public Color spindle = new Color(192, 200, 205);

    public MatchesPane(ArrayList<Person> matches) {
        super();
        myMatches = matches;
        setPreferredSize(new Dimension(300, 769));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createView();
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
    }

    private class MatchPanel extends JPanel {
        private MatchPanel(Person p) {
            super();
            GridLayout layout = new GridLayout(1, );
            this.setLayout(layout);
            setMaximumSize(new Dimension(450, 100));

            ImageIcon profilePic = p.getProfilePic();
            Image temp = profilePic.getImage();
            Image scaledTemp = temp.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
            profilePic = new ImageIcon(scaledTemp);
            JLabel labelProfilePic = new JLabel(profilePic);
            add(labelProfilePic, BorderLayout.WEST);

            JPanel panelInfo = new JPanel();
            panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

            /*
            JTextArea bio = new JTextArea();
            bio.setLineWrap(true);
            bio.setEditable(false);
            bio.setWrapStyleWord(true);

             */

            JLabel labelName = new JLabel(p.getName() + ", " + p.getAge());
            JLabel labelBio = new JLabel(p.getBio());

            //panelInfo.add(new JLabel(p.getName() + ": " + p.getAge() + " years"));
            //panelInfo.add(bio);
            panelInfo.add(labelName);
            panelInfo.add(labelBio);
            add(panelInfo, BorderLayout.EAST);
        }
    }
}
