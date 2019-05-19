package datingapp.backend;

import datingapp.program.Person;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class AccountService {
    private Connection con;

    public AccountService() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datingapp","root","");
    }

    public void addUser (Person p) throws SQLException, IOException {
        PreparedStatement stmt = con.prepareStatement("insert into person values ( ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, p.getEmail());
        stmt.setString(2, p.getPassword());
        stmt.setString(3, p.getGender());
        stmt.setString(4, p.getSexuality());
        stmt.setString(5, p.getBio());
        stmt.setBoolean(6, p.getStatus());

        BufferedImage pfp = (BufferedImage) p.getProfilePic().getImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(pfp, "jpg", bos);
        byte[] pfpData = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(pfpData);

        stmt.setBlob(7, bis, pfpData.length);
        stmt.executeUpdate();
        con.close();
    }

    public Person isValid(String emailAdress, String password) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM person WHERE email = ?, password = ?");
        stmt.setString(1, emailAdress);
        stmt.execute();
        return null; //TODO: Fix
    }

}
