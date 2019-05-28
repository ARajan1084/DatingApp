package datingapp.backend;

import datingapp.program.Person;
import datingapp.exceptions.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAccount {

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
            pfp = new ImageIcon(ImageIO.read(new File("/home/akanksha/APCSFinal/DatingApp/DatingApp/src/datingapp/gui/defaultProfilePicture.png")));
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
