package datingapp.backend;

import datingapp.program.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class AccountService {
    private Connection con;

    /**
     * establishes a connection "con" to the database
     * @throws ClassNotFoundException in case casting the
     * @throws SQLException in case the connection to the database fails
     */
    public AccountService() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datingapp","root","");
    }

    public void addUser (Person p) throws SQLException, IOException {
        PreparedStatement stmt = con.prepareStatement("insert into person values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, p.getEmail());
        stmt.setString(2, p.getPassword());
        stmt.setString(3, p.getName());
        stmt.setInt(4, p.getAge());
        stmt.setString(5, p.getGender());
        stmt.setString(6, p.getSexuality());
        stmt.setString(7, p.getBio());
        stmt.setBoolean(8, p.getStatus());

        BufferedImage pfp = (BufferedImage) p.getProfilePic().getImage();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(pfp, "jpg", bos);
        byte[] pfpData = bos.toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(pfpData);

        stmt.setBlob(9, bis, pfpData.length);
        stmt.executeUpdate();
        con.close();
    }

    public Person isValid(String emailAdress, String password) throws SQLException, IOException {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM person WHERE email = ? AND password = ?");
        stmt.setString(1, emailAdress);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        if (rs == null) {
            return null;
        }
        rs.next();
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String sexuality = rs.getString("sexuality");
        String email = rs.getString("email");
        String bio = rs.getString("bio");
        boolean status = rs.getBoolean("status");
        ImageIcon pfp = getImage(rs.getBlob("profile_picture"));
        Person person = new Person(name, age, gender, sexuality, email, password, status, bio, pfp);
        return person;
    }

    private ImageIcon getImage(Blob blob) throws SQLException, IOException {
        InputStream in = blob.getBinaryStream();
        BufferedImage bufferedImage = ImageIO.read(in);
        ImageIcon image = new ImageIcon(bufferedImage);
        return image;
    }

}
