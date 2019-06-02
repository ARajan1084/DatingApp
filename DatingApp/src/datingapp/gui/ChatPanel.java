package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.exceptions.MessageLengthException;
import datingapp.program.Message;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * constructs a panel for the messaging are of this application
 */
public class ChatPanel extends JPanel {
    private Person match, user;
    private AccountService accountService;
    private JLabel labelName;
    private JTextArea textAreaChat;
    private JScrollPane scrollPaneChat;
    private JTextField fieldMessage;
    private JButton buttonSend;

    /**
     * constructs a ChatPanel
     * @param user the match
     */
    public ChatPanel (AccountService accountService, Person user, Person match) {
        super();
        this.accountService = accountService;
        this.user = user;
        this.match = match;
        setMaximumSize(new Dimension(450, 280));
        setPreferredSize(new Dimension(450, 280));
        createView();
        updateMessages();
    }

    private void updateMessages() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        ArrayList<Message> messages = accountService.fetchMessages(user, match);
                        Thread.sleep(500);
                        textAreaChat.setText("");
                        for (Message message : messages) {
                            textAreaChat.append(message.getSender().getName() + ": " + message.getMessage() + '\n');
                        }
                        JScrollBar scrollBar = scrollPaneChat.getVerticalScrollBar();
                        if (scrollBar.getValue() >= scrollBar.getMaximum() - 5) {
                            scrollPaneChat.getVerticalScrollBar().setValue(
                                    scrollPaneChat.getVerticalScrollBar().getMaximum());
                        }
                        scrollPaneChat.revalidate();
                        repaint();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        textAreaChat.setText("Error with displaying messages. Please try again later.");
                        removeAll();
                        revalidate();
                    } catch (MessageLengthException ex) {
                        ex.printStackTrace();
                        textAreaChat.setText("Error with displaying messages. Please try again later.");
                        removeAll();
                        revalidate();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        textAreaChat.setText("Error with displaying messages. Please try again later.");
                        removeAll();
                        revalidate();
                    }
                }
            }
        }).start();
    }

    /**
     * creates the view and formats the window and adds buttons
     */
    private void createView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelName = new JLabel(match.getName());
        labelName.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*
        textAreaChat = new JTextArea();
        textAreaChat.setPreferredSize(new Dimension(500, 270));
        textAreaChat.setMaximumSize(new Dimension(500, 270));
        textAreaChat.setEditable(false);
        textAreaChat.setAutoscrolls(true);
        */

        textAreaChat = new JTextArea();
        textAreaChat.setEditable(false);
        textAreaChat.setAutoscrolls(true);
        scrollPaneChat = new JScrollPane(textAreaChat);
        scrollPaneChat.setPreferredSize(new Dimension(500, 270));
        scrollPaneChat.setMaximumSize(new Dimension(500, 270));

        JPanel panelMessage = new JPanel();
        panelMessage.setMaximumSize(new Dimension(500, 30));
        fieldMessage = new JTextField();
        fieldMessage.setPreferredSize(new Dimension (410, 20));
        fieldMessage.addActionListener(new FieldMessageActionListener());
        buttonSend = new JButton("Send");
        buttonSend.addActionListener(new ButtonSendActionListener());
        panelMessage.add(fieldMessage);
        panelMessage.add(buttonSend);

        add(labelName);
        //add(textAreaChat);
        add(scrollPaneChat);
        add(panelMessage);
    }

    private class ButtonSendActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (fieldMessage.getText().length() > 0) {
                try {
                    Message message = new Message(fieldMessage.getText(), user, match);
                    accountService.processMessage(message);
                    fieldMessage.setText("");
                } catch (MessageLengthException ex) {
                    fieldMessage.setText("Message too long!");
                } catch (SQLException ex) {
                    fieldMessage.setText("Failed to send message. Try again later.");
                    ex.printStackTrace();
                }
            }
        }
    }

    private class FieldMessageActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buttonSend.doClick();
        }
    }
}
