package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user  = "postgres" ;
        String password = "revature" ;
        try {
           Class.forName("org.postgresql.Driver");
            System.out.println("Connection successful");
        } catch (ClassNotFoundException e) {
            System.err.println(" Error connecting to Postgres");
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }
}
