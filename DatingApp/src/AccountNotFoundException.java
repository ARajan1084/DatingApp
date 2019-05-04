public class AccountNotFoundException extends Exception {

    public AccountNotFoundException ()
    {
        super();
    }

    public String toString()
    {
        return "Account not recognized.";
    }
}
