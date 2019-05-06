import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Person {

    private String name;
    private int age;
    private String email;
    private String password;
    private Window myWindow;
    private Graphics g;
    private boolean loggedIn = false;
    private boolean single;
    private String gender;
    private String bio;

    public Person (String name, int age, String gender, String email, String password, boolean single, String bio)
            throws IOException {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.password = password;
        File data = new File("C:\\Users\\rajan\\OneDrive\\Documents\\src\\DatingApp\\DatingApp\\src\\LoginData.txt");
        BufferedWriter br = new BufferedWriter(new FileWriter(data));
        br.write(email + ", " + password);
        br.newLine();
        br.close();
        this.single = single;
        this.bio = bio;
        loggedIn = true;
    }

    /**
     * returns the name of this Person
     * @return name
     */
    public String getName () {
        return name;
    }

    /**
     * returns the age of this Person
     * @return age
     */
    public int getAge () {
        return age;
    }

    /**
     * returns the status of this Person (single or not)
     * @return single
     */
    public boolean getStatus () {
        return single;
    }
}
