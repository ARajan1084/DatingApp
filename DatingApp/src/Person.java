import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Person implements Serializable {

    private String name;
    private int age;
    private String email;
    private String password;
    private boolean loggedIn = false;
    private boolean single;
    private String gender;
    private String bio;
    private String poolRootName;
    ArrayList<Integer> path;

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

    public void updatePath (int path) {
        this.path.add(path);
    }

    public String toString () {
        String output = "";
        output += "Name: " + name + "\n";
        output += "Age: " + age + "\n";
        output += "Email: " + email + "\n";
        output += "Password: " + password + "\n";
        output += "Status: " + single + "\n";
        output += "Gender: " + gender + "\n";
        output += "Bio: " + bio + "\n";
        output += "TreeID: "+ poolRootName + "\n";
        output += "Path: ";
        for (Integer i: path) {
            output += i;
        }
        return output;
    }
}
