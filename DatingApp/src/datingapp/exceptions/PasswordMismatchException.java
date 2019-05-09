package datingapp.exceptions;

public class PasswordMismatchException extends Exception {
    public PasswordMismatchException () {
        super();
    }

    public String toString() {
        return "Passwords don't match";
    }
}
