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
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(300, 400));
        createView();
    }

    public void createView () {
        setPreferredSize(new Dimension(200, 400));

        ImageIcon profilePic = myPerson.getProfilePic();
        Image temp = profilePic.getImage();
        Image scaledTemp = temp.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);

        JLabel labelName = new JLabel(myPerson.getName());
        JLabel labelAge = new JLabel(Integer.toString(myPerson.getAge()) + " years");

        JTextArea textAreaBio = new JTextArea();
        textAreaBio.setMaximumSize(new Dimension(280, 300));
        textAreaBio.setEditable(false);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ButtonEditActionListender());

        add(labelProfilePic);
        add(labelName);
        add(labelAge);
        add(textAreaBio);
        add(buttonEdit);
    }

    private class ButtonEditActionListender implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
