package datingapp.gui;

import datingapp.program.Link;
import datingapp.program.Node;
import datingapp.program.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QuestionsPanel extends JPanel {
    private Person user;
    private Node myNode;
    private JLabel labelQuestion;

    public QuestionsPanel (Person p, Node node) {
        super();
        user = p;
        myNode = node;
        createView();
    }

    private void createView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        labelQuestion = new JLabel(myNode.getQuestion());
        add(labelQuestion);
        add (new AnswersPanel(myNode.getLinks()));
    }

    private class AnswersPanel extends JPanel {
        public AnswersPanel (ArrayList<Link> ans) {
            QuestionsPanel.this.setLayout(new GridLayout());
            for (int i = 0; i < ans.size(); i++) {
                JButton button = new JButton(ans.get(i).getAnswer());
                button.addActionListener(new MyActionListener(ans, i));
                this.add(button);
            }
        }

        private class MyActionListener implements ActionListener {
            private int i;
            private ArrayList<Link> myAns;
            public MyActionListener(ArrayList<Link> ans, int i) {
                this.i = i;
                myAns = ans;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                user.addToPath(i);
                if (myAns.get(i).getNode() != null) {
                    labelQuestion.setText(myAns.get(i).getNode().getQuestion());
                    AnswersPanel.this.removeAll();
                    new AnswersPanel(myAns.get(i).getNode().getLinks());
                }
            }
        }
    }
}
