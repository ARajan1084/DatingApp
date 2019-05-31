package datingapp.program;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an Account object with an email and password
 * @author Achintya
 * @version 05/08/19
 */
public class Account implements Serializable {
    private String email;
    private String password;

    /**
     * creates an Account object with a given email and password
     * @param email email
     * @param password password
     */
    public Account (String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    /**
     * returns the email of this account
     * @return email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * returns the password of this account
     * @return password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * returns a String representation of this Account (for Object reading and writing purposes)
     * @return String representation of Account
     */
    public String toString() {
        String output = "Email: " + email + "\n";
        output += "Password: " + password + "\n";
        return output;
    }

    /**
     * sets the user's password to a specified value
     * @param password the value to set the new password to
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email) &&
                Objects.equals(password, account.password);
    }
}
