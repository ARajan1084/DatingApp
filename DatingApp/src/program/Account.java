package program;

import java.io.Serializable;

public class Account implements Serializable {
    private String email;
    private String password;

    public Account (String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        String output = "Email: " + email + "\n";
        output += "Password: " + password + "\n";
        return output;
    }
}
