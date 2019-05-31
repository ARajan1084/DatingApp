package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datingapp.gui.DashboardWindow.*;

/**
 * creates a ProfilePanel that displays a user's info for them to see
 * @author Akanksha and Achintya
 */
public class ProfilePanel extends JPanel {
    Person myPerson;
    private JLabel labelTitle, labelName, labelEmail;
    private JTextArea textAreaBio;
    private JButton buttonEdit;

    /**
     * constructs a profile panel
     * @param person the user
     */
    public ProfilePanel (Person person) {
        super();
        setBackground(Color.PINK);
        myPerson = person;
        setPreferredSize(new Dimension(280, 800));
        createView();
    }

    /**
     * creates the view and does all the formatting
     */
    public void createView () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ImageIcon profilePic = myPerson.getProfilePic();
        Image temp = profilePic.getImage();
        Image scaledTemp = temp.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);
        labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelName = new JLabel(myPerson.getName() + ", " + myPerson.getAge());
        labelName.setFont(new Font("Helvetica", Font.BOLD, 18));

        labelEmail = new JLabel(myPerson.getEmail());

        labelTitle = new JLabel("my profile");
        labelTitle.setFont(new Font("Helvetica", Font.ITALIC, 15));

        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        textAreaBio = new JTextArea();
        textAreaBio.setMaximumSize(new Dimension(220, 250));
        textAreaBio.setText(myPerson.getBio());
        textAreaBio.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAreaBio.setLineWrap(true);
        textAreaBio.setWrapStyleWord(true);
        textAreaBio.setEditable(false);

        buttonEdit = createSimpleButton(buttonEdit, "update profile");
        buttonEdit.setFont(new Font("Helvetica", Font.ITALIC, 15));
        buttonEdit.addActionListener(new ButtonEditActionListener());
        buttonEdit.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 15)));
        add(labelTitle);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(labelProfilePic);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(labelName);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(labelEmail);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(textAreaBio);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(buttonEdit);
    }

    /**
     * updates the view for a user
     * @param p the user
     */
    public void updateView(Person p) {
        myPerson = p;
        removeAll();
        createView();
        revalidate();
        repaint();
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Done button
     */
    private class ButtonEditActionListener implements ActionListener {
        /**
         * edits and changes the user's info
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new EditAccountWindow(myPerson, ProfilePanel.this);
        }
    }
}
