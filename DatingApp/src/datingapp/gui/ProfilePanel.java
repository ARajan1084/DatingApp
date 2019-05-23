package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanel extends JPanel {
    Person myPerson;
    private JLabel labelTitle, labelName, labelAge, labelEmail;
    private JTextArea textAreaBio;
    private JButton buttonEdit;

    public ProfilePanel (Person person) {
        super();
        myPerson = person;
        setPreferredSize(new Dimension(280, 800));
        createView();
    }

    public void createView () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ImageIcon profilePic = myPerson.getProfilePic();
        Image temp = profilePic.getImage();
        Image scaledTemp = temp.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);
        labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelName = new JLabel(myPerson.getName());
        labelAge = new JLabel(Integer.toString(myPerson.getAge()) + " years");
        labelEmail = new JLabel(myPerson.getEmail());
        labelTitle = new JLabel("My Profile");
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAge.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        textAreaBio = new JTextArea();
        textAreaBio.setMaximumSize(new Dimension(220, 250));
        textAreaBio.setText(myPerson.getBio());
        textAreaBio.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAreaBio.setLineWrap(true);
        textAreaBio.setWrapStyleWord(true);
        textAreaBio.setEditable(false);

        buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ButtonEditActionListener());
        buttonEdit.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(labelTitle);
        add(labelProfilePic);
        add(labelName);
        add(labelAge);
        add(labelEmail);
        add(textAreaBio);
        add(buttonEdit);
    }

    public void updateView(Person p) {
        myPerson = p;
        removeAll();
        createView();
        revalidate();
        repaint();
    }

    private class ButtonEditActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            new EditAccountWindow(myPerson, ProfilePanel.this);
        }
    }
}
