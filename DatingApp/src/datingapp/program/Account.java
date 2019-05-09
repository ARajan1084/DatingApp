package datingapp.program;

import java.io.Serializable;

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
    public String getEmail() {
        return email;
    }

    /**
     * returns the password of this account
     * @return password
     */
    public String getPassword() {
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
}
