package backend;
import program.Account;
import exceptions.AccountNotFoundException;
import program.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// import
public class Login {

    /**
     * logs the user in if given email and password match
     * @param email given email
     * @param password given password
     * @return true if login was successful, false otherwise
     */
    public Person isValid (String email, String password) throws IOException, AccountNotFoundException, ClassNotFoundException
    {
        File loginData = new File("src/data/LoginData.txt");
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

    private Person fetchUser (String email) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(new File("src/data/Users.txt"));
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

    private Person findUser(ArrayList<Person> users, String email) {
        for (Person user: users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
