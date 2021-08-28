package fridayexercise;

import static fridayexercise.Main.database;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserMapperTest {
    
    Database database;
    
    
    public UserMapperTest() {
    }
   
    
    @BeforeClass
    public static void setUpClass() { 
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException {
        database = new Database(Main.USER, Main.PASSWORD, Main.URL);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testListOfUsers() throws Exception {
        System.out.println("TEST| listOfUsers");
        UserMapper um = new UserMapper(database);
        String expResult = "1 - Name: Henning Dahl\n" + "2 - Name: Hannah Dinesen\n" + "3 - Name: Amin Kotchic\n" + "4 - Name: Harun Dupsmith\n";
        String result = um.listOfUsers();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSpecificUserDetails() throws Exception {
        System.out.println("TEST| specificUserDetails");
        int user_id = 1;
        UserMapper um = new UserMapper(database);
        String expResult = "Henning Dahl sdfw333 +4540949403 Rolighedsvej 22, 2100 Kbh Ø";
        String result = um.specificUserDetails(user_id);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateUserData() throws Exception {
        System.out.println("TEST| UpdateUserEmail");
        int user_id = 1;
        String newName = "Han";
        String newLast = "Er";
        String newPw = "Sej";
        String newPhone = "12345678";
        String newAdd = "HanErSejVej 123";
        UserMapper um = new UserMapper(database);
        int expResult = 1;
        int result = um.UpdateUserData(user_id, newName, newLast, newPw, newPhone, newAdd);
        assertEquals(expResult, result);
    }
    
}
