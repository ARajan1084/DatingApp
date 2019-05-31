package datingapp.program;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * constructs a Person object, which represents a user
 */
public class Person implements Serializable {

    private String name;
    private int age;
    private Account account;
    private boolean single;
    private String gender;
    private String sexuality;
    private String bio;
    private String poolRootName;
    private Node myNode;
    private ImageIcon profilePic;
    ArrayList<Integer> path;

    /**
     * constructs a Person, which represents a user
     * @param name the user's name
     * @param age the user's age
     * @param gender the user's gender
     * @param sexuality the user's sexual orientation
     * @param email the user's email address
     * @param password the user's password
     * @param single the user's relationship status
     * @param bio the user's bio
     * @param profilePic the user's profile picture
     */
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
     * @return name the user's name
     */
    public String getName ()
    {
        return name;
    }

    /**
     * returns the age of this datingapp.program.Person
     * @return age the user's age
     */
    public int getAge ()
    {
        return age;
    }

    /**
     * return's the user gender
     * @return the user's gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * returns the user's sexual orientation
     * @return the user's sexuality
     */
    public String getSexuality ()
    {
        return sexuality;
    }

    /**
     * returns the status of this datingapp.program.Person (single or not)
     * @return single the user's relationship status
     */
    public boolean getStatus ()
    {
        return single;
    }

    /**
     * returns the user's email address
     * @return the user's email address
     */
    public String getEmail ()
    {
        return account.getEmail();
    }


    /**
     * returns the user's bio
     * @return the user's bio
     */
    public String getBio()
    {
        return bio;
    }

    /**
     * returns the user's profile picture
     * @return the user's profile picture
     */
    public ImageIcon getProfilePic()
    {
        return profilePic;
    }

    /**
     * returns the user's's password
     * @return the user's password
     */
    public String getPassword()
    {
        return account.getPassword();
    }

    /**
     * sets the user's name to a specified value
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * sets the user's age to a specified value
     * @param age the new age
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * sets the user's profile picture to a specified value
     * @param profilePic the new profile picture
     */
    public void setProfilePic(ImageIcon profilePic)
    {
        this.profilePic = profilePic;
    }

    /**
     * sets the user's password to a specified value
     * @param password the new password
     */
    public void setPassword(String password)
    {
        account.setPassword(password);
    }

    /**
     * sets the bio of this person to the String that's passed in
     * @param newBio new bio to be set
     */
    public void setBio(String newBio)
    {
        bio = newBio;
    }


    /**
     * toString() method for the Person object
     * @return the Person in the form of a String
     */
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
        if (path != null)
        {
            for (Integer i: path) {
                output += i;
            }
        }
        else
        {
            output += "none";
        }
        output += "\n";
        return output;
    }
}
