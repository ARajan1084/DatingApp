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

import static datingapp.gui.DashboardWindow.*;

public class SwipePanel extends JPanel
{
    Person myPerson;
    private JLabel labelTitle, labelName, labelBio;
    private JTextArea textAreaBio;
    private JButton buttonYeah, buttonNah;
    public final Color redOxide = new Color(77, 21, 18);
    public final Color oysterPink = new Color(210, 176, 174);
    private final Font fontBold = new Font("Helvetica", Font.BOLD, 15);
    private final Font fontItal = new Font("Helvetica", Font.ITALIC, 15);
    private final Font fontNone = new Font("Helvetica", 0, 15);



    public SwipePanel (Person person) {
        super();
        myPerson = person;
        setPreferredSize(new Dimension(280, 800));
        createView();
    }

    public void createView () {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        ImageIcon profilePic = myPerson.getProfilePic();
        Image temp = profilePic.getImage();
        Image scaledTemp = temp.getScaledInstance(250, 250,  java.awt.Image.SCALE_SMOOTH);
        profilePic = new ImageIcon(scaledTemp);
        JLabel labelProfilePic = new JLabel(profilePic);
        labelProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        setBackground(redOxide);


        labelName = new JLabel(myPerson.getName() + ", " + myPerson.getAge());
        labelName.setFont(fontBold);
        labelName.setForeground(oysterPink);

        labelBio = new JLabel(myPerson.getBio());
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
        //buttonNah.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonYeah = createSimpleButton(buttonYeah, "yeah!");
        buttonYeah.setBackground(oysterPink);
        buttonYeah.setForeground(redOxide);
        //buttonYeah.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(buttonNah);
        buttonPane.add(Box.createRigidArea(new Dimension(550, 0)));
        buttonPane.add(buttonYeah);

        add(labelTitle);
        add(labelProfilePic);
        add(labelName);
        add(labelBio);
        add(buttonPane, BorderLayout.PAGE_END);

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
            //new EditAccountWindow(myPerson, ProfilePanel.this);
        }
    }

}
