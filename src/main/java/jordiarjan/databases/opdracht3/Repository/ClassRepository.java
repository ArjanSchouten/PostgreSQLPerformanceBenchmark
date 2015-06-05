package jordiarjan.databases.opdracht3.Repository;

import jordiarjan.databases.opdracht3.DBManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jordi_000 on 4-6-2015.
 */
public class ClassRepository {

    private DBManager dbManager;

    public ClassRepository( DBManager manager )
    {
        this.dbManager = manager;
    }

    public void Create() throws SQLException
    {
        try{
            PreparedStatement statement = this.dbManager.getConnection().prepareStatement("INSERT INTO class (enddate, name, startdate) VALUES(?,?,?)");
            statement.setDate(1, new Date(2015,6, 4));
            statement.setString(2, "Lesson");
            statement.setDate(3, new Date(2015, 6, 3));

            statement.execute();
        }
        catch(SQLException e )
        {
            e.printStackTrace();
        }
    }

    public void AddStudentToClass( int classId, int studentId ) throws SQLException
    {
        try{
            PreparedStatement statement = this.dbManager.getConnection().prepareStatement("INSERT INTO class_student (class_id, student_studentnumber) VALUES (?,?)");
            statement.setInt(1, classId);
            statement.setInt(2, studentId );
        }
        catch( SQLException e ){
            e.printStackTrace();
        }
    }
}
