package utils;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
    private static ConnectionUtility connectionUtility;

    public static ConnectionUtility getInstance() {
        if (connectionUtility == null) {
            connectionUtility = new ConnectionUtility();
        }
        return connectionUtility;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e);
        }
    }

    private Properties props = new Properties();

    private ConnectionUtility() {
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (Exception e) {
            System.err.println("Failed to load database credentials");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(props.getProperty("db-url"),
                props.getProperty("db-username"),
                props.getProperty("db-password"));

        if (conn == null) {
            throw new RuntimeException("Could not establish a connection to the postgres database");
        }
        return conn;
    }
}
