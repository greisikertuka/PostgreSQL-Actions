import java.sql.*;
import java.util.Scanner;

public class Test {
    private static Connectivity connectivity;
    private static Connection connection;

    public static void insert(String no, String fName, String lName, java.sql.Date Dt) throws SQLException {
        String sql = "INSERT INTO orders (order_no, firstname, lastname, date) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, no);
        preparedStatement.setString(2, fName);
        preparedStatement.setString(3, lName);
        preparedStatement.setDate(4, Dt);

        int row = preparedStatement.executeUpdate();

        if (row > 0) {
            System.out.println("Rekordi u ruajt me sukses!");
        }
    }

    public static void showAllRecords() throws SQLException {
        String sql = "SELECT * FROM orders";


        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int orderNo = result.getInt("order_no");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            java.sql.Date sqlDate = result.getDate("date");

            System.out.println(orderNo + ", " + firstname + ", " + lastname + ", " + sqlDate);
        }
    }

    public static void findByOrderNumber(String orderNumber) throws SQLException {
        String sql = "SELECT * FROM orders where order_no = '" + orderNumber + "'";


        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            int orderNo = result.getInt("order_no");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            java.sql.Date sqlDate = result.getDate("date");

            System.out.println(orderNo + ", " + firstname + ", " + lastname + ", " + sqlDate);
        }
    }

    public static void deleteRecord(String orderNumber) throws SQLException {
        String sql = "DELETE FROM orders" +
                " where order_no = '" + orderNumber + "';";


        Statement statement = connection.createStatement();
        try {
            statement.executeQuery(sql);
        } catch (org.postgresql.util.PSQLException e) {
            System.out.println("Rekordi u fshi me sukses!");
        }
    }

    public static void main(String[] args) throws SQLException {
        Scanner scn = new Scanner(System.in);
        connectivity = new Connectivity();
        connection = connectivity.getConnection();

        /*System.out.println("Ruaj nje te dhene!");
        System.out.println("Jep numrin e porosise, emrin dhe mbiemrin:");

        java.util.Date javaDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());

        insert(scn.nextLine(), scn.nextLine(), scn.nextLine(), sqlDate);

        System.out.println("Shfaq te dhenat!");
        showAllRecords();

        System.out.println("Kerko nje rekord!");
        System.out.println("Jep nje Order Number!");
        findByOrderNumber(scn.nextLine());
*/
        System.out.println("Fshij nje rekord!");
        System.out.println("Jep nje Order Number!");
        deleteRecord(scn.nextLine());

    }
}
