package datingapp.exceptions;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException ()
    {
        super();
    }

    public String toString()
    {
        return "datingapp.program.Account not recognized.";
    }
}
