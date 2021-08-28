package fridayexercise;

import org.junit.*;

import static org.junit.Assert.*;

public class UserMapperTest {
    
    Database database;
    UserMapper um;

    public UserMapperTest() {
    }

    public static final String G = "\u001B[32m";
    public static final String R = "\u001B[0m";

    @After
    public void tearDown() throws Exception {
        database = new Database(UserMapper.USER, UserMapper.PASSWORD, UserMapper.URL);
        um = new UserMapper(database);
        um.dropTable();
    }

    @Before
    public void setUp() throws Exception {
        database = new Database(UserMapper.USER, UserMapper.PASSWORD, UserMapper.URL);
        um = new UserMapper(database);
        um.createTable();
        um.populateTable();
    }
    
    @Test
    public void testListOfUsers() throws Exception {
        um = new UserMapper(database);
        String expResult = "1 - Name: Henning Dahl\n" + "2 - Name: Hannah Dinesen\n" + "3 - Name: Amin Kotchic\n" + "4 - Name: Harun Dupsmith\n";
        String result = um.listOfUsers();
        assertEquals(expResult, result);
        System.out.println("TEST | list Of Users --> "+G+"TEST PASSED"+R);
    }
    
    @Test
    public void testSpecificUserDetails() throws Exception {
        int user_id = 1;
        um = new UserMapper(database);
        String expResult = "Henning Dahl sdfw333 +4540949403 Rolighedsvej 22, 2100 Kbh Ø";
        String result = um.specificUserDetails(user_id);
        assertEquals(expResult, result);
        System.out.println("TEST | Specific User Details --> "+G+"TEST PASSED"+R);
    }  

    @Test
    public void testUpdateUserData() throws Exception {
        int user_id = 4;
        String newName = "Hej";
        String newLast = "Jeg";
        String newPw = "Er";
        String newPhone = "Ny";
        String newAdd = "Her";
        um = new UserMapper(database);
        int expResult = 1;
        int result = um.updateUserData(user_id, newName, newLast, newPw, newPhone, newAdd);
        assertEquals(expResult, result);
        System.out.println("TEST | Update User Data --> "+G+"TEST PASSED"+R);
    }
}