package datingapp.gui;

import datingapp.backend.EditAccount;
import datingapp.exceptions.*;
import datingapp.program.Person;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class EditAccountWindow extends JFrame {
    private JLabel labelFirstName, labelLastName, labelProfilePic, labelAge, labelPassword, labelConfirmPassword,
            labelError, labelBio;
    private JTextField textFieldFirstName, textFieldLastName, textFieldAge;
    private JPasswordField textFieldPassword, textFieldConfirmPassword;
    private JTextArea textAreaUpdateBio;
    private JButton buttonDone, buttonCancel;
    private JFileChooser fileChooserProfilePic;

    private final Color backgroundColor = new Color(205, 228, 230);
    private Person myPerson;
    private ProfilePanel profilePanel;

    public EditAccountWindow(Person person, ProfilePanel profilePanel) {
        super("Edit Your Profile!");
        myPerson = person;
        this.profilePanel = profilePanel;
        createView();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(450, 690);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(backgroundColor);
        add(panel);

        JPanel panelName = new JPanel();
        panelName.setBackground(backgroundColor);
        panelName.setMaximumSize(new Dimension(350, 30));
        labelFirstName = new JLabel("First Name: ");
        labelLastName = new JLabel("Last Name: ");

        textFieldFirstName = new JTextField();
        textFieldFirstName.setPreferredSize(new Dimension(75, 20));
        textFieldLastName = new JTextField();
        textFieldLastName.setPreferredSize(new Dimension(75, 20));
        textFieldFirstName.setText(myPerson.getName().split(" ")[0]);
        textFieldLastName.setText(myPerson.getName().split(" ")[1]);
        panelName.add(labelFirstName);
        panelName.add(textFieldFirstName);
        panelName.add(labelLastName);
        panelName.add(textFieldLastName);

        JPanel panelAge = new JPanel();
        panelAge.setMaximumSize(new Dimension(400, 25));
        panelAge.setBackground(backgroundColor);
        labelAge = new JLabel("Age: ");
        textFieldAge = new JTextField();
        textFieldAge.setPreferredSize(new Dimension(40, 20));
        textFieldAge.setText(Integer.toString(myPerson.getAge()));
        panelAge.add(labelAge);
        panelAge.add(textFieldAge);

        JPanel panelPassword = new JPanel();
        panelPassword.setBackground(backgroundColor);
        panelPassword.setMaximumSize(new Dimension(300, 30));
        labelPassword = new JLabel("Password: ");
        textFieldPassword = new JPasswordField();
        textFieldPassword.setPreferredSize(new Dimension(175, 20));
        textFieldPassword.setText(myPerson.getPassword());
        panelPassword.add(labelPassword);
        panelPassword.add(textFieldPassword);

        JPanel panelConfirmPassword = new JPanel();
        panelConfirmPassword.setBackground(backgroundColor);
        panelConfirmPassword.setMaximumSize(new Dimension(310, 30));
        labelConfirmPassword = new JLabel("Confirm Password: ");
        textFieldConfirmPassword = new JPasswordField();
        textFieldConfirmPassword.setPreferredSize(new Dimension(175, 20));
        textFieldConfirmPassword.setText(myPerson.getPassword());
        panelConfirmPassword.add(labelConfirmPassword);
        panelConfirmPassword.add(textFieldConfirmPassword);

        JPanel panelProfilePic = new JPanel();
        panelProfilePic.setBackground(backgroundColor);
        panelProfilePic.setMaximumSize(new Dimension(450, 325));
        labelProfilePic = new JLabel("Choose a profile picture...");
        fileChooserProfilePic = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooserProfilePic.setPreferredSize(new Dimension(400, 300));
        panelProfilePic.add(labelProfilePic);
        panelProfilePic.add(fileChooserProfilePic);

        /*
        JPanel panelBio = new JPanel();
        panelBio.setLayout(new BoxLayout(panelBio, BoxLayout.Y_AXIS));
        panelBio.setMaximumSize(new Dimension(425, 300));
        panelBio.setBackground(backgroundColor);
        */
        labelBio = new JLabel("Update your bio here...");
        labelBio.setAlignmentX(Component.CENTER_ALIGNMENT);
        textAreaUpdateBio = new JTextArea();
        textAreaUpdateBio.setMaximumSize(new Dimension(425, 200));
        textAreaUpdateBio.setText(myPerson.getBio());
        textAreaUpdateBio.setBorder(null);
        textAreaUpdateBio.setEditable(true);
        textAreaUpdateBio.setLineWrap(true);
        textAreaUpdateBio.setWrapStyleWord(true);
        // panelBio.add(labelBio);
        // panelBio.add(textAreaUpdateBio);

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(backgroundColor);
        panelButtons.setMaximumSize(new Dimension(400, 30));
        buttonCancel = createSimpleButton("Cancel");
        buttonCancel.addActionListener(new ButtonCancelActionListener());
        buttonDone = createSimpleButton("Done");
        buttonDone.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDone.setLocation(new Point(90, 90));
        buttonDone.addActionListener(new ButtonDoneActionListener());
        panelButtons.add(buttonCancel);
        panelButtons.add(buttonDone);
        labelError = new JLabel("");
        labelError.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(panelName);
        panel.add(panelAge);
        panel.add(panelPassword);
        panel.add(panelConfirmPassword);
        panel.add(panelProfilePic);
        // panel.add(panelBio);
        panel.add(labelBio);
        panel.add(textAreaUpdateBio);
        panel.add(panelButtons);
        panel.add(labelError);
    }

    private JButton createSimpleButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Helvetica", Font.BOLD, 12));
        Border line = new LineBorder(Color.WHITE);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }

    private class ButtonCancelActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private class ButtonDoneActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new EditAccount().edit(myPerson, textFieldFirstName.getText(), textFieldLastName.getText(),
                        textFieldAge.getText(), new String(textFieldPassword.getPassword()),
                        new String(textFieldConfirmPassword.getPassword()), textAreaUpdateBio.getText(),
                        fileChooserProfilePic.getSelectedFile());
                profilePanel.updateView(myPerson);
                dispose();
            } catch (InvalidFirstNameException ex) {
                labelError.setText("Error: Invalid First Name.");
                textFieldFirstName.setText("");
            } catch (InvalidLastNameException ex) {
                labelError.setText("Error: Invalid Last Name.");
                textFieldLastName.setText("");
            } catch (InvalidProfilePictureException ex) {
                labelError.setText("Error: Invalid file type selected for profile picture.");
                fileChooserProfilePic.cancelSelection();
            } catch (InvalidAgeException ex) {
                labelError.setText("Error: Invalid Age. You must be at least 16 years old to participate.");
                textFieldAge.setText("");
            } catch (PasswordMismatchException ex) {
                labelError.setText("Error: The two given passwords do not match.");
                textFieldPassword.setText("");
                textFieldConfirmPassword.setText("");
            } catch (InvalidPasswordException ex) {
                labelError.setText("Error: Your password must be between 4 to 10 characters long.");
                textFieldPassword.setText("");
                textFieldConfirmPassword.setText("");
            } catch (BioWordLengthException ex) {
                textAreaUpdateBio.setText("");
                labelError.setText("Error: Your bio must be below " + ex.getMaxSize() + " characters long.");
            }
        }
    }
}
