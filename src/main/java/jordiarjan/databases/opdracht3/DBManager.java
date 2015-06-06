package jordiarjan.databases.opdracht3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jordi_000 on 1-6-2015.
 */
public class DBManager {

    private Connection connection;
    private final String database = "opdracht3";
    private final String ip = "127.0.0.1";
    private final String username = "postgres";
    private final String pass = "test1234TEST_";

    public DBManager() {
        try {
            this.connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:5432/%s",ip, database ), username, pass);
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }


}
