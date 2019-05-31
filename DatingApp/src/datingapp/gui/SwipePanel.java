package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import static datingapp.gui.DashboardWindow.*;

/**
 * creates and formats the SwipePanel where the user swipes other potential matches (makes it functional too, of course)
 * @author Akanksha
 */
public class SwipePanel extends JPanel
{
    Person user, currentPerson;
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

    /**
     * constructs AND FORMATS the SwipePanel
     * @param feed the user
     * @param potentialMatches the list of the user's potential matches
     * @param acctServ the AccountService
     */
    public SwipePanel (Person feed, ArrayList<Person> potentialMatches, AccountService acctServ) {
        super();
        this.user = feed;
        this.potentialMatches = potentialMatches;
        currentPersonIndex = 0;
        this.acctServ = acctServ;
        setMaximumSize(new Dimension(280, 500));
        //myMatches = user;
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

    /**
     * does absolutely nothing cause all the formatting is being done in the window's constructor, but I'm too attached
     * to this method to delete it
     */
    public void createView ()
    {
        // B)
    }

    /**
     * displays the current potential match for the user to
     */
    private void displayCurrentPerson()
    {
        currentPerson = potentialMatches.get(currentPersonIndex);
        removeAll();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

        ImageIcon profilePic = currentPerson.getProfilePic();
        Image temp = profilePic.getImage();
        Image scaledTemp = temp.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);
        labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonNah = createSimpleButton(buttonNah, "nah");
        buttonNah.setBackground(oysterPink);
        buttonNah.setForeground(redOxide);
        buttonNah.addActionListener(new ButtonNahActionListener());
        buttonNah.setForeground(Color.WHITE);
        //buttonNah.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonYeah = createSimpleButton(buttonYeah, "yeah!");
        buttonYeah.setBackground(oysterPink);
        buttonYeah.setForeground(redOxide);
        buttonYeah.addActionListener(new ButtonYeahActionListener());
        buttonYeah.setForeground(Color.GREEN);
        //buttonYeah.setAlignmentX(Component.RIGHT_ALIGNMENT);

        BorderLayout layout = new BorderLayout();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setMaximumSize(new Dimension(400, 40));
        buttonPanel.setBackground(redOxide);
        buttonPanel.setLayout(layout);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(buttonNah, BorderLayout.WEST);
        // Component myComponent = Box.createRigidArea(new Dimension(550, 0));
        // myComponent.setBackground(redOxide);
        // buttonPanel.add(myComponent);
        buttonPanel.add(buttonYeah, BorderLayout.EAST);

        add(labelTitle);
        add(labelProfilePic);
        add(labelName);
        add(labelBio);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    /**
     * mini method that displays the sorry message for when a user has no matches
     */
    public void displaySorryMessage()
    {
        setBackground(redOxide);
        System.out.println("No more matches for person "+ user);
        removeAll();
        JLabel labelSorry = new JLabel("Sorry, you have no potential matches at the moment. Come back later!");
        labelSorry.setFont(fontItal);
        labelSorry.setForeground(oysterPink);

        add(labelSorry);
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Yeah! button
     */
    private class ButtonYeahActionListener implements ActionListener {
        /**
         * says yes to this person and saves that in the DATABASE
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                acctServ.addMatch(user, currentPerson);
                System.out.println("Person " + user.getName() + " and potential match " + currentPerson.getName() +
                        " successfully added to match table in database");
                currentPersonIndex++;
                revalidate();
                repaint();
                if (currentPersonIndex < potentialMatches.size()) {
                    displayCurrentPerson();
                } else {
                    displaySorryMessage();
                }
            } catch (SQLException ex) {
                System.out.println("Internal Error");
                ex.printStackTrace();
            }
        }
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Nah button
     */
    private class ButtonNahActionListener implements ActionListener {
        /**
         * says no to this person and saves that in the DATABASE
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Person " + user.getName() + " and potential match " + currentPerson.getName() +
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
                displaySorryMessage();
            }
        }
    }


}