import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    private static Connection connection;
    private static Statement stmt; public static Statement getStmt(){return stmt;}
    public final static String DB_NAME_PATH ="my.db";

    public static void main(String[] args) {
        File dbFile = new File(DB_NAME_PATH);
        if(dbFile.exists())dbFile.delete();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+DB_NAME_PATH);
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initBd();
    }
    public static void initBd() {
        Statement stmt = getStmt();
        try {
            stmt.execute("CREATE TABLE 'main' ('name' varchar(12) NOT NULL);");
            stmt.execute("INSERT INTO 'main' ('name') VALUES ('Peter');");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
