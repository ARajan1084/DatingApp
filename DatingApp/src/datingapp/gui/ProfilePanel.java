package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static datingapp.gui.DashboardWindow.createSimpleButton;
import static javax.swing.Box.createVerticalGlue;

public class ProfilePanel extends JPanel {
    Person myPerson;
    private JLabel labelTitle, labelName, labelEmail;
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
        Image scaledTemp = temp.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);
        labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelName = new JLabel(myPerson.getName() + ", " + myPerson.getAge());
        labelEmail = new JLabel(myPerson.getEmail());
        labelTitle = new JLabel("my profile");
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
        buttonEdit.setFont(new Font("Helvetica", Font.ITALIC, 12));
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
