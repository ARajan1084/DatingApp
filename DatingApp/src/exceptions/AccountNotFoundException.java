package exceptions;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException ()
    {
        super();
    }

    public String toString()
    {
        return "program.Account not recognized.";
    }
}
