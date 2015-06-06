package jordiarjan.databases.opdracht3.Simulations;

import jordiarjan.databases.opdracht3.DBManager;
import jordiarjan.databases.opdracht3.Entities.Student;
import jordiarjan.databases.opdracht3.Repository.StudentRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;

/**
 * Created by Arjan on 05/06/2015.
 */
public class Selection implements Simulation {

    private int concurrentUsers = 2;
    private int iterations = 600;
    private long time = 0;
    private DBManager dbManager;

    private List<Student> students;

    public void databaseSetup() {
        dbManager = new DBManager();
        StudentRepository studentRepository = new StudentRepository(dbManager);
        students = studentRepository.getAllStudents();
    }

    public void runSimulation() {
        for(int i = 0; i < concurrentUsers; i++){
            new Thread(new Runnable() {
                public void run() {
                    addConcurrentUser();
                    System.out.println("Average selection time " + averageRuntime());
                }
            }).start();
        }
    }

    protected void addConcurrentUser()
    {
        for(int i = 0; i < iterations; i++){
            Student student = students.get((int)Math.floor(Math.random() * students.size()));
            long start = System.currentTimeMillis();

            try {
                dbManager.getConnection().setAutoCommit(true);
                PreparedStatement studentStatement = dbManager.getConnection().prepareStatement("SELECT * FROM student WHERE firstname=? AND lastname=? LIMIT 1");
                studentStatement.setString(1, student.getFirstName());
                studentStatement.setString(2, student.getLastName());
                studentStatement.execute();
                PreparedStatement courseStatement = dbManager.getConnection().prepareStatement("" +
                        "SELECT course.* FROM " +
                        "class_student INNER JOIN roster ON roster.class = class_student.class_id " +
                        "INNER JOIN course ON course.coursecode = roster.course " +
                        "WHERE student_studentnumber=?");
                courseStatement.setString(1, student.getStudentNumber());
                courseStatement.execute();
            } catch (SQLException e){
                e.printStackTrace();
            }
            this.time += System.currentTimeMillis() - start;
        }
    }
    public long averageRuntime()
    {
        return time / this.totalRunnedIterations();
    }

    private int totalRunnedIterations() { return iterations * concurrentUsers; }
}
