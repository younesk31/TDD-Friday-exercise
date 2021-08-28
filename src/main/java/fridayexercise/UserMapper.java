package fridayexercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    private final Database database;

    public UserMapper(Database database) {
        this.database = database;
    }
    
    
    public void createTableAndPopulate() throws Exception {
        try (Connection connection = database.connect()) {
            String sql = "create table usertable " +
                            "(id int primary key auto_increment," +
                            "fname varchar(30)," +
                            "lname varchar(30)," +
                            "pw varchar(50)," +
                            "phone varchar(11)," +
                            "address varchar(50));"
                    + "insert into usertable (fname, lname, pw, phone, address) " +
                    "values (\"Henning\",\"Dahl\",\"sdfw333\",\"+4540949403\",\"Rolighedsvej 22, 2100 Kbh Ø\")," +
                    "(\"Hannah\",\"Dinesen\",\"fsdkk653kk\",\"+4540546754\",\"Rolighedsvej 67, 2100 Kbh Ø\")," +
                    "(\"Amin\",\"Kotchic\",\"lkjnnn443\",\"+4540345469\",\"Rolighedsvej 90, 2100 Kbh Ø\")," +
                    "(\"Harun\",\"Dupsmith\",\"kothis55\",\"+4540677667\",\"Rolighedsvej 104, 2100 Kbh Ø\");";
            
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
               ps.execute();
            } catch (SQLException ex) {
                throw new Exception(ex.getMessage());
            }
            System.out.println("Created populated table in given database...");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }
   

    public String listOfUsers() throws Exception {
        try (Connection connection = database.connect()) {
            String sql = "SELECT id, fname, lname FROM usertable";

            StringBuilder sb = new StringBuilder();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int user_id = rs.getInt("id");
                    String first_name = rs.getString("fname");
                    String last_name = rs.getString("lname");
                    sb.append(user_id).append(" - Name: ").append(first_name).append(" ").append(last_name).append("\n");
                }

            } catch (SQLException ex) {
                throw new Exception(ex.getMessage());
            }
            return sb.toString();
        } catch (SQLException ex) {
            throw new Exception("Connection to database could not be established");
        }
    }

    public String specificUserDetails(int user_id) throws Exception {
        try (Connection connection = database.connect()) {
            String sql = "SELECT fname, lname, pw, phone, address FROM usertable WHERE id=?";
            StringBuilder sb = new StringBuilder();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String first_name = rs.getString("fname");
                    String last_name = rs.getString("lname");
                    String pw = rs.getString("pw");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    sb.append(first_name).append(" ").append(last_name).append(" ").append(pw).append(" ").append(phone).append(" ").append(address);
                } else {
                    throw new Exception("Could not find user");
                }
            } catch (SQLException ex) {
                throw new Exception(ex.getMessage());
            }
            return sb.toString();
        } catch (SQLException ex) {
            throw new Exception("Connection to database could not be established");
        }
    }

    public int UpdateUserData(int user_id, String newName, String newLast, String newPw, String newPhone, String newAdd) throws Exception {
        int rowsAffeted;
        try (Connection connection = database.connect()) {
            String sql = "UPDATE usertable SET fname=?, lname=?, pw=?, phone=?, address=? WHERE id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newName);
                ps.setString(2, newLast);
                ps.setString(3, newPw);
                ps.setString(4, newPhone);
                ps.setString(5, newAdd);
                ps.setInt(6, user_id);
                rowsAffeted = ps.executeUpdate();
                System.out.println("Changed User data to --> \n");
                specificUserDetails(user_id);
            } catch (SQLException ex) {
                throw new Exception(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        return rowsAffeted;
    }
}
