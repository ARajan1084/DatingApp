package datingapp.gui;

import datingapp.backend.CreateAccount;
import datingapp.exceptions.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static datingapp.gui.DashboardWindow.FONT_1;

/**
 * Represents a graphical interface for creating an account. Users can enter information such as their name, age, and
 * sexuality. Users can also upload a profile pic and set a password.
 *
 * @author Achintya and Akanksha
 * @version 05/20/19
 */
public class CreateAccountWindow extends JFrame {
    private JLabel labelFirstName, labelLastName, labelEmail, labelPassword, labelConfirmPassword, labelGender,
            labelAge, labelSexuality, labelStatus, labelBio, labelError, labelProfilePic;
    private JTextField fieldFirstName, fieldLastName, fieldEmail, fieldAge;
    private JTextArea textAreaBio;
    private JPasswordField fieldPassword, fieldConfirmPassword;
    private JComboBox comboBoxGender, comboBoxSexuality;
    private JCheckBox checkBoxSingle;
    private JButton buttonCreate, buttonCancel;
    private JFrame loginWindow;
    private JFileChooser fileChooserProfilePic;
    private final Color backgroundColor = Color.PINK;

    /**
     * constructs a window where users can create an account
     * @param loginWindow reference to the LoginWindow
     */
    public CreateAccountWindow (JFrame loginWindow) {
        createView();

        this.loginWindow = loginWindow;
        setTitle("Welcome to TinderButBetter! - Create an Account");
        setSize(500, 770);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    /**
     * creates a view for the Create Account Window
     * sets the background color and fonts and dimensions as specified and adds all the JButtons and text fields and
     * checkboxes that are necessary for this window in order for the user to be able to create an account
     */
    private void createView() {
        JPanel panel = new JPanel();
        panel.setBackground(backgroundColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(500, 790));
        getContentPane().add(panel);

        JPanel panelName = new JPanel();
        panelName.setBackground(backgroundColor);
        panelName.setMaximumSize(new Dimension(350, 30));
        labelFirstName = new JLabel("First Name: ");
        labelFirstName.setFont(FONT_1);
        labelLastName = new JLabel("Last Name: ");
        fieldFirstName = new JTextField();
        fieldFirstName.setPreferredSize(new Dimension(75, 20));
        fieldFirstName.setBorder(null);
        fieldLastName = new JTextField();
        fieldLastName.setPreferredSize(new Dimension(75, 20));
        fieldLastName.setBorder(null);
        panelName.add(labelFirstName);
        panelName.add(fieldFirstName);
        panelName.add(labelLastName);
        panelName.add(fieldLastName);

        JPanel panelProfilePic = new JPanel();
        panelProfilePic.setBackground(backgroundColor);
        panelProfilePic.setMaximumSize(new Dimension(450, 360));
        labelProfilePic = new JLabel("Choose a profile picture...");
        fileChooserProfilePic = new JFileChooser(FileSystemView.getFileSystemView());
        fileChooserProfilePic.setPreferredSize(new Dimension(400, 300));
        panelProfilePic.add(labelProfilePic);
        panelProfilePic.add(fileChooserProfilePic);

        JPanel panelEmail = new JPanel();
        panelEmail.setBackground(backgroundColor);
        panelEmail.setMaximumSize(new Dimension(300, 30));
        labelEmail = new JLabel("Email Address: ");
        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension(175, 20));
        fieldEmail.setBorder(null);
        panelEmail.add(labelEmail);
        panelEmail.add(fieldEmail);

        JPanel panelPassword = new JPanel();
        panelPassword.setBackground(backgroundColor);
        panelPassword.setMaximumSize(new Dimension(300, 30));
        labelPassword = new JLabel("Password: ");
        fieldPassword = new JPasswordField();
        fieldPassword.setPreferredSize(new Dimension(175, 20));
        fieldPassword.setBorder(null);
        panelPassword.add(labelPassword);
        panelPassword.add(fieldPassword);

        JPanel panelConfirmPassword = new JPanel();
        panelConfirmPassword.setBackground(backgroundColor);
        panelConfirmPassword.setMaximumSize(new Dimension(310, 30));
        labelConfirmPassword = new JLabel("Confirm Password: ");
        fieldConfirmPassword = new JPasswordField();
        fieldConfirmPassword.setPreferredSize(new Dimension(175, 20));
        fieldConfirmPassword.setBorder(null);
        panelConfirmPassword.add(labelConfirmPassword);
        panelConfirmPassword.add(fieldConfirmPassword);

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(backgroundColor);
        panelInfo.setMaximumSize(new Dimension (480, 30));
        // age
        labelAge = new JLabel("Age: ");
        fieldAge = new JTextField();
        fieldAge.setPreferredSize(new Dimension(40, 20));
        // gender
        labelGender = new JLabel("Gender:");
        comboBoxGender = new JComboBox();
        comboBoxGender.addItem("Male");
        comboBoxGender.addItem("Female");
        comboBoxGender.addItem("Other");
        // status
        labelStatus = new JLabel("Long term relationship? ");
        checkBoxSingle = new JCheckBox("");
        checkBoxSingle.setBackground(backgroundColor);
        panelInfo.add(labelAge);
        panelInfo.add(fieldAge);
        panelInfo.add(labelGender);
        panelInfo.add(comboBoxGender);
        panelInfo.add(labelStatus);
        panelInfo.add(checkBoxSingle);

        JPanel panelSexuality = new JPanel();
        panelSexuality.setBackground(backgroundColor);
        panelSexuality.setMaximumSize(new Dimension(300, 50));
        labelSexuality = new JLabel("Sexual Orientation: ");
        comboBoxSexuality = new JComboBox<String>();
        comboBoxSexuality.addItem("Heterosexual");
        comboBoxSexuality.addItem("Homosexual");
        comboBoxSexuality.addItem("Bisexual");
        panelSexuality.add(labelSexuality);
        panelSexuality.add(comboBoxSexuality);

        textAreaBio = new JTextArea();
        textAreaBio.setMaximumSize(new Dimension(300, 300));
        textAreaBio.setLineWrap(true);
        textAreaBio.setWrapStyleWord(true);
        textAreaBio.setText("Type your bio here...");

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(backgroundColor);
        panelButtons.setMaximumSize(new Dimension(200, 50));
        buttonCreate = new JButton("Create!");
        buttonCreate.addActionListener(new buttonCreateActionListener());
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new buttonCancelActionListener());
        buttonCreate.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelButtons.add(buttonCreate);
        panelButtons.add(buttonCancel);

