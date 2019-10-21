import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection connect() {
        Connection conn = null;

        try {
            String user = "admin";
            String password = "admin";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";

            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}