package jordiarjan.databases.opdracht3.Repository;

import jordiarjan.databases.opdracht3.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * Created by jordi_000 on 4-6-2015.
 */
public class StudentRepository {

    private DBManager dbManager;

    public StudentRepository( DBManager dbmanager )
    {
        this.dbManager = dbmanager;
    }

    public void Create( ) throws SQLException
    {
        PreparedStatement insertStatement = dbManager.getConnection().prepareStatement("INSERT INTO student (age, city, firstname, insertion, lastname, postalcode, sex, phonenumber, street, studentnumber) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        insertStatement.setInt(1, 22);
        insertStatement.setString(2, "Rotterdam");
        insertStatement.setString(3, "Mark");
        insertStatement.setString(4, "");
        insertStatement.setString(5, "LastName");
        insertStatement.setString(6, "15");
        insertStatement.setString(7,"man");
        insertStatement.setString(8,"0653829234");
        insertStatement.setString(9, "Hoofdstraat");
        insertStatement.setString(10, createRandomStudentNumber());
    }

    public String createRandomStudentNumber()
    {
        Random randomStudentNumber = new Random();

        long range = 9999999 - 1000000 + 1;
        long fraction = (long)(range * randomStudentNumber.nextDouble());
        int randomNumber =  (int)(fraction + 1000000);

        return Integer.toString(randomNumber);
    }
}
