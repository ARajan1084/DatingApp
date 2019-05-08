package exceptions;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException ()
    {
        super();
    }

    public String toString()
    {
        return "backend.Account not recognized.";
    }
}
