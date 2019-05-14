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
        createView();
    }

    public void createView () {
        setSize(new Dimension(200, 400));
        getGraphics().drawImage(myPerson.getProfilePic(), 0, 0, this);
        add(new JLabel(myPerson.getName()));
        add(new JLabel(Integer.toString(myPerson.getAge())));
        add(new JLabel(myPerson.getBio()));
        JButton buttonEdit = new JButton("Edit");
        buttonEdit.addActionListener(new ButtonEditActionListender());
    }

    private class ButtonEditActionListender implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
