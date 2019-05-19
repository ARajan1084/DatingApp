package datingapp.gui;

import datingapp.program.Chat;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatsPanel extends JPanel {
    private Person myPerson;
    private ArrayList<Chat> myChats;

    public ChatsPanel (Person me, ArrayList<Chat> chats) {
        super();
        myChats = chats;
        createView();
    }

    private void createView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Chats"));
        for (Chat chat: myChats) {
            Person other;
            if (chat.getPerson1().equals(myPerson)) {
                other = chat.getPerson2();
            } else {
                other = chat.getPerson1();
            }
            add(new ChatPanel(other));
        }
    }

    private class ChatPanel extends JPanel {
        private ChatPanel (Person p) {
            super();
            ImageIcon profilePic = myPerson.getProfilePic();
            Image temp = profilePic.getImage();
            Image scaledTemp = temp.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
            profilePic = new ImageIcon(scaledTemp);
            JLabel labelProfilePic = new JLabel(profilePic);
            add(labelProfilePic);
            add(new JLabel(p.getName()));
        }
    }
}
