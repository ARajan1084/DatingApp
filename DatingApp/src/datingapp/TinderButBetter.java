package datingapp;

import datingapp.gui.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Init for program
 *
 */
public class TinderButBetter {

    /**
     * creates a new LoginWindow (basically starts the program)
     * @param args default parameter
     * @throws IOException in case of issues when converting the profile picture to a Blob in the DATABASE
     * @throws ClassNotFoundException in case the classes of any of the parameters are not found
     * @throws SQLException in case the connection to the DATABASE fails
     */
    public static void main (String[]args) throws SQLException, IOException, ClassNotFoundException
    {
        new LoginWindow();
    }
}
