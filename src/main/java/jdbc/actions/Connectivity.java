package jdbc.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {
    private static Connection connection = null;

    public Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost/postgres";
            String username = "postgres";
            String password = "Chochang8";

            //Hap connection, krijon nje objekt Connection per tu lidhur me db
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("U lidh me sukses!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
