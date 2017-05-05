package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDb {

    public static Connection getConnection()
    {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/events");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return c;
    }
}
