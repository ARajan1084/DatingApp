package backend;

import backend.Person;
import exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateAccount {

    public Person isValid (String firstName, String lastName, String email, String password, String confirmPassword,
                           String age, String gender, String sexuality, boolean single, String bio)
            throws InvalidFirstNameException, InvalidLastNameException, InvalidAgeException, PasswordMismatchException,
            InvalidPasswordException, IOException, InvalidEmailAddressException {
        if (firstName.length() <= 2)
        {
            throw new InvalidFirstNameException();
        }
        if (lastName.length() <= 1)
        {
            throw new InvalidLastNameException();
        }
        try {
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new InvalidAgeException(0);
        }
        int intAge = Integer.parseInt(age);
        if (intAge <= 16)
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
        return createAccount(firstName + " " + lastName, intAge, gender, email, password, single, bio);
    }

    private Person createAccount(String name, int age, String gender, String email, String password, boolean single,
                                 String bio)
        throws IOException {
        Person person = new Person(name, age, gender, email, password, single, bio);
        person.writeToFile(new File("src/data/Users.txt"));
        return person;
    }
}
