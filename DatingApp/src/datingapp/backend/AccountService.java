package datingapp.backend;
import datingapp.exceptions.AccountNotFoundException;
import datingapp.program.Person;
import datingapp.program.Tree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Represents the layer between the backend and the database. This class is responsible for all communication between
 * the database and client classes.
 *
 * The database stores a table of all users and their information, a table of matches for each user, and a table of
 * compatible users (feed) for each user
 *
 * @author Achintya
 * @version 05/25/19
 */
public class AccountService {
    private Connection con;

    /**
     * establishes a connection "con" to the database
     * @throws ClassNotFoundException in case casting the device driver fails
     * @throws SQLException in case the connection to the database fails
     */
    public AccountService() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datingapp","root","");
    }

    /**
     * adds a user to the database
     * @param p Person object to be added
     * @throws SQLException in case the query is incorrect
     * @throws IOException in case of issues when converting the profile picture to a Blob
     */
    public void addUser (Person p) throws SQLException, IOException {
        PreparedStatement stmt = con.prepareStatement("insert into person values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, p.getEmail());
        stmt.setString(2, p.getPassword());
        stmt.setString(3, p.getName());
        stmt.setInt(4, p.getAge());
        stmt.setString(5, p.getGender());
        stmt.setString(6, p.getSexuality());
        stmt.setString(7, p.getBio());
        stmt.setBoolean(8, p.getStatus());

        BufferedImage pfp = (BufferedImage) p.getProfilePic().getImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(pfp, "jpg", bos);
        byte[] pfpData = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(pfpData);

        stmt.setBlob(9, bis, pfpData.length);
        stmt.executeUpdate();
        con.close();
    }

    /**
     * edits an existing user in the database
     * @param p updated Person object
     */
    public void editUser(Person p) {

    }

    /**
     * backend method used by LoginWindow. Determines whether a login attempt (given email and password) is valid. If it
     * is, the method returns the Person object associated with the given email address
     * @param emailAdress given email address
     * @param password given password
     * @return returns Person object associated with the given email address, or throws an AccountNotFoundException if
     * such a person does not exist.
     * @throws SQLException in case errors occur when executing a query or processing a result set
     * @throws IOException in case errors occur when reading a profile picture from the blob
     * @throws AccountNotFoundException in case login attempt is invalid
     */
    public Person isValid(String emailAdress, String password) throws SQLException, IOException, AccountNotFoundException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM person WHERE email = ? AND password = ?");
        stmt.setString(1, emailAdress);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            throw new AccountNotFoundException();
        }
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String sexuality = rs.getString("sexuality");
        String email = rs.getString("email");
        String bio = rs.getString("bio");
        boolean status = rs.getBoolean("status");
        ImageIcon pfp = getImage(rs.getBlob("profile_picture"));
        Person person = new Person(name, age, gender, sexuality, email, password, status, bio, pfp);
        return person;
    }

    /**
     * converts a Blob to an ImageIcon
     * @param blob blob to be converted
     * @return returns the ImageIcon represented by the Blob
     * @throws SQLException in case of errors with reading the blob
     * @throws IOException in case of errors with constructing an ImageIcon or BufferedImage
     */
    private ImageIcon getImage(Blob blob) throws SQLException, IOException {
        InputStream in = blob.getBinaryStream();
        BufferedImage bufferedImage = ImageIO.read(in);
        ImageIcon image = new ImageIcon(bufferedImage);
        return image;
    }

    /**
     * fetches the Person object associated with a given email address in the database
     * @param emailAddress given email address
     * @return returns the unique Person associated with emailAddress or null if not found
     * @throws SQLException in case of errors with executing queries or processing result sets
     * @throws IOException in case of errors with converting Blobs to ImageIcons
     */
    private Person fetchUser(String emailAddress) throws SQLException, IOException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM person WHERE email = ?");
        stmt.setString(1, emailAddress);
        ResultSet rs = stmt.executeQuery();
        if (rs.next() == false) {
            return null;
        }
        String password = rs.getString("password");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String sexuality = rs.getString("sexuality");
        String email = rs.getString("email");
        String bio = rs.getString("bio");
        boolean status = rs.getBoolean("status");
        ImageIcon pfp = getImage(rs.getBlob("profile_picture"));
        Person person = new Person(name, age, gender, sexuality, email, password, status, bio, pfp);
        return person;
    }

    /**
     * fetches a given user's matches stored in the database
     * @param user given person
     * @return returns an ArrayList of Person objects representing the user's matches
     * @throws SQLException in case of errors with executing queries or processing result sets
     * @throws IOException in case of errors with fetching users associated with their respective email address (see
     * the fetchUser() method)
     */
    public ArrayList<Person> fetchMatches(Person user) throws SQLException, IOException {
        ArrayList<Person> matches = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM matches WHERE user = ?");
        stmt.setString(1, user.getEmail());
        ResultSet rs = stmt.executeQuery();
        if (rs == null) {
            return null;
        }
        while (rs.next()) {
            matches.add(fetchUser(rs.getString("match")));
        }
        return matches;
    }

    /**
     * adds a match (Person object) for a user to the database
     * @param user user that match matches with
     * @param match match to add
     * @throws SQLException in case of errors with executing queries
     */
    public void addMatch(Person user, Person match) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO matches VALUES ( ?, ? )");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, match.getEmail());
        stmt.executeQuery();
    }

    /**
     * adds a compatible user (Person object) for a user to the database
     * @param user the owner of the feed
     * @param match compatible user to add to the feed
     * @throws SQLException in case of errors with executing queries
     */
    public void addToFeed(Person user, Person match) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO feed VALUES ( ?, ? )");
        stmt.setString(1, user.getEmail());
        stmt.setString(2, match.getEmail());
        stmt.executeQuery();
    }

    /**
     * fetches a user's feed of compatible people to match with from the database
     * @param user the user
     * @return returns an ArrayList of people to be included in the user's feed
     * @throws SQLException in case of errors with executing queries or processing result sets
     * @throws IOException in case of errors with converting entries in the database to Person objects
     */
    public ArrayList<Person> fetchFeed (Person user) throws SQLException, IOException {
        ArrayList<Person> feed = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM feed WHERE user = ?");
        stmt.setString(1, user.getEmail());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            feed.add(fetchUser(rs.getString("feed")));
        }
        return feed;
    }

    /**
     * constructs a tree of datingPools from all the people in the database
     * @return a Tree of ArrayLists of compatible people
     * @throws SQLException in case of errors with executing queries or processing result sets
     * @throws IOException in case of errors with converting database entries to Person objects (see fetchUser() method)
     */
    public Tree constructTree() throws SQLException, IOException {
        Tree tree = new Tree();
        PreparedStatement stmt = con.prepareStatement("SELECT email FROM person");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            tree.addPerson(fetchUser(rs.getString("email")));
        }
        return tree;
    }
}