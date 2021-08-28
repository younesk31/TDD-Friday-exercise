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
    public void testCreateTableAndPopulate() throws Exception {
        System.out.println("createTableAndPopulate");
        UserMapper um = new UserMapper(database);
        um.createTableAndPopulate();
    }

    @Test
    public void testListOfUsers() throws Exception {
        System.out.println("TEST| listOfUsers");
        UserMapper um = new UserMapper(database);
        String expResult = "1 - Name: Han Er\n" + "2 - Name: Hannah Dinesen\n" + "3 - Name: Amin Kotchic\n" + "4 - Name: Harun Dupsmith\n";
        String result = um.listOfUsers();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSpecificUserDetails() throws Exception {
        System.out.println("TEST| specificUserDetails");
        int user_id = 1;
        UserMapper um = new UserMapper(database);
        String expResult = "Han Er Sej 12345678 HanErSejVej 123";
        String result = um.specificUserDetails(user_id);
        assertEquals(expResult, result);
    }
      
}
