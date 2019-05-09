package datingapp.exceptions;

public class InvalidAgeException extends Exception{

    private int age;
    private final int threshold = 16;

    public InvalidAgeException(int age)
    {
        this.age = age;
    }

    public String toString () {
        String output = "Sorry. The age: " + age + " is invalid. You must be at least " + threshold + " years old to " +
                "participate.";
        return output;
    }
}
