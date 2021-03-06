package fridayexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private final String USER;
    private final String PASSWORD;
    private final String URL;
    private Connection connection;

    public Database(String user, String password, String url) throws ClassNotFoundException {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null) {
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        } else {
            USER = user;
            PASSWORD = password;
            URL = url;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Connection connect() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
