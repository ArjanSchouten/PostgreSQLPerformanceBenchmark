package jordiarjan.databases.opdracht3.Repository;

import com.sun.rowset.internal.Row;
import jordiarjan.databases.opdracht3.DBManager;
import jordiarjan.databases.opdracht3.Entities.Student;
import jordiarjan.databases.opdracht3.Faker.Faker;

import javax.sql.RowSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jordi_000 on 4-6-2015.
 */
public class StudentRepository extends Repository {

    public StudentRepository(DBManager dbManager) {
        super(dbManager);
    }

    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<Student>();
        Statement statement = null;
        try {
            statement = dbManager.getConnection().createStatement();
            statement.execute("SELECT * FROM student");
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                Student student = new Student();
                student.setStudentNumber(result.getString("studentnumber"));
                student.setAge(result.getInt("age"));
                student.setFirstName(result.getString("firstname"));
                student.setLastName(result.getString("lastname"));
                student.setCity(result.getString("city"));
                student.setInsertion(result.getString("insertion"));
                student.setSex(result.getString("sex"));
                student.setPhonenumber(result.getString("phonenumber"));
                student.setStreet(result.getString("street"));
                student.setZipcode(result.getString("postalcode"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public String insertFakeStudent() throws SQLException
    {
        Faker faker = new Faker();
        String studentNumber = faker.studentNumber();

        PreparedStatement insertStatement = dbManager.getConnection().prepareStatement(
            "INSERT INTO student (age, city, firstname, insertion, lastname, postalcode, sex, phonenumber, street, studentnumber) SELECT ?,?,?,?,?,?,CAST(? AS sex ),?,?,?" +
            "WHERE NOT EXISTS (SELECT 1 FROM student WHERE studentNumber=?)"
        );
        insertStatement.setInt(1, faker.age());
        insertStatement.setString(2, faker.address().cityPrefix());
        insertStatement.setString(3, faker.name().firstName());
        insertStatement.setString(4, faker.name().prefix());
        insertStatement.setString(5, faker.name().lastName());
        insertStatement.setString(6, faker.address().zipCode());
        insertStatement.setObject(7, faker.gender());
        insertStatement.setString(8, faker.phoneNumber().phoneNumber().replaceAll("[a-zA-Z]", ""));
        insertStatement.setString(9, faker.address().streetName());
        insertStatement.setString(10, studentNumber);
        insertStatement.setString(11, studentNumber);

        insertStatement.execute();

        return studentNumber;
    }
}