        panel.add(panelName);
        panel.add(panelEmail);
        panel.add(panelProfilePic);
        panel.add(panelPassword);
        panel.add(panelConfirmPassword);
        panel.add(panelInfo);
        panel.add(panelSexuality);
        panel.add(textAreaBio);
        panel.add(panelButtons);
        labelError = new JLabel("");
        labelError.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelError);
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Create Account button
     */
    private class buttonCreateActionListener implements ActionListener {
        /**
         * creates a new account for the user who input the data
         * does so by creating a new Person and setting the data to whatever the user entered in
         * checks for and accounts for the various possible errors in entering the data
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                File file;
                if (fileChooserProfilePic.getSelectedFile() == null) {
                    file = null;
                } else {
                    file = new File(fileChooserProfilePic.getSelectedFile().getAbsolutePath());
                }
                new CreateAccount().isValid(fieldFirstName.getText(), fieldLastName.getText(), fieldEmail.getText(),
                        new String(fieldPassword.getPassword()), new String(fieldConfirmPassword.getPassword()),
                        fieldAge.getText(), comboBoxGender.getSelectedItem().toString().toLowerCase(),
                        comboBoxSexuality.getSelectedItem().toString().toLowerCase(), checkBoxSingle.isSelected(),
                        textAreaBio.getText(), file);
                dispose();
                loginWindow.setVisible(true);
            } catch (InvalidFirstNameException ex) {
                labelError.setText("Error: Invalid First Name.");
                fieldFirstName.setText("");
            } catch (InvalidLastNameException ex) {
                labelError.setText("Error: Invalid Last Name.");
                fieldLastName.setText("");
            } catch (InvalidProfilePictureException ex) {
                labelError.setText("Error: Invalid file type selected for profile picture.");
                fileChooserProfilePic.cancelSelection();
            } catch (InvalidAgeException ex) {
                labelError.setText("Error: Invalid Age. You must be at least 16 years old to participate.");
                fieldAge.setText("");
            } catch (InvalidEmailAddressException ex) {
                labelError.setText("Please enter a valid email address (ex: mike.smith@gmail.com)");
                fieldEmail.setText("");
            } catch (PasswordMismatchException ex) {
                labelError.setText("Error: The two given passwords do not match.");
                fieldPassword.setText("");
                fieldConfirmPassword.setText("");
            } catch (InvalidPasswordException ex) {
                labelError.setText("Error: Your password must be between 4 to 10 characters long.");
                fieldPassword.setText("");
                fieldConfirmPassword.setText("");
            } catch (BioWordLengthException ex) {
                textAreaBio.setText("");
                labelError.setText("Error: Your bio must be below " + ex.getMaxSize() + " characters long.");
            } catch (IOException ex) {
                ex.printStackTrace();
                labelError.setText("Internal Error: please try again later.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                labelError.setText("Internal Error: please try again later.");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                labelError.setText("Internal Error: please try again later.");
            }
        }
    }

    /**
     * an ActionListener that tells what to do when the user clicks the Cancel button
     */
    private class buttonCancelActionListener implements ActionListener {
        /**
         * closes the window
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            loginWindow.setVisible(true);
        }
    }
}
