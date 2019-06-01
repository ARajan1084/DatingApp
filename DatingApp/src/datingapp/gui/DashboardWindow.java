package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.program.Person;
import datingapp.program.Tree;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * main GUI interface for user interactions. Users answer questions, communicate with each other, and view profiles
 * on this window.
 * The user's profile is displayed in the top left panel. The user's current chats are displayed in the top right panel.
 * The user's questions are displayed in the center panel. A logout button exists in the top of the center panel.
 * The user's matches are displayed in the bottom half of the center panel and message requests are displayed in the
 * bottom left panel
 *
 * @author Akanksha
 */
public class DashboardWindow extends JFrame {

    private Person feed;
    private ArrayList<Person> potentialMatches;
    private AccountService accountService;
    private final Dimension dashSize = new Dimension(1200, 769);

    public static final Font FONT_1 = new Font("Helvetica", Font.BOLD, 12);
    public static final Font FONT_2 = new Font("Helvetica", Font.ITALIC, 12);
    public static final Font FONT_3 = new Font("Helvetica", 0, 12);
    public static final Color NAVY_BLUE = new Color(36, 72, 104);
    private JPanel centerPanel;



    /**
     * constructs a window that serves as the user's dashboard
     * @param person person object to pull the profile panel from
     */
    public DashboardWindow(Person person)
        throws SQLException, ClassNotFoundException, IOException {
        accountService = new AccountService();
        feed = person;
        potentialMatches = accountService.fetchFeed(feed);

        createView();
        setSize(dashSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * helper method that primarily creates the GUI
     * @throws SQLException in case the connection to the DATABASE fails
     * @throws IOException in case of issues when converting the profile picture to a Blob in the DATABASE
     */
    private void createView() throws SQLException, IOException {
        BorderLayout layout = new BorderLayout();
        JPanel panelDash = new JPanel();
        panelDash.setLayout(layout);
        panelDash.setPreferredSize(dashSize);
        panelDash.add(centerPanel(), BorderLayout.CENTER);
        panelDash.add(westPanel(), BorderLayout.WEST);
        panelDash.add(eastPanel(), BorderLayout.EAST);

        //REMOVE centerPanel.add(centerCenterPanel());
        add(panelDash);
    }

    /**
     * helper method that constructs the west panel
     * @return completed west panel
     */
    private JPanel westPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel westPanel = new JPanel();
        westPanel.setLayout(layout);
        westPanel.setBackground(new Color(243, 232, 232));
        westPanel.setPreferredSize(new Dimension(250, 800));
        westPanel.add(new ProfilePanel(feed), BorderLayout.NORTH);
        return westPanel;
    }

    /**
     * helper method that constructs the center panel
     * @return completed center panel
     */
    private JPanel centerPanel() {
        BorderLayout layout = new BorderLayout();
        centerPanel = new JPanel();
        centerPanel.setLayout(layout);
        centerPanel.add(centerNorthPanel(), BorderLayout.NORTH);
        centerPanel.add(centerCenterPanel(), BorderLayout.CENTER);
        centerPanel.add(centerSouthPanel(), BorderLayout.SOUTH);
        centerPanel.setBackground(new Color	(222,237,242));
        return centerPanel;
    }

    /**
     * helper method that constructs the center north panel
     * @return completed center north panel
     */
    private JPanel centerNorthPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel centerNorthPanel = new JPanel();
        centerNorthPanel.setBackground(new Color(201, 249, 255));
        centerNorthPanel.setMaximumSize(new Dimension(450, 40));
        centerNorthPanel.setLayout(layout);

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(new Color(201, 249, 255));
        JButton buttonDeleteAccount = new JButton("Delete My Account");
        buttonDeleteAccount.addActionListener(new ButtonDeleteAccountActionListener());
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.addActionListener(new ButtonLogoutActionListener());
        panelButtons.add(buttonDeleteAccount);
        panelButtons.add(buttonLogout);
        centerNorthPanel.add(panelButtons, BorderLayout.EAST);
        return centerNorthPanel;
    }


    /**
     * helper method of centerPanel() that constructs the center center panel
     * @return completed center part of the center panel
     */
    private JPanel centerCenterPanel() {

        return new SwipePanel(feed, potentialMatches, accountService);
        /*
        catch (SQLException ex) {
            ex.printStackTrace();
            JPanel errorPanel = new JPanel();
            errorPanel.setMaximumSize(new Dimension(50, 450));
            errorPanel.add(new JLabel("Internal Error - please try again later"));
            return errorPanel;
        } catch (IOException ex) {
            ex.printStackTrace();
            JPanel errorPanel = new JPanel();
            errorPanel.setPreferredSize(new Dimension(50, 450));
            errorPanel.add(new JLabel("Internal Error - please try again later"));
            return errorPanel;
        }
        */

    }

    /**
     * Default SouthPanel for Center panel
     * @return returns a blank panel as a placeholder
     */
    public JPanel centerSouthPanel() {
        JPanel centerSouthPanel = new JPanel();
        centerSouthPanel.setBackground(Color.CYAN);
        centerSouthPanel.setMaximumSize(new Dimension(450, 280));
        centerSouthPanel.setPreferredSize(new Dimension(450, 280));
        return centerSouthPanel;
    }


    /**
     * helper method of centerPanel() that constructs the bottom half where matches are displayed
     * @return completed south pane of centerPanel()
     */
    public void updateCenterSouthPanel(Person user) {
        ChatPanel chatPanel = new ChatPanel(user);
        centerPanel.add(chatPanel, BorderLayout.SOUTH);
        centerPanel.repaint();
    }

    /**
     * helper method that constructs the east Panel
     * @return completed eastPanel
     * @throws SQLException in case the connection to the DATABASE fails
     * @throws IOException in case of issues when converting the profile picture to a Blob in the DATABASE
     */
    private JPanel eastPanel() throws SQLException, IOException {
        return new MatchesPane(feed, accountService.fetchMatches(feed), accountService, this);
    }

    /**
     * @author Achintya
     *
     * an ActionListener that tells what to do when the user clicks the Logout button
     */
    private class ButtonLogoutActionListener implements ActionListener {
        /**
         * closes the Dashboard window and opens up a new Login window
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginWindow();
        }
    }

    /**
     * @author Achintya
     *
     * an ActionListener that tells what to do when the user clicks the Delete Account button
     */
    private class ButtonDeleteAccountActionListener implements ActionListener {
        /**
         * opens another mini window that asks for a confirmation on whether the user really wants to delete their account
         * @param e the event in which the body of the code will be carried out
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            new DeleteAccountConfirmationWindow(DashboardWindow.this, feed, accountService);
        }
    }

    /**
     * @author Akanksha
     *
     * method that creates and initializes a JButton and sets it's font and colors to a simple color scheme
     * @param button the button that will be newed and returned
     * @param text the text that will be displayed on the JButton
     * @return an simple pretty looking button
     */
    public static JButton createSimpleButton(JButton button, String text) {
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
