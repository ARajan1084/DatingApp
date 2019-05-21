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

/**
 *
 */
public class EditBioWindow extends JFrame
{
    private JLabel labelDone;
    private JTextField updateBioField;
    private JButton buttonDone;
    private JFrame editBioWindow;
    private final Color backgroundColor = new Color(205,228,230);
    private Person myPerson;

    public EditBioWindow (Person person)
    {
        myPerson = person;

        createView();

        editBioWindow = this;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

    }

    private void createView()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        getContentPane().add(panel);

        labelDone = new JLabel("Done");

        updateBioField = new JTextField();
        updateBioField.setMaximumSize(new Dimension (175, 90));
        updateBioField.setBorder(null);

        /*
        buttonDone = new JButton("Done");
        buttonDone.addActionListener(new EditBioWindow.ButtonDoneActionListener());
        buttonDone.setAlignmentX(0);
         */

        buttonDone = createSimpleButton(buttonDone, "Done");
        buttonDone.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDone.setLocation(new Point(90, 90));
        buttonDone.addActionListener(new EditBioWindow.ButtonDoneActionListener());


        panel.add(updateBioField);
        panel.add(buttonDone);
    }

    private class ButtonDoneActionListener implements ActionListener {
        @Override
        //TODO set bio text box to input
        public void actionPerformed(ActionEvent e)
        {
            myPerson.setBio(updateBioField.getText());

            setVisible(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    private static JButton createSimpleButton(JButton button, String text) {
        button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Helvetica", Font.BOLD, 12));
        Border line = new LineBorder(Color.WHITE);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }
}
