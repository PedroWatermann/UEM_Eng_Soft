import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    String url = "jdbc:sqlite:databaseexamples.db";

    public DataBase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connected to database successfully");
            System.out.println("Driver: " + conn.getMetaData().getDriverName());
        } catch (SQLException e)  {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
