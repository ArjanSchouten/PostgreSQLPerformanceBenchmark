package jordiarjan.databases.opdracht3.Repository;

import com.sun.rowset.internal.Row;
import jordiarjan.databases.opdracht3.DBManager;
import jordiarjan.databases.opdracht3.Faker.Faker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi_000 on 4-6-2015.
 */
public class ClassRepository extends Repository{

    public ClassRepository(DBManager dbManager) {
        super(dbManager);
    }

    public List<Integer> getAllClassIds() throws SQLException {
        List<Integer> classIds = new ArrayList<Integer>();
        Statement statement = dbManager.getConnection().createStatement();
        statement.execute("select id FROM class");
        ResultSet result = statement.getResultSet();
        while (result.next()) {
            classIds.add(result.getInt("id"));
        }
        return classIds;
    }

    public void insertFakeClass() throws SQLException
    {
        Faker faker = new Faker();
        PreparedStatement statement = dbManager.getConnection().prepareStatement("INSERT INTO class (enddate, name, startdate) VALUES(?,?,?)");
        statement.setDate(1, new Date(2015,6, 4));
        statement.setString(2, faker.lorem().fixedString(50));
        statement.setDate(3, new Date(2015, 6, 3));
        statement.execute();
    }

    public void addStudentToClass(int classId, String studentNumber) throws SQLException
    {
        PreparedStatement statement = dbManager.getConnection().prepareStatement("INSERT INTO class_student (class_id, student_studentnumber) VALUES (?,?)");
        statement.setInt(1, classId);
        statement.setString(2, studentNumber);
        statement.execute();
    }

    public String insertFakeModule() throws SQLException {
        Faker faker = new Faker();
        String coursecode = faker.lorem().fixedString(10).toUpperCase().replaceAll(" ", "A").replaceAll("\\.", "B");
        PreparedStatement statement = dbManager.getConnection().prepareStatement(
                "INSERT INTO course (coursecode, name, teacher, startdate, enddate)  SELECT ?,?,?,?,? WHERE NOT EXISTS (SELECT 1 FROM course WHERE coursecode=?)");
        statement.setString(1, coursecode);
        statement.setString(2, faker.lorem().words(1).get(0));
        statement.setString(3, "teacher");
        statement.setDate(4, new Date(2015,6, 4));
        statement.setDate(5, new Date(2015,6, 5));
        statement.setString(6, coursecode);
        statement.execute();
        return coursecode;
    }

    public void insertFakeRoster(int classId, String course) throws SQLException {
        Faker faker = new Faker();
        PreparedStatement statement = dbManager.getConnection().prepareStatement(
                "INSERT INTO roster (class, classroom, starttime, endtime, teacher, course)  VALUES(?,?,?,?,?,?)");
        statement.setInt(1, classId);
        statement.setString(2, faker.lorem().words(1).get(0));
        statement.setTimestamp(3, new Timestamp(2015, 6, 6, 0, 0, 0, 0));
        statement.setTimestamp(4, new Timestamp(2015, 7, 7, 0, 0, 0, 0));
        statement.setString(5, "teacher");
        statement.setString(6, course);
        statement.execute();
    }
}
