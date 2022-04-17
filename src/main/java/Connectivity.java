import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connectivity {
    Connection connection = null;
    public Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost/postgres";
            String username = "postgres";
            String password = "Chochang8";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected succesfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
