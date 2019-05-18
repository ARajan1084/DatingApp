package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanel extends JPanel {
    Person myPerson;

    public ProfilePanel (Person person) {
        super();
        myPerson = person;
        setPreferredSize(new Dimension(280, 400));
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

        JLabel labelName = new JLabel(myPerson.getName());
        JLabel labelAge = new JLabel(Integer.toString(myPerson.getAge()) + " years");
        JLabel labelEmail = new JLabel(myPerson.getEmail());
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelAge.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelEmail.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea textAreaBio = new JTextArea();
        textAreaBio.setMaximumSize(new Dimension(220, 250));
        textAreaBio.setText(myPerson.getBio());
        textAreaBio.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAreaBio.setLineWrap(true);
        textAreaBio.setWrapStyleWord(true);
        textAreaBio.setEditable(false);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ButtonEditActionListender());
        buttonEdit.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(labelProfilePic);
        add(labelName);
        add(labelAge);
        add(labelEmail);
        add(textAreaBio);
        add(buttonEdit);
    }

    private class ButtonEditActionListender implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
