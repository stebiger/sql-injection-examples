import javax.faces.bean.ManagedBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by stefan on 29.08.16.
 */

@ManagedBean
public class Login {

    private String username;
    private String password;

    private User user;

    private final String CHECK_LOGIN_SQL = "SELECT * FROM USER WHERE USERNAME = 'x' AND PASSWORD = 'y'";

    // just for show of force
    private String query;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getQuery() {
        return query;
    }

    public String actionLogin() {
        Database db = new Database();
        query = CHECK_LOGIN_SQL.replace("x", username);
        query = query.replace("y", password);

        user = db.queryLogin(query);
            if(null != user) {
                System.out.println("Login successfull!");
                return "loginSuccess.xhtml";
            }

        System.out.println("Login failed!");
        return "loginFailed.xhtml";
    }
}
