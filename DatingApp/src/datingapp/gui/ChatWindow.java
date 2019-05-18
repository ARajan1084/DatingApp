package datingapp.gui;
import datingapp.program.Chat;
import datingapp.program.Message;
import datingapp.program.Person;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatWindow extends JFrame {

    private JTextField textFieldMessage;
    private JTextArea textAreaWindow;
    private ObjectOutputStream ouput;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private JButton buttonSend;
    private Person me;
    private Person recipient;
    private Chat myChat;

    public ChatWindow (Person from, Person to) {
        super(to.getName());
        this.me = from;
        this.recipient = to;
        myChat = new Chat(me, recipient);
        createView();
        setResizable(false);
        setVisible(true);
    }

    public void createView() {
        textFieldMessage = new JTextField();
        textFieldMessage.setEditable(false);
        buttonSend = new JButton();
        buttonSend.addActionListener(new ButtonSendActionListener());
        add(textFieldMessage, BorderLayout.SOUTH);

        textAreaWindow = new JTextArea();
        add(new JScrollPane(textAreaWindow), BorderLayout.CENTER);
        setVisible(true);
    }


    private class ButtonSendActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            myChat.sendMessage(new Message(textFieldMessage.getText(), me, recipient));
            textFieldMessage.setText("");
        }
    }

    /**
    public void startRunning() {
        try {
            server = new ServerSocket(69, 500);
            while (true) {
                try {

                } catch (EOFException e) {
                    // showMessage("\n Server ended the connection! ");
                } finally {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */
}