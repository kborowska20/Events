package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDb {
    static Connection c = null;
    public static Connection getConnection()
    {
        if(c == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database/onlineshop");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
        return c;
    }
    public static void closeConnection(){
        try{
            if(c!= null) {
                c.close();
                c = null;
            }
        } catch (Exception e) {
        }
    }
}