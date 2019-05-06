import exceptions.AccountNotFoundException;

import java.io.*;
import java.util.HashMap;

// import
public class Login {

    /**
     * logs the user in if given email and password match
     * @param email given email
     * @param password given password
     * @return true if login was successful, false otherwise
     */
    public boolean isValid (String email, String password) throws IOException, AccountNotFoundException
    {
        File loginData = new File("/Users/achintya/DatingApp/DatingApp/src/LoginData.txt");
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
            return true;
        }
        return false;
    }
}
