package datingapp.backend;
import datingapp.exceptions.*;
import datingapp.program.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * enables the user to edit and change specific details about their account
 * @author Achintya
 */
public class EditAccount
{
    /**
     * edits the user's account info
     * @param person the user
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param age the user's age
     * @param password the user's password
     * @param confirmPassword the user's password (for confirmation)
     * @param bio the user's bio
     * @param pfp the user's profile picture
     * @throws InvalidFirstNameException in case the first name is invalid
     * @throws InvalidLastNameException in case the last name is invalid
     * @throws InvalidAgeException in case the age is invalid or under the limit
     * @throws PasswordMismatchException in case the password is invalid
     * @throws InvalidPasswordException in case the passwords do not match up
     * @throws BioWordLengthException in case the bio is over the limit
     * @throws InvalidProfilePictureException in case the profile picture is invalid
     */
    public void edit(Person person, String firstName, String lastName, String age, String password,
                     String confirmPassword, String bio, File pfp)
            throws InvalidFirstNameException, InvalidLastNameException, InvalidAgeException, PasswordMismatchException,
            InvalidPasswordException, BioWordLengthException, InvalidProfilePictureException {
        if (firstName.length() <= 2)
        {
            throw new InvalidFirstNameException();
        }
        if (lastName.length() <= 1)
        {
            throw new InvalidLastNameException();
        }
        ImageIcon profilePicture = null;
        if (pfp != null) {
            try {
                profilePicture = new ImageIcon(ImageIO.read(pfp));
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
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException();
        }
        if (password.length() < 4 || password.length() > 10) {
            throw new InvalidPasswordException();
        }
        if (bio.length() > 350) {
            throw new BioWordLengthException(350);
        }
        person.setName(firstName + " " + lastName);
        person.setAge(Integer.parseInt(age));
        if (profilePicture != null) {
            person.setProfilePic(profilePicture);
        }
        person.setPassword(password);
        person.setBio(bio);
        try {
            new AccountService().editUser(person);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
