package fridayexercise;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserMapperTest {
    
    Database database;
    UserMapper um;

    public UserMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        database = new Database(UserMapper.USER, UserMapper.PASSWORD, UserMapper.URL);
        um = new UserMapper(database);
        um.createTable();
        um.populateTable();
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testListOfUsers() throws Exception {
        um = new UserMapper(database);
        String expResult = "1 - Name: Henning Dahl\n" + "2 - Name: Hannah Dinesen\n" + "3 - Name: Amin Kotchic\n" + "4 - Name: Harun Dupsmith\n";
        String result = um.listOfUsers();
        assertEquals(expResult, result);
        System.out.println("TEST| listOfUsers --> PASSED");
    }
    
    @Test
    public void testSpecificUserDetails() throws Exception {
        int user_id = 1;
        um = new UserMapper(database);
        String expResult = "Henning Dahl sdfw333 +4540949403 Rolighedsvej 22, 2100 Kbh Ø";
        String result = um.specificUserDetails(user_id);
        assertEquals(expResult, result);
        System.out.println("TEST| listOfUsers --> PASSED");
    }  
}
