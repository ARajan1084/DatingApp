package datingapp.program;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;

import datingapp.*;
import javafx.scene.image.Image;

import javax.swing.*;

public class Person implements Serializable {

    private String name;
    private int age;
    private Account account;
    private boolean single;
    private String gender;
    private String sexuality;
    private String bio;
    private ImageIcon profilePic;

    public Person (String name, int age, String gender, String sexuality, String email, String password, boolean single,
                   String bio, ImageIcon profilePic) {
        this.name = name;
        this.age = age;
        this.profilePic = profilePic;
        this.gender = gender;
        this.sexuality = sexuality;
        this.account = new Account(email, password);
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

    public String getGender() {
        return gender;
    }

    public String getSexuality () {
        return sexuality;
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

    public String getBio() {
        return bio;
    }

    public ImageIcon getProfilePic() {
        return profilePic;
    }

    public String getPassword() {
        return account.getPassword();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProfilePic(ImageIcon profilePic) {
        this.profilePic = profilePic;
    }

    public void setPassword(String password) {
        account.setPassword(password);
    }

    /**
     * Akanksha
     *
     * sets the bio of this person to the String that's passed in
     * @param newBio new bio to be set
     */
    public void setBio(String newBio)
    {
        bio = newBio;
    }

    public String toString () {
        String output = "";
        output += "Name: " + name + "\n";
        output += "Age: " + age + "\n";
        output += account.toString();
        output += "Status: " + single + "\n";
        output += "Gender: " + gender + "\n";
        output += "Bio: " + bio + "\n";
        output += "\n";
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(account, person.account);
    }

}
