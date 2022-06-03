package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    private static ConnectionUtility instance;

    private ConnectionUtility() { super();}

    public static ConnectionUtility getInstance() {
        if (instance == null) {
            instance = new ConnectionUtility();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=design_style";
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
