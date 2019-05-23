package datingapp.backend;
import datingapp.exceptions.*;
import datingapp.program.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.sql.SQLException;

public class EditAccount {
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
        }
    }
}
