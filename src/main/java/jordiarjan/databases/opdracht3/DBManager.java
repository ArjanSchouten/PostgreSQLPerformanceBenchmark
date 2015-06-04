package jordiarjan.databases.opdracht3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jordi_000 on 1-6-2015.
 */
public class DBManager {

    private Connection connection;

    public DBManager() {
        try {
            this.connection = DriverManager.getConnection("jdbc:postgresql://url:5432/db", "user", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }


}
