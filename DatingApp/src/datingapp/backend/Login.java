package datingapp.backend;
import datingapp.program.Account;
import datingapp.exceptions.AccountNotFoundException;
import datingapp.program.Person;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handles the datingapp.backend of LoginWindow.java
 * Checks to see if the Login attempt is legitimate and returns the Person object associated with the attempt if it is
 *
 * @author  Achintya
 * @version 05/08/19
 */
public class Login {

    /**
     * logs the user in if given email and password match
     * @param email given email
     * @param password given password
     * @return true if login was successful, false otherwise
     */
    public Person isValid (String email, String password) throws IOException, AccountNotFoundException,
            ClassNotFoundException, SQLException
    {
        return new AccountService(true).isValid(email, password);
    }
}
