package datingapp.program;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import datingapp.*;
import javafx.scene.image.Image;

import javax.swing.*;

public class Person implements Serializable {

    private String name;
    private int age;
    private Account account;
    private boolean single;
    private String gender;
    private String bio;
    private String poolRootName;
    private Node myNode;
    private ImageIcon profilePic;
    ArrayList<Integer> path;

    public Person (String name, int age, String gender, String email, String password, boolean single, String bio,
                   ImageIcon profilePic) {
        this.name = name;
        this.age = age;
        this.profilePic = profilePic;
        this.gender = gender;
        this.account = new Account(email, password);
        try {
            Files.write(Paths.get("src/datingapp/data/LoginData.txt"),
                    (email + ", " + password + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.single = single;
        this.bio = bio;
    }

    /**
     * returns the name of this datingapp.program.Person
     * @return name
     */
    public String getName () {
        return name;
    }

    /**
     * returns the age of this datingapp.program.Person
     * @return age
     */
    public int getAge () {
        return age;
    }

    /**
     * returns the status of this datingapp.program.Person (single or not)
     * @return single
     */
    public boolean getStatus () {
        return single;
    }

    public String getEmail () {
        return account.getEmail();
    }

    public void updatePath (int path) {
        this.path.add(path);
    }

    public String getBio() {
        return bio;
    }

    public ImageIcon getProfilePic() {
        return profilePic;
    }

    public void writeToFile (File file) {
        try {

            FileOutputStream fileOut = new FileOutputStream(file.getPath());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String toString () {
        String output = "";
        output += "Name: " + name + "\n";
        output += "Age: " + age + "\n";
        output += account.toString();
        output += "Status: " + single + "\n";
        output += "Gender: " + gender + "\n";
        output += "Bio: " + bio + "\n";
        output += "TreeID: "+ poolRootName + "\n";
        output += "Path: ";
        for (Integer i: path) {
            output += i;
        }
        output += "\n";
        return output;
    }
}
