package jordiarjan.databases.opdracht3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jordi_000 on 1-6-2015.
 */
public class DBManager {

    private static DBManager dbManager = null;

    private Connection connection;
    private final String database = "x";
    private final String ip = "x";
    private final String username = "x";
    private final String pass = "x";

    private DBManager() {
        try {
            this.connection = DriverManager.getConnection(String.format("jdbc:postgresql://%s:5432/%s",ip, database ), username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance()
    {
        if(DBManager.dbManager == null)
            DBManager.dbManager = new DBManager();
        return DBManager.dbManager;
    }


    public Connection getConnection() {
        return this.connection;
    }


}
