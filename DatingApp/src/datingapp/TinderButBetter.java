package datingapp;

import datingapp.backend.AccountService;
import datingapp.gui.*;
import datingapp.program.Tree;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Init for program
 *
 */
public class TinderButBetter {

    public static void main (String[]args) throws SQLException, IOException, ClassNotFoundException {

        new LoginWindow();
    }
}
