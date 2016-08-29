import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefan on 29.08.16.
 */
public class Database {
    private Connection conn = null;
    private String url = "jdbc:mysql://172.17.0.2:3306/";
    private String dbName = "security";
    private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "admin";

    public User queryLogin(String query) {
        User user = null;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement st = conn.createStatement();
            System.out.println("Query is: " + query);

            ResultSet result = st.executeQuery(query);
            if(result.next()) {
                user = new User();
                user.setUsername(result.getString("username"));
                user.setStreet(result.getString("street"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<String> queryFriends(String query) {
        List<String> friends = new ArrayList<>();
        try {
        Class.forName(driver).newInstance();
        conn = DriverManager.getConnection(url + dbName, userName, password);
        Statement st = conn.createStatement();
        System.out.println("Query is: " + query);

        ResultSet result = st.executeQuery(query);
            while(result.next()) {
                friends.add(result.getString("friendname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return friends;
    }
}
