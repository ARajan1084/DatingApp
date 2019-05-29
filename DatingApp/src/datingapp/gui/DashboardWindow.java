package datingapp.gui;

import datingapp.backend.AccountService;
import datingapp.program.Chat;
import datingapp.program.ConstantKey;
import datingapp.program.Person;
import datingapp.program.Tree;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
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
 * @version 05/09
 */
public class DashboardWindow extends JFrame {

    private Person feed;
    private ArrayList<Chat> myChats;
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
     * @param chats list of chats to pull the chats panel from
     */
    public DashboardWindow(Person person, ArrayList<Chat> chats)
        throws SQLException, ClassNotFoundException, IOException {
        accountService = new AccountService();
        Tree globalTree = accountService.getGlobalTree();
        feed = person;
        myChats = chats;
        potentialMatches = globalTree.getMatches(feed);
        /*
        Tree testTree = new Tree();
        Person p1 = new Person("Tommy Hilfiger", 32, ConstantKey.MALE, ConstantKey.BI, "th@gmail.com", "flagsand",
                true, "t.h.", null);
        Person p2 = new Person("Vera Wang", 31, ConstantKey.FEMALE, ConstantKey.STRAIGHT, "vera@gmail.com", "notbradley",
                true, "v.w.", null);
        Person p3 = new Person("Ralph Lauren", 35, ConstantKey.MALE, ConstantKey.GAY, "th@gmail.com", "polo",
                true, "horsey", null);
        Person p4 = new Person("Michael Kors", 34, ConstantKey.MALE, ConstantKey.STRAIGHT, "michaellllll@gmail.com", "kors",
                true, "mmmmk", null);

        testTree.addPerson(p1);
        testTree.addPerson(p2);
        testTree.addPerson(p3);
        testTree.addPerson(p4);

        potentialMatches = testTree.getMatches(feed);
        */

        createView();
        setSize(dashSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * helper method that primarily creates the GUI
     */
    private void createView() {
        BorderLayout layout = new BorderLayout();
        JPanel panelDash = new JPanel();
        panelDash.setLayout(layout);
        panelDash.setPreferredSize(dashSize);
        panelDash.add(centerPanel(), BorderLayout.CENTER);
        panelDash.add(westPanel(), BorderLayout.WEST);
        panelDash.add(eastPanel(), BorderLayout.EAST);
        panelDash.setBackground(Color.PINK);

        //TODO REMOVE centerPanel.add(centerCenterPanel());
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
        //TODO REMOVE centerPanel.add(centerCenterPanel(), BorderLayout.CENTER);
        centerPanel.add(centerSouthPanel(), BorderLayout.SOUTH);
        centerPanel.setBackground(new Color	(222,237,242));

        centerPanel.setLayout(layout);
        centerPanel.setPreferredSize(new Dimension(500, 30));
        //TODO TEMOVE centerPanel.add(new SwipePanel(feed, potentialMatches, accountService), BorderLayout.NORTH);
        return centerPanel;
    }

    private JPanel centerNorthPanel() {
        BorderLayout layout = new BorderLayout();
        JPanel centerNorthPanel = new JPanel();
        centerNorthPanel.setMaximumSize(new Dimension(450, 300));
        centerNorthPanel.setLayout(layout);
        centerNorthPanel.setPreferredSize(new Dimension(500, 300));
        centerNorthPanel.add(new SwipePanel(feed, potentialMatches, accountService), BorderLayout.NORTH);
        JPanel panelButtons = new JPanel();
        JButton buttonDeleteAccount = new JButton("Delete My Account");
        buttonDeleteAccount.addActionListener(new ButtonDeleteAccountActionListener());
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.addActionListener(new ButtonLogoutActionListener());
        panelButtons.add(buttonDeleteAccount);
        panelButtons.add(buttonLogout);
        centerNorthPanel.add(panelButtons, BorderLayout.EAST);
        return centerNorthPanel;
    }

    /*
    TODO REMOVE THIS COMPLETELY IT IS GARBAGE

    private JPanel centerCenterPanel() {

        try {
            return new SwipePanel(accountService.fetchFeed(feed));
        } catch (SQLException ex) {
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

    }

     */

    /**
     * helper method of centerPanel() that constructs the bottom half where matches are displayed
     * @return completed south pane of centerPanel()
     */
    private JPanel centerSouthPanel() {
        /*
        JPanel southPane = new MatchesPane(myMatches);
        return southPane;
         */
        BorderLayout layout = new BorderLayout();
        JPanel centerSouthPanel = new JPanel();
        centerSouthPanel.setLayout(layout);
        centerSouthPanel.setPreferredSize(new Dimension(500, 30));

        Person p = new Person("Tommy Hilfiger", 32,"M", "", "th@gmail.com", "",
                true, "t.h.", null);
        Chat c = new Chat(feed, feed);
        ArrayList<Chat> list = new ArrayList<Chat>();
        list.add(c);

        //centerSouthPanel.add(new ChatsPanel(feed, list), BorderLayout.SOUTH);
        return centerSouthPanel;
    }

    /**
     * helper method that constructs the east Panel
     * @return completed
     */
    private JPanel eastPanel() {
        /*
        BorderLayout layout = new BorderLayout();
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(layout);
        eastPanel.setPreferredSize(new Dimension(250, 800));
        eastPanel.add(new ChatsPanel(feed, myChats), BorderLayout.NORTH);
        return eastPanel;
         */
        JPanel eastPanel = new MatchesPane(accountService.fetchMatches(feed));

        //TODO REMOVE BLOCK BELOW
        /*
        ArrayList<Person> myMatches = new ArrayList<Person>();
        Person m1 = new Person("Tommy Hilfiger", 32, ConstantKey.MALE, ConstantKey.BI, "th@gmail.com", "flagsand",
                true, "t.h.", null);
        Person m2 = new Person("Michael Kors", 34, ConstantKey.MALE, ConstantKey.STRAIGHT, "michaellllll@gmail.com", "kors",
                true, "mmmmk", null);
        myMatches.add(m1);
        myMatches.add(m2);
        ////////////////////
        */

        JPanel eastPanel = new MatchesPane(myMatches);
        return eastPanel;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException
    {
        ArrayList<Chat> chats = new ArrayList<Chat>();
        ArrayList<Person> matches = new ArrayList<Person>();
        Tree myTree = new Tree();
        Person p = new Person("Alexis Rose", 30,ConstantKey.FEMALE, ConstantKey.STRAIGHT, "everybodysgotahorse@gmail.com", "laalalalalalala",
                true, "hide your diamonds, hide ur exes. I'm a little bit Alexis ;)", new ImageIcon(ImageIO.read(new File("/home/akanksha/Pictures/alexis4.jpg"))));
        /*
        Person p1 = new Person("Ted Mullens", 30,ConstantKey.MALE, ConstantKey.STRAIGHT, "fifa@gmail.com", "creekdog",
                true, "dogsdogsdogscatsdogs", new ImageIcon(ImageIO.read(new File("/home/akanksha/Pictures/ted.png"))));
        Person p2 = new Person("Mutt Skits", 31,ConstantKey.MALE, ConstantKey.BI, "barns@gmail.com", "lumber",
                true, "barns", new ImageIcon(ImageIO.read(new File("/home/akanksha/Pictures/mutt.png"))));

         */
        new DashboardWindow(p, chats);
    }

    /**
     * Action Listener for Logout Button
     */
    private class ButtonLogoutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new LoginWindow();
        }
    }

    /**
     * Akanksha
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

    private class ButtonDeleteAccountActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DeleteAccountConfirmationWindow(DashboardWindow.this, feed, accountService);
        }
    }
}
