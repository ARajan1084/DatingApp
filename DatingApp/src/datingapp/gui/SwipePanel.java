package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.program.Person;
import datingapp.program.Tree;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static datingapp.gui.DashboardWindow.*;

public class SwipePanel extends JPanel
{
    Person myPerson, currentPerson;
    private ArrayList<Person> potentialMatches;
    private int currentPersonIndex;
    private AccountService acctServ;
    //ArrayList<Person> myMatches;
    private JLabel labelTitle, labelName, labelBio;
    private JTextArea textAreaBio;
    private JButton buttonYeah, buttonNah;
    public final Color redOxide = new Color(77, 21, 18);
    public final Color oysterPink = new Color(210, 176, 174);
    private final Font fontBold = new Font("Helvetica", Font.BOLD, 15);
    private final Font fontItal = new Font("Helvetica", Font.ITALIC, 15);
    private final Font fontNone = new Font("Helvetica", 0, 15);

    public SwipePanel (Person feed, ArrayList<Person> potentialMatches, AccountService acctServ) {
        super();
        myPerson = feed;
        this.potentialMatches = potentialMatches;
        currentPersonIndex = 0;
        this.acctServ = acctServ;
        setPreferredSize(new Dimension(280, 800));
        //myMatches = feed;
        createView();

        if (potentialMatches == null || potentialMatches.isEmpty())
        {
            displaySorryMessage();
        }
        else
        {
            displayCurrentPerson();
        }
    }

    public void createView () {
        /*
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ImageIcon profilePic = myPerson.getProfilePic();
        setLayout(new GridLayout(1, 3));

        JPanel panelProfile = new JPanel();
        panelProfile.setMaximumSize(new Dimension(250, 350));
        panelProfile.setBackground(redOxide);
        ImageIcon profilePic = myMatches.get(0).getProfilePic();
        Image temp = profilePic.getImage();
        Image scaledTemp = temp.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);
        labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        setBackground(redOxide);

        labelName = new JLabel(myMatches.get(0).getName() + ", " + myMatches.get(0).getAge());
        labelName.setFont(fontBold);
        labelName.setForeground(oysterPink);

        labelBio = new JLabel(myMatches.get(0).getBio());
        labelBio.setFont(fontItal);
        labelBio.setForeground(oysterPink);

        labelTitle = new JLabel("Would you date them?");
        labelTitle.setFont(fontBold);
        labelTitle.setForeground(oysterPink);

        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelBio.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonNah = createSimpleButton(buttonNah, "nah");
        buttonNah.setBackground(oysterPink);
        buttonNah.setForeground(redOxide);
        buttonNah.setAlignmentY(Component.CENTER_ALIGNMENT);

        buttonYeah = createSimpleButton(buttonYeah, "yeah!");
        buttonYeah.setBackground(oysterPink);
        buttonYeah.setForeground(redOxide);
        //buttonYeah.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(buttonNah);
        Component myComponent = Box.createRigidArea(new Dimension(550, 0));
        myComponent.setBackground(redOxide);
        buttonPane.add(myComponent);
        buttonPane.add(buttonYeah);

        add(labelTitle);
        add(labelProfilePic);
        add(labelName);
        add(labelBio);
        add(buttonPane, BorderLayout.PAGE_END);

        buttonYeah.setAlignmentY(Component.CENTER_ALIGNMENT);

        panelProfile.add(labelTitle);
        panelProfile.add(labelProfilePic);
        panelProfile.add(labelName);
        panelProfile.add(labelBio);
        add(buttonNah);
        add(panelProfile);
        add(buttonYeah);
         */
    }

    private void displayCurrentPerson()
    {
        currentPerson = potentialMatches.get(currentPersonIndex);
        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            /* TODO remove comment
            ImageIcon profilePic = p.getProfilePic();
            Image temp = profilePic.getImage();
            Image scaledTemp = temp.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
            profilePic = new ImageIcon(scaledTemp);
            JLabel labelProfilePic = new JLabel(profilePic);
            labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

             */
        setBackground(redOxide);
        System.out.println("Displaying potential match, " + currentPerson.getName() + ", at index " + currentPersonIndex);

        labelName = new JLabel(currentPerson.getName() + ", " + currentPerson.getAge());
        labelName.setFont(fontBold);
        labelName.setForeground(oysterPink);

        labelBio = new JLabel(currentPerson.getBio());
        labelBio.setFont(fontItal);
        labelBio.setForeground(oysterPink);

        labelTitle = new JLabel("Would you date them?");
        labelTitle.setFont(fontBold);
        labelTitle.setForeground(oysterPink);

        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelBio.setAlignmentX(Component.CENTER_ALIGNMENT);



        buttonNah = createSimpleButton(buttonNah, "nah");
        buttonNah.setBackground(oysterPink);
        buttonNah.setForeground(redOxide);
        buttonNah.addActionListener(new ButtonNahActionListener());
        //buttonNah.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonYeah = createSimpleButton(buttonYeah, "yeah!");
        buttonYeah.setBackground(oysterPink);
        buttonYeah.setForeground(redOxide);
        buttonYeah.addActionListener(new ButtonYeahActionListener());
        //buttonYeah.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(buttonNah);
        Component myComponent = Box.createRigidArea(new Dimension(550, 0));
        myComponent.setBackground(redOxide);
        buttonPane.add(myComponent);
        buttonPane.add(buttonYeah);

        add(labelTitle);
            /* TODO remove comment
            add(labelProfilePic);

             */
        add(labelName);
        add(labelBio);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    public void displaySorryMessage()
    {
        setBackground(redOxide);

        removeAll();
        JLabel labelSorry = new JLabel("Sorry, you have no potential matches at the moment. Come back later!");
        labelSorry.setFont(fontItal);
        labelSorry.setForeground(oysterPink);

        add(labelSorry);
    }

    public void updateView(Person p) {
        //myPerson = p;
        removeAll();
        createView();
        revalidate();
        repaint();
    }

    private class ButtonYeahActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //acctServ.addMatch(myPerson, currentPerson); TODO uncomment this line
            System.out.println("Person " + myPerson.getName() + " and potential match " + currentPerson.getName() +
                    " successfully added to match table in database");
            currentPersonIndex++;
            revalidate();
            repaint();
            if (currentPersonIndex < potentialMatches.size())
            {
                displayCurrentPerson();
            }
            else
            {
                System.out.println("Sorry message printing");
                displaySorryMessage();
            }
        }
    }

    private class ButtonNahActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Person " + myPerson.getName() + " and potential match " + currentPerson.getName() +
                    " did NOT match (no addition to table)");
            currentPersonIndex++;
            revalidate();
            repaint();
            if (currentPersonIndex < potentialMatches.size())
            {
                displayCurrentPerson();
            }
            else
            {
                System.out.println("Sorry message printing");
                displaySorryMessage();
            }
        }
    }


}
