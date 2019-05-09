package datingapp.backend;
import datingapp.program.Account;
import datingapp.exceptions.AccountNotFoundException;
import datingapp.program.Person;

import java.io.*;
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
    public Person isValid (String email, String password) throws IOException, AccountNotFoundException, ClassNotFoundException
    {
        File loginData = new File("src/datingapp.data/LoginData.txt");
        HashMap<String, Account> accounts = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(loginData));
        String input = br.readLine();
        while (input != null && input.length() != 0)
        {
            String[] loginInfo = input.split(", ");
            Account ac = new Account(loginInfo[0], loginInfo[1]);
            accounts.put( email, ac );
            input = br.readLine();
        }

        Account ac = accounts.get(email);
        if (ac == null) {
            throw new AccountNotFoundException();
        }
        if (ac.getPassword().equals(password)) {
            return fetchUser(email);
        }
        return null;
    }

    /**
     * helps by fetching the Person object associated with the email provided
     * @param email email
     * @return returns the Person object associated with the email account
     * @throws FileNotFoundException when the file with User object datingapp.data is not found
     * @throws IOException when errors with FileInput and Output streams occur
     * @throws ClassNotFoundException when the method can't cast Object info in the file into a Person object
     */
    private Person fetchUser (String email) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("src/datingapp.data/Users.txt"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        Object ob = oi.readObject();
        ArrayList<Person> users = new ArrayList<>();
        while (ob != null) {
            users.add((Person)ob);
            try {
                ob = oi.readObject();
            } catch (EOFException e) {
                break;
            }
        }
        return findUser(users, email);
    }

    /**
     * helper method for fetchUser() that finds a given Person object in an ArrayList based on an email
     * @param users ArrayList of Person objects to search
     * @param email email to search for
     * @return returns the Person object associated with the given email, or null if no such person exists
     */
    private Person findUser(ArrayList<Person> users, String email) {
        for (Person user: users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
