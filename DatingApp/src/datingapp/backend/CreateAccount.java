package datingapp.backend;

import datingapp.program.Person;
import datingapp.exceptions.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * creates an account for the Person object (user) that wanted to create an account
 * (called from CreateAccountWindow.java)
 * @author Achintya
 */
public class CreateAccount {

    /**
     * creates an account for a Person (user) using the data passed in as the parameters,
     * but only if everything is valid
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param email the user's email
     * @param password the user's password
     * @param confirmPassword the user's password (for confirmation)
     * @param age the user's age
     * @param gender the user's gender
     * @param sexuality the user's sexual orientation
     * @param single the user's availability
     * @param bio the user's bio
     * @param profilePic the user's profile picture
     * @return a Person with the correct
     * @throws InvalidFirstNameException in case the user's first name is invalid
     * @throws InvalidLastNameException in case the user's last name is invalid
     * @throws InvalidAgeException in case the user's age is invalid or under the limit
     * @throws PasswordMismatchException in case the user's password is invalid
     * @throws InvalidPasswordException in case the user's passwords do not match up when being created
     * @throws IOException in case of issues when converting the profile picture to a Blob in the DATABASE
     * @throws InvalidEmailAddressException in case the email the user entered in is invalid
     * @throws InvalidProfilePictureException in case the profile picture the user wants to use is invalid
     * @throws BioWordLengthException in case the length of the bio the user entered in is over the limit of 350 chars
     * @throws ClassNotFoundException in case the classes of any of the parameters are not found
     * @throws SQLException in case the connection to the DATABASE fails
     */
    public Person isValid (String firstName, String lastName, String email, String password, String confirmPassword,
                           String age, String gender, String sexuality, boolean single, String bio, File profilePic)
            throws InvalidFirstNameException, InvalidLastNameException, InvalidAgeException, PasswordMismatchException,
            InvalidPasswordException, IOException, InvalidEmailAddressException, InvalidProfilePictureException,
            BioWordLengthException, ClassNotFoundException, SQLException {
        if (firstName.length() <= 2)
        {
            throw new InvalidFirstNameException();
        }
        if (lastName.length() <= 1)
        {
            throw new InvalidLastNameException();
        }
        ImageIcon pfp = null;
        if (profilePic == null) {
            pfp = new ImageIcon(ImageIO.read(new File("/Users/achintya/DatingApp/DatingApp/src/datingapp/gui/defaultProfilePicture.png")));
        } else {
            try {
                pfp = new ImageIcon(ImageIO.read(profilePic));
            } catch (Exception e) {
                throw new InvalidProfilePictureException();
            }
        }
        try {
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException(0);
        }
        int intAge = Integer.parseInt(age);
        if (intAge < 16)
        {
            throw new InvalidAgeException(intAge);
        }
        if (!email.contains("@") || !email.contains(".com"))
        {
            throw new InvalidEmailAddressException();
        }
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException();
        }
        if (password.length() < 4 || password.length() > 10) {
            throw new InvalidPasswordException();
        }
        if (bio.length() > 350) {
            throw new BioWordLengthException(350);
        }
        return createAccount(firstName + " " + lastName, intAge, gender, sexuality, email, password, single, bio, pfp);
    }

    /**
     * adds the Person (user) to the DATABASE
     * @param name the user's full name
     * @param age the user's age
     * @param gender the user's gender
     * @param sexuality the user's sexual orientation
     * @param email the user's email address
     * @param password the user's password
     * @param single the user's availability
     * @param bio the user's bio
     * @param pfp the user's profile picture
     * @return the Person (user) that was added to the DATABASE
     * @throws IOException in case of issues when converting the profile picture to a Blob in the DATABASE
     * @throws ClassNotFoundException in case the classes of any of the parameters are not found
     * @throws SQLException in case the connection to the DATABASE fails
     */
    private Person createAccount(String name, int age, String gender, String sexuality, String email, String password,
                          boolean single, String bio, ImageIcon pfp)
            throws IOException, ClassNotFoundException, SQLException {
        Person person = new Person(name, age, gender, sexuality, email, password, single, bio, pfp);
        try {
            new AccountService().addUser(person);
        } catch (SQLException s) {
            System.out.println("ERROR: Database error!"+s.getMessage());
        }
        return person;
    }
}
