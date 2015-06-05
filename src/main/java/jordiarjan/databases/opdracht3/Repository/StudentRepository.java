package jordiarjan.databases.opdracht3.Repository;

import jordiarjan.databases.opdracht3.DBManager;
import jordiarjan.databases.opdracht3.Faker.Faker;

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
        Faker faker = new Faker();
        PreparedStatement insertStatement = dbManager.getConnection().prepareStatement("INSERT INTO student (age, city, firstname, insertion, lastname, postalcode, sex, phonenumber, street, studentnumber) VALUES (?,?,?,?,?,?,CAST(? AS sex ),?,?,?)");
        insertStatement.setInt(1, faker.age());
        insertStatement.setString(2, faker.address().cityPrefix());
        insertStatement.setString(3, faker.name().firstName());
        insertStatement.setString(4, faker.name().prefix());
        insertStatement.setString(5, faker.name().lastName());
        insertStatement.setString(6, faker.address().zipCode());
        insertStatement.setObject(7, faker.gender());
        insertStatement.setString(8, faker.phoneNumber().toString());
        insertStatement.setString(9, faker.address().streetName());
        insertStatement.setString(10, faker.studentNumber());

        insertStatement.execute();
    }
}
