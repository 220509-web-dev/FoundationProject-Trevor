package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    public static Connection getConnection(){
        String url = "jdbc:postgresql://localhost:5432/design_style";
        String username  = "postgres" ;
        String password = "trevor96" ;
        try {
          Connection connection = DriverManager.getConnection(url, username, password);
          System.out.println("Connection successful");
          return connection;
        } catch (SQLException e) {
            System.out.println(" Error connecting to Postgres");
            e.printStackTrace();
            return null;
        }
    }
}
