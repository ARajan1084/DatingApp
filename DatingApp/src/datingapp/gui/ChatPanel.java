package datingapp.gui;

import datingapp.program.Person;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class ChatPanel extends JPanel {
    private Person user;
    private JLabel labelName;
    private JTextArea textAreaChat;
    private JTextField fieldMessage;
    private JButton buttonSend;

    public ChatPanel (Person user) {
        super();
        this.user = user;
        setMaximumSize(new Dimension(450, 280));
        setPreferredSize(new Dimension(450, 280));
        createView();
    }

    private void createView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelName = new JLabel(user.getName());
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);

        textAreaChat = new JTextArea();
        textAreaChat.setPreferredSize(new Dimension(500, 270));
        textAreaChat.setMaximumSize(new Dimension(500, 270));
        textAreaChat.setEditable(false);
        textAreaChat.setAutoscrolls(true);

        JPanel panelMessage = new JPanel();
        panelMessage.setMaximumSize(new Dimension(500, 30));
        fieldMessage = new JTextField();
        fieldMessage.setPreferredSize(new Dimension (410, 20));
        buttonSend = new JButton("Send");
        panelMessage.add(fieldMessage);
        panelMessage.add(buttonSend);

        add(labelName);
        add(textAreaChat);
        add(panelMessage);
    }
}
