package fridayexercise;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    final static String USER = "developer";
    final static String PASSWORD = "test";
    final static String URL = "jdbc:mysql://localhost:3306/startcode?serverTimezone=CET";
    public static Database database;

    public static void main(String[] args) throws Exception {
       
        try {
            database = new Database(USER, PASSWORD, URL);
            UserMapper um = new UserMapper(database);
            System.out.println(um.listOfUsers());
            System.out.println(um.specificUserDetails(1));
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
}
