package datingapp.JUnitTests;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.tools.internal.jxc.ap.Const;
import datingapp.backend.AccountService;
import datingapp.backend.CreateAccount;
import datingapp.backend.EditAccount;
import datingapp.backend.Login;
import datingapp.exceptions.*;
import datingapp.program.*;
import org.junit.*;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JUDatingAppTest
{

    private Person p = new Person("John Smith", 17, "male", "heterosexual",
            "johnsmith@gmail.com", "1234", true, "hi", getImage());
    private Person p1 = new Person("Jane Doe", 18, "female", "heterosexual",
            "janedoe@gmail.com", "1234", true, "hi", getImage());
    private Person p2 = new Person("Bob Builder", 22, "female", "homosexual",
            "bobbuilder@gmail.com", "1234", false, "hi", getImage());
    private Person p3 = new Person("Mike Smith", 23, "male", "bisexual",
            "mikesmith@gmail.com", "1234", false, "hi", getImage());


    // --Test DashboardWindow
    @Test
    public void AccountServiceConstructorTest() {
        try {
            AccountService accountService = new AccountService();
        } catch (SQLException ex) {
            fail();
        } catch (IOException ex) {
            fail();
        } catch (ClassNotFoundException ex) {
            fail();
        }
    }

    @Test
    public void AccountServiceIsValidTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            assertEquals(p, accountService.isValid("johnsmith@gmail.com", "1234"));
            accountService.removeUser(p);
        } catch (Exception e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public void AccountServiceAddUserTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            ResultSet rs = accountService.runQuery("SELECT * FROM person WHERE email = 'johnsmith@gmail.com' AND password = '1234'");
            if (!rs.next()) {
                fail();
            } else {
                assertEquals(rs.getString("email"), p.getEmail());
            }
            accountService.removeUser(p);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void AccountServiceEditUserTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            p.setPassword("4321");
            accountService.editUser(p);
            ResultSet rs = accountService.runQuery("SELECT * FROM person WHERE email = 'johnsmith@gmail.com' AND password = '4321'");
            if (!rs.next()) {
                fail();
            } else {
                assertEquals(rs.getString("email"), p.getEmail());
            }
            accountService.removeUser(p);
        } catch (SQLException e) {
            e.printStackTrace();
            fail();
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void AccountServiceRemoveUserTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            accountService.removeUser(p);
            ResultSet rs = accountService.runQuery("SELECT * FROM person WHERE email = 'johnsmith@gmail.com'");
            if (rs.next()) {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void AccountServiceFetchFeedTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            accountService.addUser(p1);
            assertTrue(accountService.fetchFeed(p).contains(p1));
            accountService.removeUser(p);
            accountService.removeUser(p1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void AccountServiceFetchMatchesTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            accountService.addUser(p1);
            accountService.addMatch(p, p1);
            accountService.addMatch(p1, p);
            assertTrue(accountService.fetchMatches(p).contains(p1));
            assertTrue(accountService.fetchMatches(p1).contains(p));
            accountService.removeUser(p);
            accountService.removeUser(p1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    /*
    @Test
    public void AccountServiceConstructGlobalTreeTest() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            accountService.addUser(p1);
            assertTrue(accountService.fetchFeed(p).contains(p1));
            assertTrue(accountService.fetchFeed(p1).contains(p));
            accountService.addMatch(p, p1);
            accountService.addMatch(p1, p);
            assertTrue(accountService.fetchMatches(p).contains(p1));
            assertTrue(accountService.fetchMatches(p1).contains(p));
            accountService.removeUser(p);
            accountService.removeUser(p1);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    */

    @Test
    public void CreateAccountIsValidTest() {
        try {
            Person p = new CreateAccount().isValid("John", "Smith", this.p.getEmail(), "1234",
                    "1234", "19", "male", "heterosexual", true,
                    "hi", getFile());
            assertTrue(p.getEmail().equals(this.p.getEmail()));
            new AccountService().removeUser(p);
            Person p2 = new CreateAccount().isValid("John", "Smith", this.p.getEmail(), "1234",
                    "1234", "19", "male", "heterosexual", true,
                    "hi", null);
            assertTrue(p.getEmail().equals(this.p.getEmail()));
            new AccountService().removeUser(p);
            try {
                new CreateAccount().isValid("J", "Smith", p.getEmail(), "1234",
                        "1234", "19", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (InvalidFirstNameException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "S", p.getEmail(), "1234",
                        "1234", "19", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (InvalidLastNameException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", "jongm", "1234",
                        "1234", "19", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (InvalidEmailAddressException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", "johnsmith@gmail.com", "1234",
                        "1234", "19", "male", "heterosexual", true,
                        "hi", new File("/Users/achintya/DatingApp/DatingApp/src/datingapp/JUnitTests/JUDatingAppTest.java"));
                fail();
            } catch (InvalidProfilePictureException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", p.getEmail(), "1234",
                        "1234", "12", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (InvalidAgeException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", p.getEmail(), "1234",
                        "1234", "big", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (InvalidAgeException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", p.getEmail(), "1234",
                        "4321", "19", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (PasswordMismatchException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", p.getEmail(), "4321",
                        "1234", "19", "male", "heterosexual", true,
                        "hi", getFile());
                fail();
            } catch (PasswordMismatchException e) {

            } catch (Exception e) {
                fail();
            }

            try {
                new CreateAccount().isValid("John", "Smith", p.getEmail(), "1234",
                        "1234", "19", "male", "heterosexual", true,
                        "qwertyuiopasdfghjklzxcvbnmqweuiopasdfghjklxcvbnmwqqwertyuiopsdfghjklzxcvbnmqweasdfghjklxcvbnmxbjkdfghjdfyuygvbugfvgtrewsxswqazxesxdedcvhujhnjikmkokmplmyuioiuyuioiuioiuiiuqqqqaaaaaplkojhjiuhghuygftrdsxdsazxdeazxdesqwertyuiopasdfghjklzxcvbnmqweuiopasdfghjklxcvbnmwqqwertyuiopsdfghjklzxcvbnmqweasdfghjklxcvbnmxbjkdfghjdfyuygvbugfvgtrewsxswqazxesxdedcvhujhnjikmkokmplmyuioiuyuioiuioiuiiuqqqqaaaaaplkojhjiuhghuygftrdsxdsazxdeazxdes", getFile());
                fail();
            } catch (BioWordLengthException e) {
            } catch (Exception e) {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void EditAccountEditTest() {
        try {
            AccountService accountService = new AccountService();
            Person p = new Person("John Smith", 17, "male", "heterosexual",
                    "johnsmith@gmail.com", "1234", true, "hi", getImage());
            accountService.addUser(p);
            new EditAccount().edit(p, "Mike", "Smith", "19", "4321",
                    "4321", "bi", getFile());
            ResultSet rs = accountService.runQuery("SELECT * FROM person WHERE email = 'johnsmith@gmail.com' AND password = '4321'");
            if (!rs.next()) {
                fail();
            }
            assertEquals(rs.getString("name"), "Mike Smith");
            assertEquals(rs.getInt("age"), 19);
            assertEquals(rs.getString("password"), "4321");
            assertEquals(rs.getString("Bio"), "bi");
            accountService.removeUser(p);

            try {
                new EditAccount().edit(p, "M", "Smith", "19", "4321",
                        "4321", "bi", getFile());
                fail();
            } catch (InvalidFirstNameException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "S", "19", "4321",
                        "4321", "bi", getFile());
                fail();
            } catch (InvalidLastNameException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "Smith", "11", "4321",
                        "4321", "bi", getFile());
                fail();
            } catch (InvalidAgeException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "Smith", "thirty", "4321",
                        "4321", "bi", getFile());
                fail();
            } catch (InvalidAgeException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "Smith", "19", "1",
                        "4321", "bi", getFile());
                fail();
            } catch (PasswordMismatchException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "Smith", "19", "1234",
                        "1234", "bi", new File("datingapp/JUnitTests/GUITestLog.txt"));
                fail();
            } catch (InvalidProfilePictureException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "Smith", "19", "1",
                        "1", "bi", getFile());
                fail();
            } catch (InvalidPasswordException e) {
            } catch (Exception e) {
                fail();
            }

            try {
                new EditAccount().edit(p, "Mike", "Smith", "19", "1234",
                        "1234", "qwertyuiopasdfghjklzxcvbnmqweuiopasdfghjklxcvbnmwqqwertyuiopsdfghjklzxcvbnmqweasdfghjklxcvbnmxbjkdfghjdfyuygvbugfvgtrewsxswqazxesxdedcvhujhnjikmkokmplmyuioiuyuioiuioiuiiuqqqqaaaaaplkojhjiuhghuygftrdsxdsazxdeazxdesqwertyuiopasdfghjklzxcvbnmqweuiopasdfghjklxcvbnmwqqwertyuiopsdfghjklzxcvbnmqweasdfghjklxcvbnmxbjkdfghjdfyuygvbugfvgtrewsxswqazxesxdedcvhujhnjikmkokmplmyuioiuyuioiuioiuiiuqqqqaaaaaplkojhjiuhghuygftrdsxdsazxdeazxdes", getFile());
                fail();
            } catch (BioWordLengthException e) {
            } catch (Exception e) {
                fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void LoginIsValid() {
        try {
            AccountService accountService = new AccountService();
            accountService.addUser(p);
            assertEquals(new Login().isValid(p.getEmail(), p.getPassword()), p);
            accountService.removeUser(p);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void AccountConstructorTest() {
        Account account = new Account("mike@gmail.com", "1234");
        assertEquals(account.getEmail(), "mike@gmail.com");
        assertEquals(account.getPassword(), "1234");
    }

    @Test
    public void AccountSetPasswordTest() {
        Account account = new Account("mike@gmail.com", "1234");
        account.setPassword("smith");
        assertEquals(account.getPassword(), "smith");
    }

    @Test
    public void PersonConstructorTest() {
        String expected = "Name: John Smith\nAge: 17\nEmail: johnsmith@gmail.com\nPassword: 1234\nStatus: " +
                "true\nGender: male\nBio: hi\n\n";
        assertEquals(p.toString(), expected);
    }

    @Test
    public void PersonSetNameTest() {
        Person p = new Person("John Smith", 17, "male", "heterosexual",
                "johnsmith@gmail.com", "1234", true, "hi", getImage());
        p.setName("Mike Smith");
        assertEquals(p.getName(), "Mike Smith");
    }

    @Test
    public void PersonSetAgeTest() {
        Person p = new Person("John Smith", 17, "male", "heterosexual",
                "johnsmith@gmail.com", "1234", true, "hi", getImage());
        p.setAge(19);
        assertEquals(p.getAge(), 19);
    }

    @Test
    public void PersonSetPasswordTest() {
        Person p = new Person("John Smith", 17, "male", "heterosexual",
                "johnsmith@gmail.com", "1234", true, "hi", getImage());
        p.setPassword("4321");
        assertEquals(p.getPassword(), "4321");
    }

    @Test
    public void PersonSetBioTest() {
        Person p = new Person("John Smith", 17, "male", "heterosexual",
                "johnsmith@gmail.com", "1234", true, "hi", getImage());
        p.setBio("bi");
        assertEquals(p.getBio(), "bi");
    }

    @Test
    public void TreePrintMatchesTest() {
        Tree tree = new Tree();
        tree.addPerson(p);
        tree.addPerson(p1);
        assertEquals(tree.printMatches(p), "Jane Doe");
        assertEquals(tree.printMatches(p1), "John Smith");
    }

    @Test
    public void TreeGetMatchesTest() {
        Tree tree = new Tree();
        tree.addPerson(p2);
        tree.addPerson(p3);
        tree.getMatches(p2, new ArrayList<Person>());
        tree.getMatches(p3, new ArrayList<Person>());
    }

    @Test
    public void NodeAddPersonTest() {
        Node node = new Node("long term");
        assertTrue(node.addPerson(p));
        assertFalse(node.addPerson(p));
    }

    @Test
    public void NodeGetNodeByArgumentTest() {
        Node node = new Node("long term");
        assertNull(node.getNodeByArgument("long term"));
    }

    @Test
    public void ConstantKeyTest() {
        ConstantKey ck = new ConstantKey();
    }

    @Test
    public void QuestionSpitterTest() {
        try {
            QuestionSpitter questionSpitter = new QuestionSpitter();
            questionSpitter.getRandomList();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    private ImageIcon getImage() {
        try {
            return new ImageIcon(ImageIO.read(new File("/Users/achintya/DatingApp/DatingApp/src/datingapp/gui/defaultProfilePicture.png")));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private File getFile() {
        return new File("/Users/achintya/DatingApp/DatingApp/src/datingapp/gui/defaultProfilePicture.png");
    }

}
